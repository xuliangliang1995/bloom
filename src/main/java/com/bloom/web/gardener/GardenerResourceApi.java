package com.bloom.web.gardener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.BasicInfoService;
import com.bloom.web.gardener.resource.GardenerReosurceAssembler;
import com.bloom.web.gardener.resource.GardenerResource;

/**
 * 花匠
 * @author 83554
 *
 */
@RestController
@ExposesResourceFor(Gardener.class)
@RequestMapping("/gardeners")
public class GardenerResourceApi {
	@Autowired
	private BasicInfoService basicInfoServiceImpl;
	
	@GetMapping("/{gardenerId}")
	@CrossOrigin
	public GardenerResource getGardener(@PathVariable Integer gardenerId) {
		return new GardenerReosurceAssembler().toResource(basicInfoServiceImpl.findGardenerById(gardenerId));
	}
}
