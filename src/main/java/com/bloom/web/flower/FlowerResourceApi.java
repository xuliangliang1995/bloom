package com.bloom.web.flower;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
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
import com.bloom.web.flower.dto.CreateFlowerCommand;
import com.bloom.web.flower.dto.EditFlowerCommand;
import com.bloom.web.flower.resource.FlowerResource;
import com.bloom.web.flower.resource.FlowerResourceAssembler;
/**
 * 花儿er
 * @author 83554
 *
 */
@RestController
@RequestMapping("/gardener/{gardenerId}/flowers")
public class FlowerResourceApi {
	@Autowired
	private FlowerService flowerServiceImpl;
	
	@GetMapping
	public Resources<FlowerResource> readFlowers(@PathVariable Integer gardenerId){
		return null;
	}
	
	@GetMapping("/{flowerId}")
	public FlowerResource readFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId) {
		return new FlowerResourceAssembler().toResource(
				flowerServiceImpl.findById(flowerId)
				.orElseThrow(() -> new FlowBreakException("资源不存在！"))
				);
	}
	
	@PostMapping
	public ResponseEntity<?> createFlower(@PathVariable Integer gardenerId, @Validated CreateFlowerCommand createFlowerCommand,
			BindingResult result, HttpServletRequest request) {
		Flower flower = new Flower();
		flower.setGardenerId(gardenerId);
		flower.setName(createFlowerCommand.getName());
		flower.setMoral(createFlowerCommand.getMoral());
		flower = flowerServiceImpl.create(request, flower);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(linkTo(methodOn(this.getClass()).readFlower(gardenerId, flower.getId())).toUri());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(responseHeaders);
	}
	
	@PutMapping("/{flowerId}")
	public ResponseEntity<?> editFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId, EditFlowerCommand editFlowerCommand,
			HttpServletRequest request){
		Flower flower = new Flower();
		flower.setId(flowerId);
		flower.setName(editFlowerCommand.getName());
		flower.setMoral(editFlowerCommand.getMoral());
		flower.setGardenerId(gardenerId);
		flower = flowerServiceImpl.edit(request, flower);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(linkTo(methodOn(this.getClass()).readFlower(gardenerId, flower.getId())).toUri());
		return ResponseEntity.status(HttpStatus.OK)
				.body(responseHeaders);
	}
	
	@DeleteMapping("/{flowerId}")
	public ResponseEntity<?> deleteFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId,
			HttpServletRequest request){
		flowerServiceImpl.deleteById(request, flowerId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
