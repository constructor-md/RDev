package com.protectdev.manage.pojo;

import java.util.Date;

public class Fault {

    private int faultId;
    private String faultDesc;
    private int devId;
    private int upUserId;
    private Date upTime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fault{");
        sb.append("faultId=").append(faultId);
        sb.append(", faultDesc='").append(faultDesc).append('\'');
        sb.append(", devId=").append(devId);
        sb.append(", upUserId=").append(upUserId);
        sb.append(", upTime=").append(upTime);
        sb.append('}');
        return sb.toString();
    }

    public int getFaultId() {
        return faultId;
    }

    public void setFaultId(int faultId) {
        this.faultId = faultId;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public int getUpUserId() {
        return upUserId;
    }

    public void setUpUserId(int upUserId) {
        this.upUserId = upUserId;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}
