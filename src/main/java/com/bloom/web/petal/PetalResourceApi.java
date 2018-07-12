package com.bloom.web.petal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 叶子
 * @author 83554
 *
 */

import com.bloom.dao.po.Petal;
import com.bloom.domain.petal.PetalService;
import com.bloom.exception.FlowBreakException;
import com.bloom.web.petal.resource.PetalResource;
import com.bloom.web.petal.resource.PetalResourceAssembler;
import com.bloom.web.petal.vo.CreatePetalForm;
@RestController
@ExposesResourceFor(Petal.class)
@RequestMapping("/flowers/{flowerId}/petal")
public class PetalResourceApi {
	@Autowired
	private PetalService petalServiceImpl;
	
	@GetMapping
	public Resources<PetalResource> flowerPetals(@PathVariable Integer flowerId){
		return new Resources<PetalResource>(
				new PetalResourceAssembler().toResources(petalServiceImpl.flowerPetals(flowerId))
				);
	}
	
	@GetMapping("/{petalId}")
	public PetalResource findById(@PathVariable Integer flowerId,@PathVariable Integer petalId) {
		Petal petal = petalServiceImpl.findByPetalId(petalId)
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
		return new PetalResourceAssembler().toResource(petal);
	}
	
	@PostMapping
	public PetalResource create(@Validated CreatePetalForm createPetalForm,BindingResult result,@PathVariable Integer flowerId) {
		Petal petal = new Petal();
		petal.setFlowerId(flowerId);
		petal.setName(createPetalForm.getName());
		petal.setNote(createPetalForm.getNote());
		petal.setPetalVarietyId(createPetalForm.getPetalVariety());
		return new PetalResourceAssembler().toResource(petalServiceImpl.add(petal));
	}

}
