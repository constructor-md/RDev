package com.protectdev.manage.controller;


import com.alibaba.fastjson.JSONObject;
import com.protectdev.manage.annotation.PermissionCheck;
import com.protectdev.manage.mapper.DeviceMapper;
import com.protectdev.manage.mapper.FieldMapper;
import com.protectdev.manage.mapper.SZoneMapper;
import com.protectdev.manage.mapper.TemplateMapper;
import com.protectdev.manage.pojo.Field;
import com.protectdev.manage.pojo.SZone;
import com.protectdev.manage.pojo.Tem;
import com.protectdev.manage.pojo.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    /**
     *
     * @param request
     * @return
     *
     * 根据传来的参数，如果包含模板名，则新建模板。或者包含模板Id
     * 根据定值块名找到模板Id和定值块名相等的定值块，如果存在则不新建定值块，如果不存在则新建定值块返回主键
     * 根据定值字段名找到模板Id和定值块Id和字段名相同的字段，没有则新建，有则返回重复
     *
     */
    @RequestMapping("tem/add")
    @ResponseBody
    @PermissionCheck("temAdd")
    public String temAdd(HttpServletRequest request) {

        if (request.getParameter("sZoneName") == null || request.getParameter("sZoneName").equals("")){
           return "{\"status\":\"sZoneNameNull\"}";

        }
        if (request.getParameter("fieldName") == null || request.getParameter("fieldName").equals("")){
            return "{\"status\":\"fieldNameNull\"}";
        }
        if (request.getParameter("temName").equals("")){
            return "{\"status\":\"temNameNull\"}";
        }

        //检查了有模板的，有无定值块两种情况
        if (request.getParameter("temName") == null){

            //如果模板名为空，检查定值块名和模板Id，如果不存在，新建定值块，新建定值字段

            if (sZoneMapper.checkSZone(Integer.parseInt(request.getParameter("temId")),
                                            request.getParameter("sZoneName")) == null){

                SZone sZone = new SZone();
                sZone.setsZoneName(request.getParameter("sZoneName"));
                sZone.setTemId(Integer.parseInt(request.getParameter("temId")));
                int addSZone = sZoneMapper.addSZone(sZone);

                if (addSZone == 0){
                    return "{\"status\":\"err\"}";
                }

                Field field = new Field();
                field.setFieldName(request.getParameter("fieldName"));
                field.setsZoneId(sZone.getId());
                field.setTemId(Integer.parseInt(request.getParameter("temId")));

                int addField = fieldMapper.addField(field);

                if (addField == 0){
                    return "{\"status\":\"err\"}";
                }

                return "{\"status\":\"ok\"}";

            }

            //如果模板存在，sZone存在，新建定值字段

            SZone sZone = sZoneMapper.checkSZone(Integer.parseInt(request.getParameter("temId")),
                    request.getParameter("sZoneName"));
            Field field = new Field();
            field.setTemId(sZone.getTemId());
            field.setsZoneId(sZone.getId());
            field.setFieldName(request.getParameter("fieldName"));

            int addField = fieldMapper.addField(field);

            if (addField == 0){
                return "{\"status\":\"err\"}";
            }
            return "{\"status\":\"ok\"}";

        }

        //没有模板的情况

        Template template = new Template();

        template.setDevId(Integer.parseInt(request.getParameter("devId")));
        template.setTemName(request.getParameter("temName"));

        int addTemplate = templateMapper.addTemplate(Integer.parseInt(request.getParameter("devId")),request.getParameter("temName"));

        SZone sZone = new SZone();

        sZone.setTemId(template.getId());
        sZone.setsZoneName(request.getParameter("sZoneName"));

        int addSZone = sZoneMapper.addSZone(new SZone());

        Field field = new Field();

        field.setFieldName(request.getParameter("fieldName"));
        field.setsZoneId(sZone.getId());
        field.setTemId(template.getId());

        int addField = fieldMapper.addField(field);

        if (addField == 0){
            return "{\"status\":\"err\"}";
        }


        return "{\"status\":\"ok\"}";
    }




    @RequestMapping("tem/delete")
    @ResponseBody
    @PermissionCheck("temDelete")
    public String temDelete(HttpServletRequest request) {


        //获取对应的模板和定值块
        int fieldId = Integer.parseInt(request.getParameter("fieldId"));
        int sZoneId = fieldMapper.getFieldByFieldId(fieldId).getsZoneId();
        int temId = fieldMapper.getFieldByFieldId(fieldId).getTemId();
       int deleteField = fieldMapper.deleteFieldByFieldId(fieldId);

       if (deleteField == 0){
           return "{\"status\":\"err\"}";
       }

       //检查还有没有该定值块和模板,如果该定值字段ID对应的模板和定值块在字段表中不存在了，就将两者从其表中删除

        if (fieldMapper.getFieldBysZoneId(sZoneId) == null){

            sZoneMapper.deleteSZone(sZoneId);

        }
        if (fieldMapper.getField(temId) == null){

            templateMapper.deleteTemplate(temId);

            deviceMapper.updateDeviceTemId(Integer.parseInt(request.getParameter("devId")),0);


        }

        return "{\"status\":\"ok\"}";

    }





    @RequestMapping("tem/get")
    @ResponseBody
    @PermissionCheck("temGet")
    public String temGet(HttpServletRequest request) {


        Template template = templateMapper.getTemplate(Integer.parseInt(request.getParameter("temId")));

        List<Field> fields = fieldMapper.getField(template.getId());

        List<Tem> temList = Collections.synchronizedList(new ArrayList<>());


        for (Field field : fields){

            Tem tem = new Tem();

            tem.setFieldId(field.getId());
            tem.setFieldName(field.getFieldName());
            tem.setsZoneName(sZoneMapper.getSZone(field.getId()).getsZoneName());
            tem.setTemName(templateMapper.getTemplate(field.getTemId()).getTemName());

            temList.add(tem);

        }

        return JSONObject.toJSONString(temList);
    }



}
