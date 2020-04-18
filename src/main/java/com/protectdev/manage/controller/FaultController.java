package com.protectdev.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.FaultMapper;
import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.Fault;
import com.protectdev.manage.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FaultController {


    @Resource
    private FaultMapper faultMapper;

    @Resource
    private UserMapper userMapper;


    @RequestMapping("fault/get")
    @ResponseBody
    @PermissionCheck("faultGet")
    public StringBuilder faultGet(HttpServletRequest request){

        //组合数据拼串
        Fault fault = faultMapper.getFaultByFaultId(Integer.parseInt(request.getParameter("faultId")));

        User user = userMapper.userGetById(fault.getUpUserId());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(JSONObject.toJSONString(fault).replace("}","")).append(",")
                .append(JSONObject.toJSONString(user).replace("{",""));

        return stringBuilder;

    }




}
