package com.bloom.web.gardener;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.SignService;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.gardener.meta.SessionConstantKey;
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
	 * 注册
	 * @param signUpForm
	 * @param result
	 * @return
	 */
	@PostMapping
	public Result signUp(@Validated SignUpForm signUpForm,BindingResult result) {
		signServiceImpl.signUp(signUpForm);
		return Result.success();
	}

	/**
	 * 登录
	 * @param signInForm
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping
	public ResponseEntity<GardenerResource> signIn(@Validated SignInForm signInForm,BindingResult result,
			HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
		return ResponseEntity.status(HttpStatus.OK).body(
					new GardenerReosurceAssembler().toResource(
							signServiceImpl.signIn(request, signInForm.getUsername(), signInForm.getPassword())
							)
					);
	}

	/**
	 * 当前登录信息
	 * @param request
	 * @return
	 */
	@GetMapping("/loginInfo")
	public ResponseEntity<?> loginInfo(HttpServletRequest request){
		return ResponseEntity.ok(LoginCheckUtil.loginCheck(request)
				?LoginCheckUtil.loginGardenerId(request)
						:0);
	}

	/**
	 * 登出
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping("/loginOut")
	public ResponseEntity<?> signOut(HttpServletRequest request) throws URISyntaxException{
		signServiceImpl.signOut(request);
		HttpHeaders headers = new HttpHeaders();
		headers.setPragma("no-cache");
		headers.setCacheControl(CacheControl.noCache());
		headers.setCacheControl(CacheControl.noStore());
		headers.setLocation(new URI("https://www.grasswort.com"));
		return new ResponseEntity<>(headers,HttpStatus.PERMANENT_REDIRECT);
	}
}
