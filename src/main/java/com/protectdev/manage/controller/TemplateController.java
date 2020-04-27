package com.protectdev.manage.controller;


import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import com.protectdev.manage.mapper.FieldMapper;
import com.protectdev.manage.mapper.SZoneMapper;
import com.protectdev.manage.mapper.TemplateMapper;
import com.protectdev.manage.pojo.Field;
import com.protectdev.manage.pojo.SZone;
import com.protectdev.manage.pojo.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 * 用于处理来自模板信息页面的请求
 *
 */
@Controller
public class TemplateController {

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private TemplateMapper templateMapper;

    @Resource
    private SZoneMapper sZoneMapper;

    @Resource
    private FieldMapper fieldMapper;


    @RequestMapping("tem/add")
    @ResponseBody
    @PermissionCheck("temAdd")
    public String temAdd(@RequestBody Template template){


        int addTemplate = templateMapper.addTemplate(template);

        if (addTemplate != 0){
            return "ok";
        }


        return "";
    }

    @RequestMapping("sZone/add")
    @ResponseBody
    @PermissionCheck("temAdd")
    public String sZoneAdd(@RequestBody SZone sZone){


        int addSZone = sZoneMapper.addSZone(sZone);

        if (addSZone != 0){
            return "ok";
        }


        return "";
    }


    @RequestMapping("field/add")
    @ResponseBody
    @PermissionCheck("temAdd")
    public String fieldAdd(@RequestBody Field field){


        int addField = fieldMapper.addField(field);

        if (addField != 0){
            return "ok";
        }


        return "";
    }


}
