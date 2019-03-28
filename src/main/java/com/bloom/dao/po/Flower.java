package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Flower implements Serializable{

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
    
}