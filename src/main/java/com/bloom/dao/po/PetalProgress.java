package com.bloom.dao.po;

import java.util.Date;

public class PetalProgress {
    private Long id;

    private Integer petalId;

    private Integer gardenerId;

    private Integer retentionCurveId;

    private Date fireTime;

    private Date ct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPetalId() {
        return petalId;
    }

    public void setPetalId(Integer petalId) {
        this.petalId = petalId;
    }

    public Integer getGardenerId() {
        return gardenerId;
    }

    public void setGardenerId(Integer gardenerId) {
        this.gardenerId = gardenerId;
    }

    public Integer getRetentionCurveId() {
        return retentionCurveId;
    }

    public void setRetentionCurveId(Integer retentionCurveId) {
        this.retentionCurveId = retentionCurveId;
    }

    public Date getFireTime() {
        return fireTime;
    }

    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}