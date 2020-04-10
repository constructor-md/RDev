package com.protectdev.manage.controller;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.PermissionMapper;
import com.protectdev.manage.pojo.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * 接收来自权限管理相关的请求
 *
 */
@Controller
public class PermissionController {

    @Resource
    private PermissionMapper permissionMapper;


    /**
     *
     * 查询全部权限信息并返回
     * @return
     */
    @RequestMapping("permission/get")
    @ResponseBody
    @PermissionCheck(value = "permissionGet")
    public String permissionGet(){

        //根据角色名查找角色权限,单个数据
//        String permissionName = request.getParameter("permission");
//        Permission permission = permissionMapper.getPermission(permissionName);

        //返回全部角色信息
        List<Permission> permissionList = permissionMapper.getPermissionList();

        //JSONObject jsonObject = (JSONObject) JSONObject.toJSON(permissionList);报错不能强转，解决办法是直接使用转回的结果

        String permissionListJson = JSONObject.toJSON(permissionList).toString();

        return permissionListJson;
    }

    /**
     *
     *
     * @param permission
     * @return
     *
     * 使用@RequestBody注解将接收的JSON字符串转为Java对象
     */
    @RequestMapping("permission/post")
    @ResponseBody
    @PermissionCheck(value = "permissionPost")
    public String permissionPost(@RequestBody Permission permission){

        //根据发送过来的JSON数据转换为一个Permission对象

        int updatePermission = permissionMapper.updatePermission(permission);

        if (updatePermission != 0){

        //查询修改后的信息并返回
        permission = permissionMapper.getPermission(permission.getPermission());

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(permission);
        String permissionJson = jsonObject.toJSONString();

        return  "角色信息修改成功:"+"/t"+permissionJson;
        }

        return "角色信息修改失败";
    }

    @RequestMapping("permission/add")
    @ResponseBody
    @PermissionCheck("permissionAdd")
    public String permissionAdd(@RequestBody Permission permission){

        //前端负责将权限默认为false，对象新增是提供默认值 todo 考虑使用URL直接传回参数的情况，前端不能直接把值设置为false
        if (permission.getPermission() == null || permission.getPermission().equals("")){
            return "角色名不可为空";
        }
        if (permissionMapper.getPermission(permission.getPermission()) != null){
            return "该角色名字重复";
        }

        int addPermission = permissionMapper.addPermission(permission);

        // todo 新增后返回新的列表
        if (addPermission != 0){
            return "新增角色权限成功";
        }

        return "新增角色权限失败";

    }

    @RequestMapping("permission/delete")
    @ResponseBody
    @PermissionCheck("permissionDelete")
    public String permissionDelete(@RequestBody Permission permission){

        Permission exist = permissionMapper.getPermission(permission.getPermission());

        if (exist == null){
            return "该角色不存在";
        }

        int permissionDelete = permissionMapper.deletePermission(permission.getPermission());

        // todo 删除后返回新的列表
        if (permissionDelete != 0){
            return "成功删除该角色";
        }

        return "删除该角色失败";

    }


}
