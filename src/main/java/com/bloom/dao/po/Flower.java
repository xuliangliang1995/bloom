package com.bloom.dao.po;

import java.util.Date;

public class Flower {
    private Integer id;

    private Integer gardenerId;

    private String name;

    private String moral;

    private Byte star;

    private Date ct;

    private Date ut;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMoral() {
        return moral;
    }

    public void setMoral(String moral) {
        this.moral = moral == null ? null : moral.trim();
    }

    public Byte getStar() {
        return star;
    }

    public void setStar(Byte star) {
        this.star = star;
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