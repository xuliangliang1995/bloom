package com.bloom.web.petal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.PetalVariety;
import com.bloom.domain.petal.PetalVarietyService;
import com.bloom.web.petal.resource.PetalVarietyResource;
import com.bloom.web.petal.resource.PetalVarietyResourceAssembler;

/**
 * 叶子种类
 * @author 83554
 *
 */
@RestController
@ExposesResourceFor(PetalVariety.class)
@RequestMapping("/petal/varieties")
public class PetalVarietyResourceApi {
	@Autowired
	private PetalVarietyService petalVarietyServiceImpl;
	
	@GetMapping
	public Resources<PetalVarietyResource> varieties(){
		return new Resources<PetalVarietyResource>(
				new PetalVarietyResourceAssembler().toResources(petalVarietyServiceImpl.varieties())
				);
	}
	
	@GetMapping("/{varietyId}")
	public PetalVarietyResource findById(@PathVariable Integer varietyId) {
		PetalVariety variety = petalVarietyServiceImpl.findById(varietyId);
		return new PetalVarietyResourceAssembler().toResource(variety);
	}

}
