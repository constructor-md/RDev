package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.User;

import java.util.Date;
import java.util.List;


public interface UserMapper {


    // todo 新增用户
    int addUser(User user);

    // todo 删除用户
    int deleteUser(int userId);
    // 修改用户信息
    int updateUser(User user);

    //根据工号、姓名、用户名查找
    List<User> getUser(User user);

    User userGetById(int id);


    //根据工号和名字检查用户是否有注册权限
    User idNameCheck(String jobId,String name);
    //检查账号名是否存在
    User usernameCheck(String username);
    //用户注册相关
    int register(String jobId,String name,String username, String password);

    //用户登陆相关
    User login(User user);
    //登陆后写入登陆时间
    int updateLoginTime(User user);

}
