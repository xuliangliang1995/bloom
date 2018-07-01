package com.bloom.aspect;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.bloom.exception.FlowBreakException;

@Aspect
@Component
public class ValidatorAspect {
	
	@Before(value = "execution(* com.bloom.web..*.*(..))")
	public void validate(JoinPoint jp) {
		Object [] args = jp.getArgs();
		Optional<Object> bindingResult = Arrays.stream(args)
				.filter(arg -> arg instanceof BindingResult)
				.findFirst();
		if(bindingResult.isPresent()) {
			BindingResult result = (BindingResult) bindingResult.get();
			if(result.hasErrors()) {
				throw new FlowBreakException(result.getAllErrors().get(0).getDefaultMessage());
			}
		}
	}

}
