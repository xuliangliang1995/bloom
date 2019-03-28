package com.bloom.dao.po;

public class PetalInnerTextWithBLOBs extends PetalInnerText {
    private String raw;

    private String text;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw == null ? null : raw.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}