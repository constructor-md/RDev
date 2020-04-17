package com.protectdev.manage.pojo;

public class Software {

    private int id;
    private String softId;
    private String softName;
    private String softFac;
    private String softVer;
    private int devId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Software{");
        sb.append("id=").append(id);
        sb.append(", softId='").append(softId).append('\'');
        sb.append(", softName='").append(softName).append('\'');
        sb.append(", softFac='").append(softFac).append('\'');
        sb.append(", softVer='").append(softVer).append('\'');
        sb.append(", devId=").append(devId);
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoftId() {
        return softId;
    }

    public void setSoftId(String softId) {
        this.softId = softId;
    }

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getSoftFac() {
        return softFac;
    }

    public void setSoftFac(String softFac) {
        this.softFac = softFac;
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer;
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }
}
