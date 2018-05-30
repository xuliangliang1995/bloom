package com.bloom.web.gardener;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.SignService;
import com.bloom.response.Result;
/**
 * 签入、签出
 * @author 83554
 *
 */
@RestController
@RequestMapping(value = "/gardener")
public class SignResourceApi {
	@Resource
	private SignService signServiceImpl;
	/**
	 * SignUp
	 * @return
	 */
	@PutMapping
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
	public ResponseEntity<Gardener> signIn(
			@RequestParam("username")String username,
			@RequestParam("password")String password,
			HttpServletRequest request) {
		Gardener gardener = signServiceImpl.signIn(request, username, password);
		return ResponseEntity.status(HttpStatus.OK)
				.body(gardener);
	}

}
