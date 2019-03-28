package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PetalInnerLink implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer petalId;

    private String link;

    private Date ct;

    private Date ut;
}