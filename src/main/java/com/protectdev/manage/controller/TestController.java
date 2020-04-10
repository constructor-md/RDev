package com.protectdev.manage.controller;


import com.protectdev.manage.annotation.PermissionCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @RequestMapping("test")
    @ResponseBody
    @PermissionCheck(value = "deviceAdd")
    public String test(HttpServletRequest request){

        String user =  (String) request.getSession().getAttribute("username");
        String permission = (String) request.getSession().getAttribute("permission");

        return "普通用户：" + user + "，权限角色：" + permission;

    }

}
