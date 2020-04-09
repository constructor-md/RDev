package com.protectdev.manage.controller;


import com.protectdev.manage.annotation.PermissionCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * 用于处理来自登录信息页面的请求
 *
 *
 */
@Controller
public class DeviceController {

    @RequestMapping("deviceGet")
    @ResponseBody
    @PermissionCheck(value = "deviceGet")
    public String deviceGet(HttpServletRequest request){


        return "device";
    }


}
