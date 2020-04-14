package com.protectdev.manage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {


    @RequestMapping("home/userInfo")
    @ResponseBody
    public String getUserInfo(){

        return "";
    }


}
