package com.bloom.web.petal;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.util.time.GeneralDateUtils;
import com.bloom.web.petal.resource.PetalProgressResource;
import com.bloom.web.petal.resource.PetalResource;
import com.bloom.web.petal.resource.PetalResourceAssembler;

@RestController
@RequestMapping("/gardener/{gardenerId}/petalProgress")
public class PetalProgressResourceApi {
	@Autowired
	private PetalProgressService petalProgressServiceImpl;
	@Autowired
	private PetalService petalServiceImpl;
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping
	public Resources<PetalProgressResource> petalsProgress(
			@PathVariable int gardenerId,
			@RequestParam(value = "start_date",required = false)Date startDate,
			@RequestParam(value = "end_date",required = false)Date endDate){
		Assert.isTrue(LoginCheckUtil.loginGardenerId(request)==gardenerId,"权限不足！");
		if(null == startDate || null == endDate) {
			Date now = new Date();
			startDate = GeneralDateUtils.start(now);
			endDate = GeneralDateUtils.end(now);
		}
		return new Resources<PetalProgressResource>(
				petalProgressServiceImpl.petalProgress(gardenerId,startDate, endDate).stream()
				.map(progress -> {
					PetalResource petalResource = new PetalResourceAssembler().toResource(
							petalServiceImpl.findByPetalId(progress.getPetalId()));
					return new PetalProgressResource(progress,petalResource);
				})
				.collect(Collectors.toList())
				);
	}

}
