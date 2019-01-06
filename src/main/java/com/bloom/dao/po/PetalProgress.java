package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PetalProgress implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer petalId;

    private Integer gardenerId;

    private Integer retentionCurveId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date fireTime;

    private Byte fire;
    
    private Date ct;
    
}