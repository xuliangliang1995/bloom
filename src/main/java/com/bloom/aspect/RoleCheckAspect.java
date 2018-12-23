package com.bloom.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.bloom.annotation.RoleCheck;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.exception.ServiceException;

@Aspect
@Component
public class RoleCheckAspect {
	@Resource
	private HttpServletRequest request;
	
	@Before(value = "@annotation(com.bloom.annotation.RoleCheck)")
	public void check(JoinPoint jp) throws NoSuchMethodException, SecurityException {
		Signature signature = jp.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();
		Method realMethod = jp.getTarget().getClass().getMethod(signature.getName(), targetMethod.getParameterTypes());
		RoleCheck rc = realMethod.getAnnotation(RoleCheck.class);
		Arrays.stream(rc.value())
			.filter(role -> role.value() == LoginCheckUtil.loginRoleId(request))
			.findFirst()
			.orElseThrow(() -> new ServiceException("权限不足！"));
	}

}
