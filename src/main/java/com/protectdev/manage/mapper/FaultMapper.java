package com.protectdev.manage.mapper;

import com.protectdev.manage.pojo.Fault;

import java.util.List;

public interface FaultMapper {

    Fault getFaultByFaultId(int faultId);

    List<Fault> getFaultList();

}
