package com.bloom.web.petal.resource;

import org.springframework.hateoas.Resource;

import com.bloom.dao.po.Petal;

public class PetalResource extends Resource<Petal> {

	PetalResource(Petal petal) {
		super(petal);
	}

}
