package com.bloom.web.gardener.resource;

import org.springframework.hateoas.Resource;

import com.bloom.dao.po.Gardener;

public class GardenerResource extends Resource<Gardener>{

	public GardenerResource(Gardener gardener) {
		super(gardener);
		// TODO Auto-generated constructor stub
	}

}
