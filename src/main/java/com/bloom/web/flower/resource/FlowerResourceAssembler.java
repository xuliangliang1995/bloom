package com.bloom.web.flower.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.Flower;
import com.bloom.web.flower.FlowerResourceApi;

public class FlowerResourceAssembler extends ResourceAssemblerSupport<Flower, FlowerResource> {

	public FlowerResourceAssembler() {
		super(FlowerResourceApi.class, FlowerResource.class);
	}

	@Override
	public FlowerResource toResource(Flower flower) {
		return createResourceWithId(flower.getId(), flower,flower.getGardenerId());
	}

	@Override
	protected FlowerResource instantiateResource(Flower flower) {
		return new FlowerResource(flower);
	}

	
}
