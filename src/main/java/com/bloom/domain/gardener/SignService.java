package com.bloom.domain.gardener;

import javax.servlet.http.HttpSession;

import com.bloom.dao.po.Gardener;
/**
 * 注册、签入、签出
 * @author 83554
 *
 */
public interface SignService {
	
	void signUp(String originalUsername,String originalPassword);
	
	Gardener signIn(HttpSession session, String originalUsername, String originalPassword);
	
	void signOut(HttpSession session);
}
