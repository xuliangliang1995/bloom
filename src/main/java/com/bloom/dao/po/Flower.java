package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Flower implements Identifiable<Integer>,Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer gardenerId;

    private String name;

    private String moral;

    private Byte star;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date ct;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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