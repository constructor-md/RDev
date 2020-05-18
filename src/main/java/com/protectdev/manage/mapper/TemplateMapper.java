package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.Template;

public interface TemplateMapper {


    int addTemplate(int devId,String temName);

    int deleteTemplate(int temId);

    Template getTemplate(int temId);

    Template getTemplateByTemName(String temName);



}
