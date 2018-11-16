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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.exception.FlowBreakException;
import com.bloom.util.mybatis.Page;
import com.bloom.web.petal.resource.PetalResource;
import com.bloom.web.petal.resource.PetalResourceAssembler;
import com.bloom.web.petal.vo.CreatePetalForm;
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
		Page page = new Page<Petal>(pageNo,pageSize);
		return new PageResources<PetalResource>(
				new PetalResourceAssembler().toResources(petalServiceImpl.flowerPetals(flowerId,page))
				).withTotal(page.getTotalCount());
	}
	
	@GetMapping("/{petalId}")
	public PetalResource findById(@PathVariable Integer flowerId,@PathVariable Integer petalId) {
		Petal petal = Optional.of(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
		return new PetalResourceAssembler().toResource(petal);
	}
	
	@PostMapping
	public PetalResource create(@Validated CreatePetalForm createPetalForm,BindingResult result,@PathVariable Integer flowerId) {
		int gardenerId = LoginCheckUtil.loginGardenerId(request);
		Flower flower = Optional.of(flowerServiceImpl.findById(flowerId))
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new FlowBreakException("操作权限不足！"));
		return new PetalResourceAssembler().toResource(
				petalServiceImpl.add(flower,createPetalForm)
				);
	}
	
	@GetMapping("/{petalId}/link")
	public ResponseEntity<?> petalLink(@PathVariable Integer flowerId,@PathVariable Integer petalId){
		Petal petal = Optional.of(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
		Assert.isTrue(PetalVarietyEnum.LINK.getId() == petal.getPetalVarietyId().intValue(),"请求错误！");
		return ResponseEntity.ok(
				petalServiceImpl.getPetalInnerLinkService().findByPetalId(petalId)
				);
	}
	
	@GetMapping("/{petalId}/text")
	public ResponseEntity<?> petalText(@PathVariable Integer flowerId,@PathVariable Integer petalId){
		Petal petal = Optional.of(petalServiceImpl.findByPetalId(petalId))
				.filter(spetal -> spetal.getFlowerId().equals(flowerId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
		Assert.isTrue(PetalVarietyEnum.RICH_TEXT.getId() == petal.getPetalVarietyId().intValue(),"请求错误！");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return new ResponseEntity<>(
				petalServiceImpl.getPetalInnerTextService().findByPetalId(petalId), 
				headers, 
				HttpStatus.OK);
	}

}
