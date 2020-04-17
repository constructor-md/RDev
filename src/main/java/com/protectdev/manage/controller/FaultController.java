package com.protectdev.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.FaultMapper;
import com.protectdev.manage.pojo.Fault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class FaultController {


    @Resource
    private FaultMapper faultMapper;



    @RequestMapping("fault/get")
    @ResponseBody
    @PermissionCheck("faultGet")
    public String faultGet(HttpServletRequest request){

        Fault fault = faultMapper.getFaultByFaultId(Integer.parseInt(request.getParameter("faultId")));

        String faultJson = JSONObject.toJSONString(fault);

        return faultJson;

    }




}
