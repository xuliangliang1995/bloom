package com.bloom.web.flower;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Flower;
import com.bloom.domain.flower.FlowerService;
import com.bloom.exception.FlowBreakException;
import com.bloom.web.flower.dto.CreateFlowerCommand;
import com.bloom.web.flower.resource.FlowerResource;
import com.bloom.web.flower.resource.FlowerResourceAssembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
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
	
	@GetMapping("/{flowerId}")
	public FlowerResource readFlower(@PathVariable Integer gardenerId, @PathVariable Integer flowerId) {
		return new FlowerResourceAssembler().toResource(
				flowerServiceImpl.findById(flowerId)
				.orElseThrow(() -> new FlowBreakException("资源不存在！"))
				);
	}
	
	@PostMapping
	public ResponseEntity<?> createFlower(@PathVariable Integer gardenerId, CreateFlowerCommand createFlowerCommand,
			HttpServletRequest request) {
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

}
