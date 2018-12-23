package com.bloom.web.petal;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 叶子
 * @author 83554
 *
 */

import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Petal;
import com.bloom.domain.PageResources;
import com.bloom.domain.flower.FlowerService;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.petal.PetalPageRender;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.exception.ServiceException;
import com.bloom.util.mybatis.Page;
import com.bloom.web.petal.resource.PetalResource;
import com.bloom.web.petal.resource.PetalResourceAssembler;
import com.bloom.web.petal.vo.CreatePetalForm;
import com.bloom.web.petal.vo.EditPetalForm;
@RestController
@ExposesResourceFor(Petal.class)
@RequestMapping("/flowers/{flowerId}/petal")
public class PetalResourceApi {
	@Autowired
	private PetalService petalServiceImpl;
	@Autowired
	private FlowerService flowerServiceImpl;

	@Autowired
	private HttpServletRequest request;
	
	@GetMapping
	public Resources<PetalResource> flowerPetals(@PathVariable Integer flowerId,
			@RequestParam(value = "page_no", required = false, defaultValue = Page.DEFAULT_PAGE_NO_TEXT)Integer pageNo,
			@RequestParam(value = "page_size", required = false, defaultValue = Page.DEFAULT_PAGE_SIZE_TEXT)Integer pageSize){
		Page<Petal> page = new Page<Petal>(pageNo,pageSize);
		return new PageResources<PetalResource>(
				new PetalResourceAssembler().toResources(petalServiceImpl.flowerPetals(flowerId,page))
				).withTotal(page.getTotalCount());
	}
	
	@GetMapping("/{petalId}")
	public PetalResource findById(@PathVariable Integer flowerId, @PathVariable Integer petalId) {
		Petal petal = Optional.of(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new ServiceException("资源不存在或已被删除！"));
		return new PetalResourceAssembler().toResource(petal);
	}
	
	
	@PostMapping
	public PetalResource create(@Validated CreatePetalForm createPetalForm, BindingResult result, @PathVariable Integer flowerId) {
		int gardenerId = LoginCheckUtil.loginGardenerId(request);
		Flower flower = Optional.of(flowerServiceImpl.findById(flowerId))
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new ServiceException("操作权限不足！"));
		return new PetalResourceAssembler().toResource(
				petalServiceImpl.add(flower,createPetalForm)
				);
	}
	
	@PutMapping("{petalId}")
	public PetalResource edit(@Validated EditPetalForm editPetalForm, BindingResult result,
			@PathVariable Integer flowerId, @PathVariable Integer petalId) {
		int gardenerId = LoginCheckUtil.loginGardenerId(request);
		Flower flower = Optional.of(flowerServiceImpl.findById(flowerId))
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new ServiceException("操作权限不足！"));
		return new PetalResourceAssembler().toResource(
				petalServiceImpl.edit(petalId,flower,editPetalForm)
				);
	}
	
	@DeleteMapping("{petalId}")
	public ResponseEntity<?> deletePetal(@PathVariable Integer flowerId, @PathVariable Integer petalId){
		int gardenerId = LoginCheckUtil.loginGardenerId(request);
		Flower flower = Optional.of(flowerServiceImpl.findById(flowerId))
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new ServiceException("操作权限不足！"));
		petalServiceImpl.deletePetal(petalId, flower);
		return ResponseEntity.ok("删除成功");
	}
	
	@GetMapping("/{petalId}/link")
	public ResponseEntity<?> petalLink(@PathVariable Integer flowerId, @PathVariable Integer petalId){
		Petal petal = Optional.of(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new ServiceException("资源不存在或已被删除！"));
		Assert.isTrue(PetalVarietyEnum.LINK.getId() == petal.getPetalVarietyId().intValue(),"请求错误！");
		return ResponseEntity.ok(
				petalServiceImpl.getPetalInnerLinkService().findByPetalId(petalId)
				);
	}
	
	@GetMapping("/{petalId}/text")
	public ResponseEntity<?> petalText(@PathVariable Integer flowerId, @PathVariable Integer petalId){
		Petal petal = Optional.of(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new ServiceException("资源不存在或已被删除！"));
		Assert.isTrue(PetalVarietyEnum.RICH_TEXT.getId() == petal.getPetalVarietyId().intValue(),"请求错误！");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(
				petalServiceImpl.getPetalInnerTextService().findByPetalId(petalId), 
				headers, 
				HttpStatus.OK);
	}
	
	@GetMapping("/{petalId}/page")
	public ResponseEntity<?> petalPage(@PathVariable Integer flowerId, @PathVariable Integer petalId) {
		Petal petal = Optional.ofNullable(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new ServiceException("资源不存在或已被删除！"));
		Assert.isTrue(PetalVarietyEnum.RICH_TEXT.getId() == petal.getPetalVarietyId().intValue(), "请求错误！");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("text/html;charset=utf-8"));;
		return new ResponseEntity<>(
				PetalPageRender.render(petalServiceImpl.getPetalInnerTextService().findByPetalId(petalId).getText()), 
				headers, 
				HttpStatus.OK);
	}
}
