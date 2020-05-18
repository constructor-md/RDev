package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.Field;

import java.util.List;

public interface FieldMapper {


    int addField(Field field);

    int deleteFieldByFieldId(int fieldId);

    List<Field> getField(int temId);

    List<Field> getFieldBysZoneId(int sZoneId);

    Field getFieldByFieldId(int fieldId);


}
