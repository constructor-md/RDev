package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.Software;

public interface SoftwareMapper {

    Software getSoftById(int id);

    int updateSoft(Software software);

    int addSoft(Software software);

    int deleteSoft(int softId);


}
