package com.protectdev.manage.mapper;


import com.protectdev.manage.pojo.Permission;

import java.util.List;


public interface PermissionMapper {

    //根据角色查询对应角色权限、检查权限名是否重复
    Permission getPermission(String permission);

    //查询数据库中所有角色及其对应权限
    List<Permission> getPermissionList();

    //修改权限
    int updatePermission(Permission permission);

    //新增权限
    int addPermission(Permission permission);

    //删除角色
    int deletePermission(String permission);
}
