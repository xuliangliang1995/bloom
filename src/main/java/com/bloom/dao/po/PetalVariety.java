package com.bloom.dao.po;

import java.io.Serializable;

import lombok.Data;

@Data
public class PetalVariety implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private String variety;

    private String note;

}