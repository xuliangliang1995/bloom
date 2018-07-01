package com.bloom.advice;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bloom.exception.FlowBreakException;

@ControllerAdvice
public class GeneralAdvice {
	
	@ResponseBody
	@ExceptionHandler(FlowBreakException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public VndErrors flowBreakException(FlowBreakException ex) {
		return new VndErrors("error", ex.getMessage());
	}
	
}
