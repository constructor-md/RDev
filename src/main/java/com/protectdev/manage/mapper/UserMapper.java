package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.User;


public interface UserMapper {

    //用户注册相关
    User idNameCheck(User user);
    User usernameCheck(User user);
    int register(User user);

    //用户登陆相关
    User login(User user);
    int updateLoginTime(User user);

}
