package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RoleMenu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Date ct;

}