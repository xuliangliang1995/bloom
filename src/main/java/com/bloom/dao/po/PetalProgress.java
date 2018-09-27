package com.bloom.dao.po;

import java.util.Date;

public class PetalProgress {
    private Long id;

    private Integer petalId;

    private Integer gardenerId;

    private Integer retentionCurveId;

    private Date fireTime;

    private Byte fire;

    private Date ct;
    
    public static enum FireStatus{
    	NO_FIRE((byte)0),
    	FIRE((byte)1);
    	private Byte status;
		private FireStatus(Byte status) {
			this.status = status;
		}
		public byte status() {
			return status;
		}
    }

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

    public Byte getFire() {
        return fire;
    }

    public void setFire(Byte fire) {
        this.fire = fire;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}