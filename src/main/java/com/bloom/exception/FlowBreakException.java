package com.bloom.exception;
/**
 * 因业务规则导致流程中断
 * @author 83554
 *
 */
public class FlowBreakException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FlowBreakException(String msg) {
		super(msg);
	};
}
