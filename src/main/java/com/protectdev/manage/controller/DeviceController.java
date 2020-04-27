package com.protectdev.manage.controller;


import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import com.protectdev.manage.mapper.FaultMapper;
import com.protectdev.manage.mapper.SoftwareMapper;
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

    @Resource
    private SoftwareMapper softwareMapper;

    @Resource
    private FaultMapper faultMapper;


    /**
     * @return
     *
     *
     */
    @RequestMapping("device/get")
    @ResponseBody
    @PermissionCheck(value = "deviceGet")
    public String deviceGet(@RequestBody Device device){

        System.out.println(device.toString());

        List<Device> devices = deviceMapper.getDevice(device);

        String deviceJson = JSONObject.toJSON(devices).toString();

        return deviceJson;
    }

    @RequestMapping("deadlineDevice/get")
    @ResponseBody
    @PermissionCheck(value = "deviceGet")
    public String deadlineDeviceGet(){

        List<Device> devices = deviceMapper.getDeadlineDevice();


        return JSONObject.toJSONString(devices);
    }


    @RequestMapping("device/post")
    @ResponseBody
    @PermissionCheck("devicePost")
    public String devicePost(@RequestBody Device device){

        System.out.println(device.toString());

        //用户输入主键不可为空
        if (device.getId() == 0){
            return "{\"status\":\"idNull\"}";
        }



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

        //用户输入主键不可为空
        if (device.getId() == 0){
            return "{\"status\":\"idNull\"}";
        }

        //用户输入主键不可重复
        if (deviceMapper.getDeviceById(device.getId()) != null){
            return "{\"status\":\"idExist\"}";
        }


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

        Device device = deviceMapper.getDeviceById(id);

        int deviceFaultDelete = 0;
        int deviceSoftDelete = 0;

        if (device.getSoftId() != 0){
            deviceSoftDelete = softwareMapper.deleteSoft(device.getSoftId());
        }

        if (device.getFaultId() != 0){
            deviceFaultDelete = faultMapper.deleteFault(device.getFaultId());
        }



        int deviceDelete = deviceMapper.deleteDeviceById(id);

        //todo 应该将模板、软件、设备、故障信息一起删除

        if ((deviceDelete+deviceSoftDelete+deviceFaultDelete) == 3 ){

            return "{\"status\":\"ok\"}";
        }

        return "{\"status\":\"err\"}";

    }

}
