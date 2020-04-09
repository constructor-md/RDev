package com.protectdev.manage.mapper;


import com.protectdev.manage.pojo.Device;

import java.util.List;

public interface DeviceMapper {

    /**
     *
     * @param devId
     * @return
     *
     * 设备查询功能：联合/单项查询
     * 1.根据设备ID定位一组设备
     * 2.根据设备位置模糊定位一组设备
     * 3.根据设备名、设备位置定位一组设备
     *
     *
     */

    Device getDeviceByDevId(String devId);
    List<Device> getDeviceByLocation(String location);

    //修改


    //增加


    //删除




}
