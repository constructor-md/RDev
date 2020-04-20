package com.protectdev.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import com.protectdev.manage.mapper.FaultMapper;
import com.protectdev.manage.mapper.UserMapper;
import com.protectdev.manage.pojo.Fault;
import com.protectdev.manage.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FaultController {


    @Resource
    private FaultMapper faultMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DeviceMapper deviceMapper;


    @RequestMapping("fault/get")
    @ResponseBody
    @PermissionCheck("faultGet")
    public StringBuilder faultGet(HttpServletRequest request){

        //组合数据拼串 故障信息和上传用户信息
        Fault fault = faultMapper.getFaultByFaultId(Integer.parseInt(request.getParameter("faultId")));

        User user = userMapper.userGetById(fault.getUpUserId());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(JSONObject.toJSONString(fault).replace("}","")).append(",")
                .append(JSONObject.toJSONString(user).replace("{",""));

        return stringBuilder;

    }

    @RequestMapping("fault/post")
    @ResponseBody
    @PermissionCheck("faultPost")
    public String faultPost(HttpServletRequest request){


        if (request.getParameter("faultDesc") == null || request.getParameter("faultDesc").equals("")){

            return  "{\"status\":\"faultDescNull\"}";

        }
        //更新时将修改人信息一并更新
        int updateFault = faultMapper.updateFault(request.getParameter("faultDesc"),
                                                  userMapper.usernameCheck(request.getParameter("username")).getUserId(),
                                                    Integer.parseInt(request.getParameter("faultId")));

        if (updateFault == 0){
            return  "{\"status\":\"err\"}";
        }


        return  "{\"status\":\"ok\"}";

    }








    @RequestMapping("fault/add")
    @ResponseBody
    @PermissionCheck("faultAdd")
    public String faultAdd(HttpServletRequest request){

        if (request.getParameter("faultDesc") == null || request.getParameter("faultDesc").equals("")){
            return  "{\"status\":\"faultDescNull\"}";
        }

        //插入的同时要填写用户信息
        Fault fault = new Fault();
        fault.setDevId(Integer.parseInt(request.getParameter("devId")));
        fault.setFaultDesc(request.getParameter("faultDesc"));
        //获取用户Id填入故障上报表,上报时间在数据库中填写
        fault.setUpUserId(userMapper.usernameCheck(request.getParameter("username")).getUserId());

        int addFault = faultMapper.addFault(fault);

        if (addFault == 0){

            return  "{\"status\":\"err\"}";

        }

        //将新的主键填写到对应设备中

        deviceMapper.updateDeviceFaultId(fault.getDevId(),fault.getFaultId());


        return  "{\"status\":\"ok\"}";
    }


    @RequestMapping("fault/delete")
    @ResponseBody
    @PermissionCheck("faultDelete")
    public String faultDelete(HttpServletRequest request){

        //删除数据库中该fault，并将设备表中faultId职位0

        int deleteFault = faultMapper.deleteFault(Integer.parseInt(request.getParameter("faultId")));

        if (deleteFault == 0){
            return  "{\"status\":\"err\"}";
        }

        deviceMapper.updateDeviceFaultId(Integer.parseInt(request.getParameter("devId")),
                                        Integer.parseInt(request.getParameter("faultId")));


        return  "{\"status\":\"ok\"}";
    }





}
