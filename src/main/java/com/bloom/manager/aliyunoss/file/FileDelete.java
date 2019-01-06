/**
 * 
 */
package com.bloom.manager.aliyunoss.file;

import com.bloom.manager.aliyunoss.Oss;

/**
 * <p>Title: FileDelete.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
public class FileDelete {
	
	private FileDelete() {};
	
	/**
	 * 
	 * <p>Title: instance</p>
	 * <p>Description: 获取FileDelete实例</p>
	 * @return
	 * FileDelete
	 */
	public static FileDelete instance() {
		return LazyHolder.sington;
	}
	/**
	 * 
	 * <p>Title: delete</p>
	 * <p>Description: 删除对象</p>
	 * @param bucketName
	 * @param key
	 * void
	 */
	public void delete(String bucketName, String key) {
		Oss.client().deleteObject(bucketName, key);
	}
	/**
	 * 
	 * <p>Title: FileDelete.java<／p>
	 * <p>Description: FileDelete懒加载<／p>
	 * <p>Copyright: Copyright (c) 2019<／p>
	 * <p>Company: grasswort<／p>
	 *
	 * @author 树林里面丢了鞋
	 * @date 2019年1月6日
	 * @version 1.0
	 */
	private static class LazyHolder {
		static FileDelete sington = new FileDelete();
	}

}
