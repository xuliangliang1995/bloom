package com.bloom.web.petal.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.Petal;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.web.petal.PetalResourceApi;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;

public class PetalResourceAssembler extends ResourceAssemblerSupport<Petal, PetalResource> {

	public PetalResourceAssembler() {
		super(PetalResourceApi.class, PetalResource.class);
	}

	@Override
	public PetalResource toResource(Petal petal) {
		PetalResource pr = createResourceWithId(petal.getId(), petal, petal.getFlowerId());
		PetalVarietyEnum variety = Arrays.stream(PetalVarietyEnum.values())
				.filter(var -> var.getId() == petal.getPetalVarietyId().intValue())
				.findFirst().get();
		switch (variety) {
		case LINK:
			pr.add(linkTo(methodOn(PetalResourceApi.class).petalLink(petal.getFlowerId(), petal.getId())).withRel("link"));
			break;
		case RICH_TEXT:
			pr.add(linkTo(methodOn(PetalResourceApi.class).petalText(petal.getFlowerId(), petal.getId())).withRel("text"));
			break;
		}
		return pr;
				
	}

	@Override
	protected PetalResource instantiateResource(Petal petal) {
		return new PetalResource(petal);
	}
	
	
}
