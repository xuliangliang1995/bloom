package com.bloom.dao.po;

import java.util.Date;

public class RetentionCurve {
    private Integer id;

    private Long intervalMinutes;

    private Boolean enabled;

    private String note;

    private Date ct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIntervalMinutes() {
        return intervalMinutes;
    }

    public void setIntervalMinutes(Long intervalMinutes) {
        this.intervalMinutes = intervalMinutes;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }
}