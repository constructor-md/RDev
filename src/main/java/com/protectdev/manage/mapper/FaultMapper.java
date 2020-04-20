package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.Fault;

import java.util.List;

public interface FaultMapper {




    Fault getFaultByFaultId(int faultId);

    int updateFault(String faultDesc,int upUserId,int faultId);

    int addFault(Fault fault);

    int deleteFault(int faultId);


    List<Fault> getFaultList();

}
