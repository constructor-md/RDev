package com.protectdev.manage.mapper;


import com.protectdev.manage.pojo.Device;

import java.util.List;

public interface DeviceMapper {

    /*
     *
     * 设备查询功能：联合/单项查询
     * 1.根据设备ID定位一台设备
     * 2.根据设备位置模糊定位一组设备
     * 3.根据设备名、设备位置定位一组设备
     *
     */

    Device getDeviceById(int Id);
    List<Device> getDevice(Device device,int page);

    /* 前端将修改过后的完整设备对象传回，包含设备主键ID
     * 根据该ID唯一定位一个设备，赋值修改
     *
     */
    int updateDevice(Device device);

    /*
     * 前端将完整的设备对象传回，空值数据库中默认修改和程序中默认修改
     *
     */
    int addDevice(Device device);

    /*
     * 前端将完整的对象信息传回，根据Device对象的ID准确删除该设备
     *
     */
    int deleteDeviceById(int id);



}
