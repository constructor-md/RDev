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
     *
     * @param request
     * @return
     *
     *
     */
    @RequestMapping("device/get")
    @ResponseBody
    @PermissionCheck(value = "deviceGet")
    public String deviceGet(HttpServletRequest request){

        //根据devId/name/location查询一组设备
        Device device = new Device();
        device.setDevId(request.getParameter("devId"));
        device.setLocation(request.getParameter("location"));
        device.setDevName(request.getParameter("devName"));

        int page = Integer.parseInt(request.getParameter("page"));


        List<Device> devices = deviceMapper.getDevice(device,page);

        if (devices.isEmpty()){
            return "找不到设备";
        }

        String deviceJson = JSONObject.toJSON(devices).toString();

        return deviceJson;
    }


    @RequestMapping("device/post")
    @ResponseBody
    @PermissionCheck("devicePost")
    public String devicePost(@RequestBody Device device){

        int updateDevice = deviceMapper.updateDevice(device);

        if (updateDevice != 0){

            Device devicePost = deviceMapper.getDeviceById(device.getId());

            String deviceJson = JSONObject.toJSON(devicePost).toString();

            return "信息修改成功:"+deviceJson;

        }
        Device devicePost = deviceMapper.getDeviceById(device.getId());

        String deviceJson = JSONObject.toJSON(devicePost).toString();
        return "信息修改失败:" + deviceJson;

    }

    @RequestMapping("device/add")
    @ResponseBody
    @PermissionCheck("deviceAdd")
    public String deviceAdd(@RequestBody Device device){

        if (device.getDevId() == null || device.getDevId().equals("")){
            return "设备编号不可为空";
        }
        if (device.getDevName() == null || device.getDevName().equals("")){
            return "设备名不可为空";
        }
        if (device.getLocation() == null || device.getLocation().equals("")){
            return "设备地址描述不可为空";
        }
        if (device.getDeadline() == null){
            return "设备使用期限不可为空";
        }


        int addDevice = deviceMapper.addDevice(device);

        if (addDevice == 0){

            return "设备信息添加成功";
        }


        return "设备信息新增失败";
    }

    @RequestMapping("device/delete")
    @ResponseBody
    @PermissionCheck("deviceDelete")
    public String deviceDelete(HttpServletRequest request){

        int id = Integer.parseInt(request.getParameter("id"));

        int deviceDelete = deviceMapper.deleteDeviceById(id);

        //todo 应该将模板、软件、设备、故障信息一起删除

        if (deviceDelete != 0){


            return "设备信息删除成功";
        }

        return "设备信息删除成功";

    }

}
