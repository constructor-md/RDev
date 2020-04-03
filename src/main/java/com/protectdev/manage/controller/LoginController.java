package com.protectdev.manage.controller;


import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.User;
import com.protectdev.manage.service.intf.LoginService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private LoginService loginService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     *
     * @param request
     * @param response
     * @return
     *
     * 前端”登陆按钮的接收者“
     * 将发送至此的登录信息，包括IP和验证码，交给LoginServiceImpl业务层做判断
     *
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request , HttpServletResponse response){

        System.out.println("进入了登陆接口");

        String loginState = loginService.login(request,response);

        HttpSession session = request.getSession();

        //登陆成功后会在session中填入一些信息，没有则登陆失败，登陆失败一定次数/密码错误一定次数则该用户名一定时间内不允许登录

        // todo 如果登陆返回密码错误信息一定次数，就应该禁止登陆判断，前端锁住输入框是一部分，后端不进行该判断又是一部分，计时后清楚又是一部分
        //      用session记住该信息，半小时后session失效又可以登陆是不是好点？

        if (session.getAttribute("passFault") == null){
            session.setAttribute("passFault" , 0);
        }

        if (loginState.equals("密码错误")){

            int passFault = (int)session.getAttribute("passFault");
            passFault++;
            session.setAttribute("passFault" , passFault);
            if (passFault >= 5){

                //半小时后session失效，passFault清零
                return "密码错误五次及以上，半小时内禁止登陆";
            }

            return loginState;

        }
        // todo 返回密码正确后应该对session中的密码错误次数清零，否则session失效的半小时内可能登陆成功后又密码失败的话会没那么多次

        return loginState;

    }

    /**
     * @param request
     * @return
     *
     *  前端”账号检测“按钮的接收者
     * 登陆前执行用户名检查，并根据用户名找到手机号，显示在前端
     *
     */
    @RequestMapping("login/check")
    @ResponseBody
    public String loginCheck(HttpServletRequest request){

        User user = new User();
        user.setUsername(request.getParameter("username"));

        User usernameCheck = userMapper.usernameCheck(user);

        //todo 对象产生过多容易导致内存溢出，应该及时释放对象内存
        //检查用户名是否存在
        if (usernameCheck == null){
            return "用户名错误/该用户未注册";
        }
        String phoneNum = usernameCheck.getPhoneNum();

        //电话号码写入session
        HttpSession session = request.getSession();
        session.setAttribute("phoneNum",phoneNum);

        // todo IP验证

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }


        // todo 前端JS应该获得IP改变页面形式，IP信息不要用其他变量代替传输，后端需要获得IP判断是否进行验证码判断
        //      JS有被篡改的可能，IP硬编码应该写在后端，但是前端只负责进行是否填写验证码的判断，验证还是由后端做
        //      前端有了IP之后没有验证码发送框，后端有了IP登陆的时候不判断验证码！
        //      这里有个问题，验证码发送的控制器如果仍然可以通过URL直接访问到的话，会有系统漏洞
        if(ip.equals("0:0:0:0:0:0:0:1")){

            System.out.println("IP正确");
            session.setAttribute("IP",ip);
        }


        //进行了账号检测

        //释放内存
        usernameCheck = null;
        return phoneNum;
    }

    //登出，使session失效，并且要消除redis中的相关内容
    @RequestMapping("logout")
    public void logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionId = request.getRequestedSessionId();
        session.invalidate();

        redisTemplate.delete(sessionId);


    }

    // todo 指定访问POST方法，防止URL暴露信息


}
