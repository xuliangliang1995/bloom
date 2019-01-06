package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PetalInnerText implements Serializable{

	private Integer id;

    private Integer petalId;

    private Date ct;

    private Date ut;

}