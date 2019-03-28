package com.bloom.web.petal.resource;

import org.springframework.hateoas.Resource;

import com.bloom.dao.po.PetalProgress;

public class PetalProgressResource extends Resource<PetalProgress>{
	private PetalResource petal;
	public PetalProgressResource(PetalProgress progress,PetalResource petalResource) {
		super(progress);
		this.petal = petalResource;
	}
	PetalProgressResource(PetalProgress progress) {
		super(progress);
	}
	public PetalResource getPetal() {
		return petal;
	}
	public void setPetal(PetalResource petal) {
		this.petal = petal;
	}
	
	
}
