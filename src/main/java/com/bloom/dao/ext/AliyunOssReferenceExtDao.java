/**
 * 
 */
package com.bloom.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.AliyunOssReferenceMapper;
import com.bloom.dao.po.AliyunOssReference;
import com.bloom.domain.oss.meta.OssReferrerTypeEnum;

/**
 * <p>Title: AliyunOssReferenceExtDao.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
public interface AliyunOssReferenceExtDao extends AliyunOssReferenceMapper {
	
	/**
	 * 
	 * <p>Title: countOssRef</p>
	 * <p>Description: 查询引用数量</p>
	 * @param ossBucket
	 * @param ossKey
	 * @return
	 * int
	 */
	int countOssRef(@Param("bucket")String ossBucket, @Param("key")String ossKey);
	
	/**
	 * 
	 * <p>Title: listOssRefs</p>
	 * <p>Description: 获取引用列表</p>
	 * @param type
	 * @param referrerId
	 * @return
	 * List<AliyunOssReference>
	 */
	List<AliyunOssReference> listOssRefs(@Param("referrerTypeEnum")OssReferrerTypeEnum type, @Param("referrerId")Integer referrerId);
	/**
	 * 
	 * <p>Title: removeRefs</p>
	 * <p>Description: 移除引用</p>
	 * @param type
	 * @param referrerId
	 * @param ossBucket
	 * @param ossKey
	 * @return
	 * int
	 */
	int removeRefs(@Param("referrerTypeEnum")OssReferrerTypeEnum type, @Param("referrerId")Integer referrerId, @Param("bucket")String ossBucket, @Param("key")String ossKey);
}
