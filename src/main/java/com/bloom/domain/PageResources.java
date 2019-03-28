package com.bloom.domain;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PageResources<T> extends Resources<T> {
	private Long total;
	
	public PageResources(Iterable<T> content, Link... links) {
		super(content,links);
	}
	public PageResources<T> withTotal(Long total){
		this.total = total;
		return this;
	}
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	

}
