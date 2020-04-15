package com.protectdev.manage.controller;




import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.User;
import com.protectdev.manage.service.intf.RegisterService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class RegisterController {


    @Resource
    private UserMapper userMapper;

    @Resource
    private RegisterService registerService;


    @RequestMapping("register")
    @ResponseBody
    public String register(HttpServletRequest request){

        String registerState = registerService.register(request);

        return registerState;

    }



    @RequestMapping("register/check")
    @ResponseBody
    public String registerCheck(HttpServletRequest request){


        HttpSession session = request.getSession();

        //工号可能重复，工号和姓名同时检测
        String jobId = request.getParameter("jobId");
        String name = request.getParameter("name");

        User idNameCheck = userMapper.idNameCheck(jobId,name);

        //检查 工号和姓名组合信息 是否存在
        if (idNameCheck == null){
            return "{\"status\":\"null\",\"desc\":\"您的数据不在数据库中，请与管理员联系\"}";
        }

        //检查用户是否已注册
        if (idNameCheck.getUsername() != null){
            return "{\"status\":\"userExist\",\"desc\":\"用户已存在，注册失败\"}";
        }

        //检查用户名是否重复
        String username = request.getParameter("username");
        User usernameCheck = userMapper.usernameCheck(username);
        //用户名已存在
        if (usernameCheck != null){
            return "{\"status\":\"usernameExist\",\"desc\":\"用户名已存在\"}";
        }

        String phoneNum = idNameCheck.getPhoneNum();
        //电话号码写入session

        session.setAttribute("phoneNum",phoneNum);
        session.setAttribute("register","ok");
        session.setAttribute("jobId",idNameCheck.getJobId());
        session.setAttribute("name",idNameCheck.getName());

        Map<String,String> state = new ConcurrentHashMap();

        state.put("status","ok");
        state.put("phoneNum",phoneNum);

        String stateJson = JSONObject.toJSON(state).toString();

        return stateJson;

    }



}
