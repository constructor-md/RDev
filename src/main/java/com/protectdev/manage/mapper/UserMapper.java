package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.User;


public interface UserMapper {


    // todo 新增用户

    // todo 删除用户

    //根据工号和名字检查用户是否有注册权限
    User idNameCheck(String jobId,String name);
    //检查账号名是否存在
    User usernameCheck(String username);
    //用户注册相关
    int register(User user);

    //用户登陆相关
    User login(User user);
    //登陆后写入登陆时间
    int updateLoginTime(User user);

}
