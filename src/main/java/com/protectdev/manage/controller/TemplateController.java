package com.protectdev.manage.controller;


import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 * 用于处理来自模板信息页面的请求
 *
 */
@Controller
public class TemplateController {

    @Resource
    private DeviceMapper deviceMapper;


    @RequestMapping("tem/add")
    @ResponseBody
    @PermissionCheck("temAdd")
    public String temAdd(){





        return "";
    }







}
