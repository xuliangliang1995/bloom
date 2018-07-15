package com.bloom.dao.po;

import java.io.Serializable;

public class PetalVariety implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String variety;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety == null ? null : variety.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}