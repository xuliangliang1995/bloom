package com.bloom.dao.po;

import java.util.Date;

public class Role {
    private Integer id;
    
    private String name;

    private Boolean ordinary;

    private Date ct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getOrdinary() {
        return ordinary;
    }

    public void setOrdinary(Boolean ordinary) {
        this.ordinary = ordinary;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}