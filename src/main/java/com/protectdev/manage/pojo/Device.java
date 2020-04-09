package com.protectdev.manage.pojo;


import java.util.Date;

public class Device {

    private int id;
    private String devId;
    private String devName;
    private String RDevType;
    private String proName;
    private String switchId;
    private String location;
    private String devFac;
    private int softId;
    private int temId;
    private int faultId;
    private Date deadline;

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

    public String getRDevType() {
        return RDevType;
    }

    public void setRDevType(String RDevType) {
        this.RDevType = RDevType;
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
