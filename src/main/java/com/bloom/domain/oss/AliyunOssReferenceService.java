/**
 * 
 */
package com.bloom.domain.oss;

import java.util.List;

import com.bloom.dao.po.AliyunOssReference;
import com.bloom.domain.oss.meta.OssReferrerTypeEnum;
import com.bloom.manager.aliyunoss.dto.OssRefDTO;

/**
 * <p>Title: AliyunOssReferenceService.java<／p>
 * <p>Description: 阿里云OSS对象引用<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
public interface AliyunOssReferenceService {
	/**
	 * 
	 * <p>Title: saveRef</p>
	 * <p>Description: 保存图片引用</p>
	 * @param type
	 * @param referrerId
	 * @param ossBucket
	 * @param ossKey
	 * void
	 */
	void saveRef(OssReferrerTypeEnum type, Integer referrerId, String ossBucket, String ossKey);
	/**
	 * 
	 * <p>Title: existOssRef</p>
	 * <p>Description: 判断OSS 文件是否被引用</p>
	 * @param ossBucket
	 * @param ossKey
	 * @return
	 * boolean
	 */
	boolean existOssRef(String ossBucket, String ossKey);
	/**
	 * 
	 * <p>Title: removeRef</p>
	 * <p>Description: 移除OSS引用</p>
	 * @param type
	 * @param referrerId
	 * @param ossBucket
	 * @param ossKey
	 * void
	 */
	void removeRef(OssReferrerTypeEnum type, Integer referrerId, String ossBucket, String ossKey);
	
	/**
	 * 
	 * <p>Title: removeRefByReferrer</p>
	 * <p>Description: 根据引用者移除所有相关OSS引用</p>
	 * @param type
	 * @param referrerId
	 * void
	 */
	void removeRefByReferrer(OssReferrerTypeEnum type, Integer referrerId);
	
	/**
	 * 
	 * <p>Title: listOssRefs</p>
	 * <p>Description: 查询OSS引用列表</p>
	 * @param type
	 * @param referrerId
	 * @return
	 * List<AliyunOssReference>
	 */
	List<AliyunOssReference> listOssRefs(OssReferrerTypeEnum type, Integer referrerId);
	
	/**
	 * 
	 * <p>Title: replaceRefs</p>
	 * <p>Description: 替换之前引用者的所有引用</p>
	 * @param type
	 * @param referrerId
	 * @param refs
	 * void
	 */
	void replaceRefs(OssReferrerTypeEnum type, Integer referrerId, List<OssRefDTO> refs);

}
