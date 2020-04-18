package com.protectdev.manage.controller;


import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import com.protectdev.manage.pojo.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 *
 * 用于处理来自登录信息页面的请求
 *
 *
 */
@Controller
public class DeviceController {

    @Resource
    private DeviceMapper deviceMapper;


    /**
     * @return
     *
     *
     */
    @RequestMapping("device/get")
    @ResponseBody
    @PermissionCheck(value = "deviceGet")
    public String deviceGet(@RequestBody Device device){

        List<Device> devices = deviceMapper.getDevice(device);

        String deviceJson = JSONObject.toJSON(devices).toString();

        return deviceJson;
    }


    @RequestMapping("device/post")
    @ResponseBody
    @PermissionCheck("devicePost")
    public String devicePost(@RequestBody Device device){

        System.out.println(device.toString());

        //为了保证数据库更新语句正确执行，设备编号不能为空
        if (device.getDevId() == null){
            return "{\"status\":\"devIdNull\"}";
        }

        int updateDevice = deviceMapper.updateDevice(device);

        if (updateDevice != 0){

            return "{\"status\":\"ok\"}";
        }

        return "{\"status\":\"err\"}";
    }

    @RequestMapping("device/add")
    @ResponseBody
    @PermissionCheck("deviceAdd")
    public String deviceAdd(@RequestBody Device device){

        if (device.getDevId() == null || device.getDevId().equals("")){
            return "{\"status\":\"devIdNull\"}";
        }
        if (device.getDevName() == null || device.getDevName().equals("")){
            return "{\"status\":\"devNameNull\"}";
        }
        if (device.getLocation() == null || device.getLocation().equals("")){
            return "{\"status\":\"locationNull\"}";
        }
        if (device.getDeadline() == null){
            return "{\"status\":\"deadlineNull\"}";
        }


        int addDevice = deviceMapper.addDevice(device);

        if (addDevice != 0){

            return "{\"status\":\"ok\"}";
        }


        return "{\"status\":\"err\"}";
    }

    @RequestMapping("device/delete")
    @ResponseBody
    @PermissionCheck("deviceDelete")
    public String deviceDelete(HttpServletRequest request){

        int id = Integer.parseInt(request.getParameter("id"));

        int deviceDelete = deviceMapper.deleteDeviceById(id);

        //todo 应该将模板、软件、设备、故障信息一起删除

        if (deviceDelete != 0){

            return "{\"status\":\"ok\"}";
        }

        return "{\"status\":\"err\"}";

    }

}
