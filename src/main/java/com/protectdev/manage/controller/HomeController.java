package com.protectdev.manage.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class HomeController {


    @RequestMapping("home/userInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request){

        HttpSession session = request.getSession();

        Map<String,String> userInfo = new ConcurrentHashMap<>();

        userInfo.put("name",(String) session.getAttribute("name"));
        userInfo.put("username",(String) session.getAttribute("username"));
        userInfo.put("role",(String) session.getAttribute("permission"));

        String userIfoJson = JSONObject.toJSON(userInfo).toString();


        return userIfoJson;
    }


}
