package com.bloom.response;

import java.io.Serializable;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private Object data;
	private Integer total;
	
	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Result() {
		super();
	}

	public static Result success() {
		Result rs = new Result();
		rs.setResultCode(ResultCode.SUCCESS);
		return rs;
	}
	
	public static Result success(Object data) {
		Result rs = success();
		rs.setData(data);
		return rs;
	}
	
	public static Result failure(ResultCode code) {
		Result rs = new Result();
		rs.setResultCode(code);
		return rs;
	}
	
	public static Result failure(ResultCode code,Object data) {
		Result rs = failure(code);
		rs.setData(data);
		return rs;
	}
	
	private void setResultCode(ResultCode code) {
		this.setCode(code.code());
		this.setMsg(code.message());
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	

}
