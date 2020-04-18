package com.protectdev.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.SoftwareMapper;
import com.protectdev.manage.pojo.Software;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SoftwareController {

    @Resource
    private SoftwareMapper softwareMapper;

    @RequestMapping("soft/get")
    @ResponseBody
    @PermissionCheck("softGet")
    public String softGet(HttpServletRequest request){

        Software software = softwareMapper.getSoftById(Integer.parseInt(request.getParameter("softId")));

        String softwareJson = JSONObject.toJSONString(software);

        return softwareJson;
    }


}
