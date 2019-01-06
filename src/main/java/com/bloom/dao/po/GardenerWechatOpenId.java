package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class GardenerWechatOpenId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer gardenerId;

    private String appId;

    private String openId;

    private Date ct;
    
}