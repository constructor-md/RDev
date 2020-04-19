package com.protectdev.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import com.protectdev.manage.mapper.SoftwareMapper;
import com.protectdev.manage.pojo.Software;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SoftwareController {

    @Resource
    private SoftwareMapper softwareMapper;

    @Resource
    private DeviceMapper deviceMapper;

    @RequestMapping("soft/get")
    @ResponseBody
    @PermissionCheck("softGet")
    public String softGet(HttpServletRequest request){

        Software software = softwareMapper.getSoftById(Integer.parseInt(request.getParameter("softId")));

        String softwareJson = JSONObject.toJSONString(software);

        return softwareJson;
    }

    @RequestMapping("soft/post")
    @ResponseBody
    @PermissionCheck("softPost")
    public String softPost(@RequestBody Software software){

        if (software.getSoftFac() == null || software.getSoftFac().equals("")){

            return "{\"status\":\"softFacEmpty\"}";

        }

        if (software.getSoftName() == null || software.getSoftName().equals("")){

            return "{\"status\":\"softNameNull\"}";

        }

        if (software.getSoftVer() == null || software.getSoftVer().equals("")){

            return "{\"status\":\"softVerNull\"}";

        }


        int updateSoft = softwareMapper.updateSoft(software);

        if (updateSoft == 0){

            return "{\"status\":\"err\"}";

        }


        return "{\"status\":\"ok\"}";
    }


    @RequestMapping("soft/add")
    @ResponseBody
    @PermissionCheck("softAdd")
    public String softAdd(@RequestBody Software software){


        if (software.getSoftFac() == null || software.getSoftFac().equals("")){

            return "{\"status\":\"softFacEmpty\"}";

        }

        if (software.getSoftName() == null || software.getSoftName().equals("")){

            return "{\"status\":\"softNameNull\"}";

        }

        if (software.getSoftVer() == null || software.getSoftVer().equals("")){

            return "{\"status\":\"softVerNull\"}";

        }


        int softAdd = softwareMapper.addSoft(software);

        System.out.println(software.getId());

        if (softAdd != 0){

            // 新增软件信息成功后取出该软件信息的主键id，加到devId对应的设备的softId中

            deviceMapper.updateDeviceSoftId(software.getDevId(),software.getId());


            return "{\"status\":\"ok\"}";

        }


        return "{\"status\":\"err\"}";
    }


    @RequestMapping("soft/delete")
    @ResponseBody
    @PermissionCheck("softDelete")
    public String softDelete(@RequestBody Software software){

       int softDelete = softwareMapper.deleteSoft(software.getId());

       if(softDelete == 0){
           return "{\"status\":\"err\"}";
       }

       //将对应设备中的softId置为0
       deviceMapper.updateDeviceSoftId(software.getDevId(),0);

            return "{\"status\":\"ok\"}";
    }


}
