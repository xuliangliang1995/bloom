/**
 * 
 */
package com.bloom.manager.aliyunoss.bucket;

import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.bloom.manager.aliyunoss.Oss;

/**
 * <p>Title: BucketQuery.java<／p>
 * <p>Description: 阿里云实例存储空间查询<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月4日
 * @version 1.0
*/
public class BucketQuery {
	
	private BucketQuery() {};
	
	/**
	 * 获取实例（单例）
	 *@author xuliangliang 
	 *@return
	 */
	public static BucketQuery instance() {
		return LazyHolder.sington;
	}
	
	/**
	 * 存储空间列表
	 * @return
	 */
	public List<Bucket> buckets(){
		OSSClient oss = Oss.client();
		return oss.listBuckets();
	}
	
	/**
	 * 是否存在该存储空间
	 * @param bucketName
	 * @return
	 */
	public boolean exists(String bucketName) {
		OSSClient oss = Oss.client();
		return oss.doesBucketExist(bucketName);
	}
	
	/**
	 * 延迟单例
	 * @author xuliangliang
	 *
	 */
	private static class LazyHolder {
		static final BucketQuery sington = new BucketQuery();
	}


}
