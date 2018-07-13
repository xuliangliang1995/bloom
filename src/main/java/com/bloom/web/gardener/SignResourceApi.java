package com.bloom.web.gardener;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.SignService;
import com.bloom.response.Result;
import com.bloom.web.gardener.resource.GardenerReosurceAssembler;
import com.bloom.web.gardener.resource.GardenerResource;
import com.bloom.web.gardener.vo.SignInForm;
import com.bloom.web.gardener.vo.SignUpForm;
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
	/**
	 * SignUp
	 * @return
	 */
	@PostMapping
	@CrossOrigin
	public Result signUp(@Validated SignUpForm signUpForm,BindingResult result) {
		signServiceImpl.signUp(signUpForm.getUsername(), signUpForm.getPassword());
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
	public ResponseEntity<GardenerResource> signIn(@Validated SignInForm signInForm,BindingResult result,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(
					new GardenerReosurceAssembler().toResource(signServiceImpl.signIn(request, signInForm.getUsername(), signInForm.getPassword()))
					);
	}


}
