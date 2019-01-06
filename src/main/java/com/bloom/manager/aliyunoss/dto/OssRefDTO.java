/**
 * 
 */
package com.bloom.manager.aliyunoss.dto;

import lombok.Data;

/**
 * <p>Title: OssRefDTO.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
@Data
public class OssRefDTO {
	/**
	 * 存储空间名称
	 */
	private String bucketName;
	/**
	 * 对象名称
	 */
	private String objectKey;
	/**
	 * url
	 */
	private String url;

}
