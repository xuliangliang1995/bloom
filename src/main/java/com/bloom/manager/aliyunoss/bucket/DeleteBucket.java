/**
 * 
 */
package com.bloom.manager.aliyunoss.bucket;

import com.aliyun.oss.OSSClient;
import com.bloom.manager.aliyunoss.Oss;

/**
 * <p>Title: DeleteBucket.java<／p>
 * <p>Description: 阿里云OSS对象存储空间删除<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月4日
 * @version 1.0
*/
public class DeleteBucket {
	
	private DeleteBucket() {};
	
	/**
	 * 获取实例（单例）
	 *@author xuliangliang 
	 *@return
	 */
	public static DeleteBucket instance() {
		return LazyHolder.sington;
	}
	
	/**
	 * 删除存储仓库
	 * @param bucketName
	 */
	public void delete(String bucketName) {
		OSSClient oss = Oss.client();
		oss.deleteBucket(bucketName);
	}
	/**
	 * 延迟单例
	 * @author xuliangliang
	 *
	 */
	private static class LazyHolder {
		static final DeleteBucket sington = new DeleteBucket();
	}


}
