package com.bloom.web.gardener.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.Gardener;
import com.bloom.web.flower.FlowerResourceApi;
import com.bloom.web.gardener.SignResourceApi;

public class GardenerReosurceAssembler extends ResourceAssemblerSupport<Gardener, GardenerResource> {
	public GardenerReosurceAssembler() {
		super(SignResourceApi.class, GardenerResource.class);
	}
	@Override
	public GardenerResource toResource(Gardener gardener) {
		GardenerResource gardenerResource = createResourceWithId(gardener.getId(), gardener);
		gardenerResource.add(
				linkTo(methodOn(FlowerResourceApi.class).readFlowers(gardener.getId())).withRel("flowers")
				);
		return gardenerResource;
	}
	@Override
	protected GardenerResource instantiateResource(Gardener gardener) {
		return new GardenerResource(gardener);
	}
	
}
