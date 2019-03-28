package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer parentId;

    private String name;

    private String icon;

    private String target;

    private Date ct;

}