package com.bloom.exception;
/**
 * 因业务规则导致流程中断
 * @author 83554
 *
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException(String msg) {
		super(msg);
	};
}
