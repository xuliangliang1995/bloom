package com.bloom.web.petal.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.Petal;
import com.bloom.web.petal.PetalResourceApi;

public class PetalResourceAssembler extends ResourceAssemblerSupport<Petal, PetalResource> {

	public PetalResourceAssembler() {
		super(PetalResourceApi.class, PetalResource.class);
	}

	@Override
	public PetalResource toResource(Petal petal) {
		return createResourceWithId(petal.getId(), petal, petal.getFlowerId());
	}

	@Override
	protected PetalResource instantiateResource(Petal petal) {
		return new PetalResource(petal);
	}
	
	
}
