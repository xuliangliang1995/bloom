package com.bloom.web.gardener;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public Result signUp(
			@RequestParam("username")String username,
			@RequestParam("password")String password) {
		signServiceImpl.signUp(username, password);
		return Result.success();
	}

}
