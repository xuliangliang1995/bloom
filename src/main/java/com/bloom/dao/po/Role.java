package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
    
    private String name;

    private Boolean ordinary;

    private Date ct;

}