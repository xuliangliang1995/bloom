package com.bloom.web.retentioncurve;

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

import com.bloom.dao.po.RetentionCurve;
import com.bloom.domain.retentioncurve.RetentionCurveService;
import com.bloom.exception.FlowBreakException;
import com.bloom.web.retentioncurve.resource.RetentionCurveResource;
import com.bloom.web.retentioncurve.resource.RetentionCurveResourceAssembler;
import com.bloom.web.retentioncurve.vo.AddRetentionCurveVO;

/**
 * 记忆曲线
 * @author 83554
 *
 */
@RestController
@ExposesResourceFor(RetentionCurve.class)
@RequestMapping("/retentioncurve")
public class RetentionCurveResourceApi {
	@Autowired
	private RetentionCurveService retentionCurveServiceImpl;
	
	@GetMapping
	public Resources<RetentionCurveResource> enabledRetentionCurve(){
		return new Resources<RetentionCurveResource>(
				new RetentionCurveResourceAssembler().toResources(retentionCurveServiceImpl.enabledRetentionCurves())
				);
	}
	
	@GetMapping("/items")
	public Resources<RetentionCurveResource> allRetentionCurve(){
		return new Resources<RetentionCurveResource>(
				new RetentionCurveResourceAssembler().toResources(retentionCurveServiceImpl.retentionCurves())
				);
	}
	
	@GetMapping("/{curveId}")
	public RetentionCurveResource getUnitCurve(@PathVariable Integer curveId) {
		RetentionCurve curve = retentionCurveServiceImpl.findById(curveId)
				.orElseThrow(() -> new FlowBreakException("指定資源不存在或已被刪除！"));
		return new RetentionCurveResourceAssembler().toResource(curve);
	}
	
	@PostMapping
	public RetentionCurveResource create(@Validated AddRetentionCurveVO addVO,BindingResult result) {
		RetentionCurve rc = new RetentionCurve();
		rc.setIntervalMinutes(addVO.getIntervalMinutes());
		rc.setNote(addVO.getNote());
		rc.setEnabled(true);
		return new RetentionCurveResourceAssembler().toResource(retentionCurveServiceImpl.add(rc));
	}
	
}
