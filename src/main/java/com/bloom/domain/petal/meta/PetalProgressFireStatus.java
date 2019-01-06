/**
 * 
 */
package com.bloom.domain.petal.meta;

/**
 * <p>Title: PetalFireStatus.java<／p>
 * <p>Description: Petal进程触发状态<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
public enum PetalProgressFireStatus {
	NO_FIRE((byte)0),
	FIRE((byte)1);
	private Byte status;
	private PetalProgressFireStatus(Byte status) {
		this.status = status;
	}
	public byte status() {
		return status;
	}

}
