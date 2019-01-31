/**
 * 
 */
package com.bloom.exception;

/**
 * <p>Title: WechatNoBindGrasswortAccountException.java<／p>
 * <p>Description: 微信账户尚未绑定grasswort账户异常<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2018年12月26日
 * @version 1.0
*/
public class WechatNoBindGrasswortAccountException extends WechatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param appId
	 * @param openId
	 * @param message
	 */
	public WechatNoBindGrasswortAccountException(String appId, String openId) {
		super(appId, openId, "您的微信账户尚未绑定grasswort账户");
		// TODO Auto-generated constructor stub
	}

}
