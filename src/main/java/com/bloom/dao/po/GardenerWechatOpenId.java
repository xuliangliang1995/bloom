package com.bloom.dao.po;

import java.util.Date;

public class GardenerWechatOpenId {
    private Integer id;

    private Integer gardenerId;

    private String appId;

    private String openId;

    private Date ct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGardenerId() {
        return gardenerId;
    }

    public void setGardenerId(Integer gardenerId) {
        this.gardenerId = gardenerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}