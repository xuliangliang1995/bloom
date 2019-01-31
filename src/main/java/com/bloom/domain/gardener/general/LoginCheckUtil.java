package com.bloom.domain.gardener.general;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.ServiceException;


public class LoginCheckUtil {
	
	/**
	 * 检测登录状态并返回GardenerId
	 * @param request
	 * @return
	 */
	public static int loginGardenerId(HttpServletRequest request) {
		return (int) Optional.ofNullable(
				WebUtils.getSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY)
				)
				.orElseThrow(() -> new ServiceException("您还没有登录！"));
	}
	
	/**
	 * 检测是否登录
	 * @param request
	 * @return
	 */
	public static boolean loginCheck(HttpServletRequest request) {
		return Optional.ofNullable(
				WebUtils.getSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY)
				).isPresent();
	}
	
	/**
	 * 获取登录者的roleId
	 * @param request
	 * @return
	 */
	public static int loginRoleId(HttpServletRequest request) {
		return (int)Optional.ofNullable(
				WebUtils.getSessionAttribute(request, SessionConstantKey.ROLE_ID_KEY)
				)
				.orElseThrow(() -> new ServiceException("您还没有登录！"));
	}
}
