package com.protectdev.manage.pojo;



public class Permission {

    private String permission;

    private Boolean userGet;
    private Boolean userPost;
    private Boolean userAdd;
    private Boolean userDelete;

    private Boolean deviceGet;
    private Boolean devicePost;
    private Boolean deviceAdd;
    private Boolean deviceDelete;

    private Boolean temGet;
    private Boolean temPost;
    private Boolean temAdd;
    private Boolean temDelete;

    private Boolean softGet;
    private Boolean softPost;
    private Boolean softAdd;
    private Boolean softDelete;

    private Boolean faultGet;
    private Boolean faultPost;
    private Boolean faultAdd;
    private Boolean faultDelete;

    private Boolean permissionGet;
    private Boolean permissionPost;
    private Boolean permissionAdd;
    private Boolean permissionDelete;

    private Boolean examine;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Permission{");
        sb.append("permission='").append(permission).append('\'');
        sb.append(", userGet=").append(userGet);
        sb.append(", userPost=").append(userPost);
        sb.append(", userAdd=").append(userAdd);
        sb.append(", userDelete=").append(userDelete);
        sb.append(", deviceGet=").append(deviceGet);
        sb.append(", devicePost=").append(devicePost);
        sb.append(", deviceAdd=").append(deviceAdd);
        sb.append(", deviceDelete=").append(deviceDelete);
        sb.append(", temGet=").append(temGet);
        sb.append(", temPost=").append(temPost);
        sb.append(", temAdd=").append(temAdd);
        sb.append(", temDelete=").append(temDelete);
        sb.append(", softGet=").append(softGet);
        sb.append(", softPost=").append(softPost);
        sb.append(", softAdd=").append(softAdd);
        sb.append(", softDelete=").append(softDelete);
        sb.append(", faultGet=").append(faultGet);
        sb.append(", faultPost=").append(faultPost);
        sb.append(", faultAdd=").append(faultAdd);
        sb.append(", faultDelete=").append(faultDelete);
        sb.append(", permissionGet=").append(permissionGet);
        sb.append(", permissionPost=").append(permissionPost);
        sb.append(", permissionAdd=").append(permissionAdd);
        sb.append(", permissionDelete=").append(permissionDelete);
        sb.append(", examine=").append(examine);
        sb.append('}');
        return sb.toString();
    }

    public String getPermission() {
        return permission;
    }

    public Boolean getExamine() {
        return examine;
    }

    public Boolean getUserGet() {
        return userGet;
    }

    public Boolean getUserPost() {
        return userPost;
    }

    public Boolean getUserAdd() {
        return userAdd;
    }

    public Boolean getUserDelete() {
        return userDelete;
    }

    public Boolean getDeviceGet() {
        return deviceGet;
    }

    public Boolean getDevicePost() {
        return devicePost;
    }

    public Boolean getDeviceAdd() {
        return deviceAdd;
    }

    public Boolean getDeviceDelete() {
        return deviceDelete;
    }

    public Boolean getTemGet() {
        return temGet;
    }

    public Boolean getTemPost() {
        return temPost;
    }

    public Boolean getTemAdd() {
        return temAdd;
    }

    public Boolean getTemDelete() {
        return temDelete;
    }

    public Boolean getSoftGet() {
        return softGet;
    }

    public Boolean getSoftPost() {
        return softPost;
    }

    public Boolean getSoftAdd() {
        return softAdd;
    }

    public Boolean getSoftDelete() {
        return softDelete;
    }

    public Boolean getFaultGet() {
        return faultGet;
    }

    public Boolean getFaultPost() {
        return faultPost;
    }

    public Boolean getFaultAdd() {
        return faultAdd;
    }

    public Boolean getFaultDelete() {
        return faultDelete;
    }

    public Boolean getPermissionGet() {
        return permissionGet;
    }

    public Boolean getPermissionPost() {
        return permissionPost;
    }

    public Boolean getPermissionAdd() {
        return permissionAdd;
    }

    public Boolean getPermissionDelete() {
        return permissionDelete;
    }
}
