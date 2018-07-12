package com.bloom.web.petal.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.PetalVariety;
import com.bloom.web.petal.PetalVarietyResourceApi;

public class PetalVarietyResourceAssembler extends ResourceAssemblerSupport<PetalVariety, PetalVarietyResource> {

	public PetalVarietyResourceAssembler() {
		super(PetalVarietyResourceApi.class, PetalVarietyResource.class);
	}

	@Override
	public PetalVarietyResource toResource(PetalVariety variety) {
		return createResourceWithId(variety.getId(), variety);
	}

	@Override
	protected PetalVarietyResource instantiateResource(PetalVariety variety) {
		return new PetalVarietyResource(variety);
	}

	
}
