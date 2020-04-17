package com.protectdev.manage.controller;


import com.alibaba.fastjson.JSONObject;

import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.PermissionMapper;
import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.User;
import com.protectdev.manage.util.Check;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 *
 * 用于处理来自用户管理页面的请求
 *
 */
@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @RequestMapping("user/get")
    @ResponseBody
    @PermissionCheck("userGet")
    public String userGet(@RequestBody User user){

        //根据工号、姓名、用户名查找

        List<User> userList = userMapper.getUser(user);

        System.out.println(userList.get(0).toString());

        user = null;

        String userGet = JSONObject.toJSON(userList).toString();

        return userGet;

    }


    @RequestMapping("user/post")
    @ResponseBody
    @PermissionCheck("userPost")
    public String userPost(@RequestBody User user){


        System.out.println(user.getLoginTime());

        int userPost = userMapper.updateUser(user);


        if (userPost != 0){
            return "{\"status\":\"ok\"}";
        }

        return "{\"status\":\"err\"}";

    }


    @RequestMapping("user/add")
    @ResponseBody
    @PermissionCheck("userAdd")
    public String userAdd(@RequestBody User user){

        if (user.getJobId() == null || user.getJobId().equals("")){

            return "{\"status\":\"jobIdNull\"}";
        }
        if (user.getName() == null || user.getName().equals("")){

            return "{\"status\":\"nameNull\"}";
        }
        // todo 手机号格式正则检查
        if (user.getPhoneNum() == null || !Check.isPhoneNum(user.getPhoneNum())){

            return "{\"status\":\"phoneNumErr\"}";

        }
        if (user.getPermission() == null){
            return "{\"status\":\"permissionEmpty\"}";
        }
        if (permissionMapper.getPermission(user.getPermission()) == null){
            return "{\"status\":\"permissionNull\"}";
        }

        int userAdd = userMapper.addUser(user);

        if (userAdd != 0){
            return "{\"status\":\"ok\"}";
        }

        return "{\"status\":\"err\"}";

    }

    @RequestMapping("user/delete")
    @ResponseBody
    @PermissionCheck("userDelete")
    public String userDelete(HttpServletRequest request){

        int userDelete = userMapper.deleteUser(Integer.parseInt(request.getParameter("userId")));

        if (userDelete != 0){
            return "{\"status\":\"ok\"}";
        }

        return "{\"status\":\"err\"}";
    }

}
