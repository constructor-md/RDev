package com.protectdev.manage.service.impl;

import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.User;
import com.protectdev.manage.service.intf.RegisterService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 *
 * 涉及的数据库元素有：job_id,user_id,username,password,ps_modify_time
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String registerCheck(HttpServletRequest request) {

        User user = new User();
        user.setJobId(request.getParameter("jobId"));
        user.setName(request.getParameter("name"));

        // todo 验证码检查
        //取得redis中发送信息前记录的验证码，与填写的vCode进行比较

        String sessionId = request.getRequestedSessionId();
        String vCode = redisTemplate.opsForValue().get(sessionId);
        //设置验证码缓存有效期


        //不能用==比较，两者值相同，但是并没有指向同一个地址，要使用equals比较内容
        if (!request.getParameter("vCode").equals(vCode)) {
            //检查完毕，不允许注册,清除验证码记录
            redisTemplate.delete(sessionId);
            return "验证码错误";
        }

        //检查完毕，允许注册,清除验证码记录
        redisTemplate.delete(sessionId);

        //user.setName(request.getParameter("name"));
        user.setUsername(request.getParameter("username"));

        //注册密码md5加密

        String password = DigestUtils.md5DigestAsHex(request.getParameter("password").getBytes());
        user.setPassword(password);

        Date date = new Date(System.currentTimeMillis());
        // todo 格式化日期存储
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年mm月dd日");

        //登记密码修改时间
        user.setPsModifyTime(date);

        //返回值是修改的行数
        int register = userMapper.register(user);

        if (register == 1){
            return "注册成功";
        }

        return "注册失败";

    }
}
