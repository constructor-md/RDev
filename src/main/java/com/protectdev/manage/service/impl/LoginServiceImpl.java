package com.protectdev.manage.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.mapper.PermissionMapper;
import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.Permission;
import com.protectdev.manage.pojo.User;
import com.protectdev.manage.service.intf.LoginService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private PermissionMapper permissionMapper;


    /**
     *
     * @param request
     * @param response
     * @return
     *
     * 实现登录逻辑
     * 登陆执行前，检查IP地址，IP符合则直接登录，不符合则使用手机发送验证码，验证验证码登陆
     *
     * 登陆后密码错误，或者冻结，到时间改密码了，都进行提示，标识登陆失败
     */
    @Override
    public String login(HttpServletRequest request , HttpServletResponse response) {

        //获取对应session，用于写入登录信息，一定时间后应该失效
        HttpSession session = request.getSession();

        User user = new User();
        user.setUsername(request.getParameter("username"));

        //密码检查
        String password = request.getParameter("password");
        //密码md5加密,与数据库中的加密结果进行比对
        //应该加盐值、缓存密码尝试次数
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Password);


        //IP不正确则必须进行验证码判断，正确则不判断验证码
        if (session.getAttribute("IP") == null){

                if (request.getParameter("vCode") == null){
                    return "{\"status\":\"vNull\",\"desc\":\"请填写验证码\"}";
                }

                //取得redis中发送信息前记录的验证码，与填写的vCode进行比较

                String sessionId = request.getRequestedSessionId();
                String vCode = redisTemplate.opsForValue().get(sessionId);

                //不能用==比较，两者值相同，但是并没有指向同一个地址，要使用equals比较内容
                if (!request.getParameter("vCode").equals(vCode)) {
                    //检查完毕，清除验证码记录
                    redisTemplate.delete(sessionId);
                    return "{\"status\":\"vNo\",\"desc\":\"验证码错误\"}";
                }

                redisTemplate.delete(sessionId);
        }

        //IP符合/验证码正确，执行登录
        User login = userMapper.login(user);


        if (login == null){
            return "{\"status\":\"psNo\",\"desc\":\"密码错误\"}";
        }
        //检查两个月内是否有登陆记录
        Date loginTime = new Date(System.currentTimeMillis());

        if (login.getLoginTime() != null){

            Date oldLoginTime = login.getLoginTime();
            Long nMs = loginTime.getTime() - oldLoginTime.getTime();
            // todo 数据库中没有存时分秒 最长可能延后24h
            int nDay = (int) ((loginTime.getTime() - oldLoginTime.getTime())/(1000*60*60*24));
            //System.out.println(nMs);
            if (nDay >= 60){
                //todo 账号冻结
                return "{\"status\":\"fz\",\"desc\":\"您的账号两个月未登陆，已被冻结，请联系管理员解冻\"}";
            }
        }

        //登记登陆时间
        login.setLoginTime(loginTime);
        userMapper.updateLoginTime(login);

        //登陆成功后
        //session中写入用户名和权限信息，用户名用于判断用户是否登录，权限信息记录该用户权限

        session.setAttribute("username", login.getUsername());
        session.setAttribute("phoneNum",login.getPhoneNum());

        Permission permission = permissionMapper.getPermission(login.getPermission());

        session.setAttribute("userGet",permission.getUserGet());
        session.setAttribute("userPost",permission.getUserPost());
        session.setAttribute("userAdd",permission.getUserAdd());
        session.setAttribute("userDelete",permission.getUserDelete());

        session.setAttribute("deviceGet",permission.getDeviceGet());
        session.setAttribute("devicePost",permission.getDeviceGet());
        session.setAttribute("deviceAdd",permission.getDeviceAdd());
        session.setAttribute("deviceDelete",permission.getDeviceDelete());

        session.setAttribute("temGet",permission.getTemGet());
        session.setAttribute("temPost",permission.getTemPost());
        session.setAttribute("temAdd",permission.getTemAdd());
        session.setAttribute("temDelete",permission.getTemDelete());

        session.setAttribute("softGet",permission.getSoftGet());
        session.setAttribute("softPost",permission.getSoftPost());
        session.setAttribute("softAdd",permission.getSoftAdd());
        session.setAttribute("softDelete",permission.getSoftDelete());

        session.setAttribute("faultGet",permission.getFaultGet());
        session.setAttribute("faultPost",permission.getFaultPost());
        session.setAttribute("faultAdd",permission.getFaultAdd());
        session.setAttribute("faultDelete",permission.getFaultDelete());

        session.setAttribute("permissionGet",permission.getPermissionGet());
        session.setAttribute("permissionPost",permission.getPermissionPost());
        session.setAttribute("permissionAdd",permission.getPermissionAdd());
        session.setAttribute("permissionDelete",permission.getPermissionDelete());

        session.setAttribute("examine",permission.getExamine());//审批员权限，增删改操作前检查，根据结果加入实表或者待审批表


        //设置session过期时间为10分钟
        // todo 最好是用户操作的时候重置时间到期之后使session过期
        //session.setMaxInactiveInterval(100);

        // todo 用户登录有些信息不需要返回 登陆成功后不用返回用户信息，用户信息写入session等待其他页面获取
        login.setPassword(null);

        //将登陆后获得的的用户信息转为JSON字符串
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(login);
        String loginJson = jsonObject.toJSONString();

        //释放内存，todo 可能有线程安全问题！
        login = null;

        return "{\"status\":\"ok\"}";
    }

}
