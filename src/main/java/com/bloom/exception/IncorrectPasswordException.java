/**
 * 
 */
package com.bloom.exception;

/**
 * <p>Title: IncorrectPasswordException.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2018年12月23日
 * @version 1.0
*/
public class IncorrectPasswordException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 */
	public IncorrectPasswordException() {
		super("密码错误！");
	}

}
