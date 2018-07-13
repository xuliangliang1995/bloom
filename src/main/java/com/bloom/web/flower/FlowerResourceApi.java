package com.bloom.web.flower;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Flower;
import com.bloom.domain.flower.FlowerService;
import com.bloom.exception.FlowBreakException;
import com.bloom.web.flower.resource.FlowerResource;
import com.bloom.web.flower.resource.FlowerResourceAssembler;
import com.bloom.web.flower.vo.CreateFlowerForm;
import com.bloom.web.flower.vo.EditFlowerForm;
/**
 * 花儿er
 * @author 83554
 *
 */
@RestController
@ExposesResourceFor(Flower.class)
@RequestMapping("/gardener/{gardenerId}/flowers")
public class FlowerResourceApi {
	@Autowired
	private FlowerService flowerServiceImpl;
	
	@GetMapping
	public Resources<FlowerResource> readFlowers(@PathVariable Integer gardenerId){
		return new Resources<FlowerResource>(
				new FlowerResourceAssembler().toResources(flowerServiceImpl.findFlowerByGardener(gardenerId))
				);
	}
	
	@GetMapping("/{flowerId}")
	public FlowerResource readFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId) {
		Flower flower = Optional.of(flowerServiceImpl.findById(flowerId))
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new FlowBreakException("资源不存在！"));
		return new FlowerResourceAssembler().toResource(flower);
	}
	
	@PostMapping
	public FlowerResource createFlower(@PathVariable Integer gardenerId, @Validated CreateFlowerForm createFlowerForm,
			BindingResult result) {
		Flower flower = flowerServiceImpl.create(gardenerId, createFlowerForm);
		return new FlowerResourceAssembler().toResource(flower);
	}
	
	@PutMapping("/{flowerId}")
	public FlowerResource editFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId,@Validated EditFlowerForm editFlowerForm,
			BindingResult result){
		return new FlowerResourceAssembler().toResource(
				flowerServiceImpl.edit(gardenerId,flowerId,editFlowerForm)
				);
	}
	
	@DeleteMapping("/{flowerId}")
	public ResponseEntity<?> deleteFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId){
		flowerServiceImpl.deleteById(gardenerId, flowerId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
