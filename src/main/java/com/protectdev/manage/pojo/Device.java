package com.protectdev.manage.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Device {

    private int id;
    private String devId;
    private String devName;
    private String rDevType;
    private String proName;
    private String switchId;
    private String location;
    private String devFac;
    private int softId;
    private int temId;
    private int faultId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JSONField(format="yyyy-MM-dd")
    private Date deadline;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Device{");
        sb.append("id=").append(id);
        sb.append(", devId='").append(devId).append('\'');
        sb.append(", devName='").append(devName).append('\'');
        sb.append(", rDevType='").append(rDevType).append('\'');
        sb.append(", proName='").append(proName).append('\'');
        sb.append(", switchId='").append(switchId).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", devFac='").append(devFac).append('\'');
        sb.append(", softId=").append(softId);
        sb.append(", temId=").append(temId);
        sb.append(", faultId=").append(faultId);
        sb.append(", deadline=").append(deadline);
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getrDevType() {
        return rDevType;
    }

    public void setrDevType(String rDevType) {
        this.rDevType = rDevType;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getSwitchId() {
        return switchId;
    }

    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevFac() {
        return devFac;
    }

    public void setDevFac(String devFac) {
        this.devFac = devFac;
    }

    public int getSoftId() {
        return softId;
    }

    public void setSoftId(int softId) {
        this.softId = softId;
    }

    public int getTemId() {
        return temId;
    }

    public void setTemId(int temId) {
        this.temId = temId;
    }

    public int getFaultId() {
        return faultId;
    }

    public void setFaultId(int faultId) {
        this.faultId = faultId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
