package com.bloom.web.gardener;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.BasicInfoService;
import com.bloom.domain.gardener.SignService;
import com.bloom.response.Result;
import com.bloom.web.gardener.resource.GardenerReosurceAssembler;
import com.bloom.web.gardener.resource.GardenerResource;
/**
 * 签入、签出
 * @author 83554
 *
 */
@RestController
@ExposesResourceFor(Gardener.class)
@RequestMapping(value = "/gardener")
public class SignResourceApi {
	@Resource
	private SignService signServiceImpl;
	@Resource
	private BasicInfoService basicInfoServiceImpl;
	/**
	 * SignUp
	 * @return
	 */
	@PostMapping
	@CrossOrigin
	public Result signUp(
			@RequestParam("username")String username,
			@RequestParam("password")String password) {
		signServiceImpl.signUp(username, password);
		return Result.success();
	}
	/**
	 * SignIn
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping
	@CrossOrigin
	public ResponseEntity<GardenerResource> signIn(
			@RequestParam("username")String username,
			@RequestParam("password")String password,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(
					new GardenerReosurceAssembler().toResource(signServiceImpl.signIn(request, username, password))
					);
	}
	
	@GetMapping("/{gardenerId}")
	public GardenerResource getGardener(@PathVariable Integer gardenerId) {
		return new GardenerReosurceAssembler().toResource(basicInfoServiceImpl.findGardenerById(gardenerId));
	}
}
