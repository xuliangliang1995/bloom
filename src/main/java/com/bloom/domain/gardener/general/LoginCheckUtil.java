package com.bloom.domain.gardener.general;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.WebUtils;

import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.FlowBreakException;

public class LoginCheckUtil {
	
	/**
	 * 检测登录状态并返回GardenerId
	 * @param request
	 * @return
	 */
	public static int loginGardenerId(HttpServletRequest request) {
		return Optional.ofNullable(
				(int)WebUtils.getSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY)
				)
				.orElseThrow(() -> new FlowBreakException("您还没有登录！"));
	}
	
	/**
	 * 检测是否登录
	 * @param request
	 * @return
	 */
	public static boolean loginCheck(HttpServletRequest request) {
		return Optional.ofNullable(
				(int)WebUtils.getSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY)
				).isPresent();
	}

}
