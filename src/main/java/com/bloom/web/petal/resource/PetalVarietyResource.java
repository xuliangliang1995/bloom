package com.bloom.web.petal.resource;

import org.springframework.hateoas.Resource;

import com.bloom.dao.po.PetalVariety;

public class PetalVarietyResource extends Resource<PetalVariety> {

	PetalVarietyResource(PetalVariety variety) {
		super(variety);
	}

}
