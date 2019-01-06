package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AliyunOssReference implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer referrerId;

    private Integer referrerType;

    private String ossBucket;

    private String ossKey;

    private Date ct;

    private Date ut;

}