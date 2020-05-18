package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.SZone;

public interface SZoneMapper {


    int addSZone(SZone sZone);


    int deleteSZone(int sZoneId);

    SZone checkSZone(int temId,String sZoneName);

    SZone getSZone(int fieldId);



}
