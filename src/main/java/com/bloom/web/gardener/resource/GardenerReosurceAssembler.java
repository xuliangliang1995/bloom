package com.bloom.web.gardener.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.Gardener;
import com.bloom.util.mybatis.Page;
import com.bloom.web.flower.FlowerResourceApi;
import com.bloom.web.gardener.GardenerResourceApi;
import com.bloom.web.petal.PetalProgressResourceApi;
import com.bloom.web.retentioncurve.RetentionCurveResourceApi;

public class GardenerReosurceAssembler extends ResourceAssemblerSupport<Gardener, GardenerResource> {
	public GardenerReosurceAssembler() {
		super(GardenerResourceApi.class, GardenerResource.class);
	}
	@Override
	public GardenerResource toResource(Gardener gardener) {
		GardenerResource gardenerResource = createResourceWithId(gardener.getId(), gardener);
		gardenerResource.add(
				linkTo(methodOn(FlowerResourceApi.class).readFlowers(gardener.getId(),Page.DEFAULT_PAGE_NO,Page.DEFAULT_PAGE_SIZE)).withRel("flowers"),
				linkTo(methodOn(RetentionCurveResourceApi.class).enabledRetentionCurve()).withRel("retentionCurve")
				);
		gardenerResource.add(linkTo(methodOn(PetalProgressResourceApi.class).petalsProgress(gardenerResource.getContent().getId(),null, null)).withRel("petalsProgress"));
		return gardenerResource;
	}
	@Override
	protected GardenerResource instantiateResource(Gardener gardener) {
		return new GardenerResource(gardener);
	}
	
}
