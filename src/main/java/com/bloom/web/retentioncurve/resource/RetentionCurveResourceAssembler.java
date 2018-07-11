package com.bloom.web.retentioncurve.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bloom.dao.po.RetentionCurve;
import com.bloom.web.retentioncurve.RetentionCurveResourceApi;

public class RetentionCurveResourceAssembler extends ResourceAssemblerSupport<RetentionCurve, RetentionCurveResource> {

	public RetentionCurveResourceAssembler() {
		super(RetentionCurveResourceApi.class, RetentionCurveResource.class);
	}

	@Override
	public RetentionCurveResource toResource(RetentionCurve curve) {
		return createResourceWithId(curve.getId(), curve);
	}

	@Override
	protected RetentionCurveResource instantiateResource(RetentionCurve curve) {
		return new RetentionCurveResource(curve);
	}
	
	
}
