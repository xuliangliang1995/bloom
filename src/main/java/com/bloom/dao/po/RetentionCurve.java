package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RetentionCurve implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Long intervalMinutes;

    private Boolean enabled;

    private String note;

    private Date ct;

}