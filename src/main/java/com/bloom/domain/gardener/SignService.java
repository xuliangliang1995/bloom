package com.bloom.domain.gardener;

import javax.servlet.http.HttpServletRequest;

import com.bloom.dao.po.Gardener;
import com.bloom.web.gardener.vo.SignUpForm;
/**
 * 注册、签入、签出
 * @author 83554
 *
 */
public interface SignService {
	
	Gardener signUp(SignUpForm signUpForm);
	
	Gardener signIn(HttpServletRequest request, String originalUsername, String originalPassword);
	
	Gardener signInByWechatOpenId(HttpServletRequest request,String appId,String openId);
	
	void signOut(HttpServletRequest request);
	
}
