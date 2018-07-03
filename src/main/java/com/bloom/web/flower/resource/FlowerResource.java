package com.bloom.web.flower.resource;

import org.springframework.hateoas.Resource;

import com.bloom.dao.po.Flower;

public class FlowerResource extends Resource<Flower>{

	public FlowerResource(Flower flower) {
		super(flower);
		// TODO Auto-generated constructor stub
	}

}
