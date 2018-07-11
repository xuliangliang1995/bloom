package com.bloom.web.retentioncurve.resource;

import org.springframework.hateoas.Resource;

import com.bloom.dao.po.RetentionCurve;

public class RetentionCurveResource extends Resource<RetentionCurve> {

	RetentionCurveResource(RetentionCurve curve) {
		super(curve);
	}

}
