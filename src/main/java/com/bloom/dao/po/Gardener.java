package com.bloom.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class Gardener implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer roleId;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    private String nickName;

    private String gender;

    private Date birthday;

    private String email;

    private Date ct;

    private Date ut;

}