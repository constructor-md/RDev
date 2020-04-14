package com.protectdev.manage.controller;


import com.protectdev.manage.util.MessageUtil;
import com.protectdev.manage.util.RandomStringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PhoneCodeController {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     *
     * @param request
     * @return
     *
     * 专门的验证码发送并存入redis的逻辑入口
     * 前端“发送验证码“按钮的接受者
     * 前端按下验证码发送按钮，后端接收后发送验证码并将验证码存入redis
     *
     * 其他控制器则根据redis中的数据对用户输入进行验证
     *
     */
    @RequestMapping("phoneCode")
    @ResponseBody
    public String phoneCodeSend(HttpServletRequest request) {

        // todo 检查验证码
        //获取随机六位数验证码
        String vCode = RandomStringUtil.getRandom();

        //获取sessionId和随机验证码
        String sessionId = request.getRequestedSessionId();
        String phoneNum = (String) request.getSession().getAttribute("phoneNum");

        //验证码和sessionId存入redis
        redisTemplate.opsForValue().set(sessionId, vCode);

        System.out.println(redisTemplate.opsForValue().get(sessionId));

        //发送验证码
        // todo 阿里短信应该告知有效时间，前端允许一定时间后重新发送，后端一定时间没有正确校核验证码则将redis中验证码消除，验证码失效，必须重新发送
        String state = MessageUtil.sendMsg(phoneNum, vCode);

        if (state.equals("发送失败")){
            return "{\"status\":\"sendNo\",\"desc\":\"发送失败\"}";
        }
        return "{\"status\":\"send\",\"desc\":\"发送成功\"}";

    }


}
