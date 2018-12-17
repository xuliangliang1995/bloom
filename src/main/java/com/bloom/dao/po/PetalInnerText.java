package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

public class PetalInnerText implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer petalId;

    private Date ct;

    private Date ut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetalId() {
        return petalId;
    }

    public void setPetalId(Integer petalId) {
        this.petalId = petalId;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public Date getUt() {
        return ut;
    }

    public void setUt(Date ut) {
        this.ut = ut;
    }
}