/**
 * 
 */
package com.bloom.domain.oss.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.AliyunOssReferenceExtDao;
import com.bloom.dao.po.AliyunOssReference;
import com.bloom.domain.oss.AliyunOssReferenceService;
import com.bloom.domain.oss.meta.OssReferrerTypeEnum;
import com.bloom.manager.aliyunoss.Oss;
import com.bloom.manager.aliyunoss.dto.OssRefDTO;

/**
 * <p>Title: AliyunOssReferenceServiceImpl.java<／p>
 * <p>Description: 阿里云OSS对象引用实现<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
@Service
public class AliyunOssReferenceServiceImpl implements AliyunOssReferenceService {
	@Resource
	private AliyunOssReferenceExtDao aliyunOssReferenceExtDao;

	/* (non-Javadoc)
	 * @see com.bloom.domain.oss.AliyunOssReferenceService#saveRef(com.bloom.domain.oss.meta.OssReferrerType, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveRef(OssReferrerTypeEnum refType, Integer referrerId, String ossBucket, String ossKey) {
		Date now = new Date();
		AliyunOssReference ref = new AliyunOssReference();
		ref.setOssBucket(ossBucket);
		ref.setOssKey(ossKey);
		ref.setReferrerType(refType.getValue());
		ref.setReferrerId(referrerId);
		ref.setCt(now);
		ref.setUt(now);
		aliyunOssReferenceExtDao.insert(ref);
	}

	/* (non-Javadoc)
	 * @see com.bloom.domain.oss.AliyunOssReferenceService#existOssRef(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existOssRef(String ossBucket, String ossKey) {
		return aliyunOssReferenceExtDao.countOssRef(ossBucket, ossKey) > 0;
	}

	/* (non-Javadoc)
	 * @see com.bloom.domain.oss.AliyunOssReferenceService#removeRef(com.bloom.domain.oss.meta.OssReferrerType, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public void removeRef(OssReferrerTypeEnum type, Integer referrerId, String ossBucket, String ossKey) {
		aliyunOssReferenceExtDao.removeRefs(type, referrerId, ossBucket, ossKey);
		boolean noOthersRef = ! this.existOssRef(ossBucket, ossKey);
		if (noOthersRef) {
			Oss.FileHandler.DELETE.delete(ossBucket, ossKey);
		}
	}

	/* (non-Javadoc)
	 * @see com.bloom.domain.oss.AliyunOssReferenceService#removeRefByReferrer(com.bloom.domain.oss.meta.OssReferrerType, java.lang.Integer)
	 */
	@Override
	public void removeRefByReferrer(OssReferrerTypeEnum type, Integer referrerId) {
		aliyunOssReferenceExtDao.listOssRefs(type, referrerId).forEach(ref -> {
			aliyunOssReferenceExtDao.deleteByPrimaryKey(ref.getId());
			boolean noOthersRef = ! this.existOssRef(ref.getOssBucket(), ref.getOssKey());
			if (noOthersRef) {
				Oss.FileHandler.DELETE.delete(ref.getOssBucket(), ref.getOssKey());
			}
		});
		
	}

	/* (non-Javadoc)
	 * @see com.bloom.domain.oss.AliyunOssReferenceService#listOssRefs(com.bloom.domain.oss.meta.OssReferrerTypeEnum, java.lang.Integer)
	 */
	@Override
	public List<AliyunOssReference> listOssRefs(OssReferrerTypeEnum type, Integer referrerId) {
		return aliyunOssReferenceExtDao.listOssRefs(type, referrerId);
	}

	/* (non-Javadoc)
	 * @see com.bloom.domain.oss.AliyunOssReferenceService#replaceRefs(com.bloom.domain.oss.meta.OssReferrerTypeEnum, java.lang.Integer, java.util.List)
	 */
	@Override
	@Transactional
	public void replaceRefs(OssReferrerTypeEnum type, Integer referrerId, List<OssRefDTO> refs) {
		List<AliyunOssReference> previousRefs = this.listOssRefs(type, referrerId);
		Set<String> previousRefSet = previousRefs.stream().map(r -> r.getOssBucket() + "," + r.getOssKey()).collect(Collectors.toSet());
		Set<String> enterRefSet = refs.stream().map(r -> r.getBucketName() + "," + r.getObjectKey()).collect(Collectors.toSet());
		// 新增集合
		Set<String> saveSet = new HashSet<>();
		// 删除集合
		Set<String> deleteSet = new HashSet<>();
		
		for (String ref : previousRefSet) {
			if (! enterRefSet.contains(ref)) {
				deleteSet.add(ref);
			}
		}
		for (String ref : enterRefSet) {
			if (! previousRefSet.contains(ref)) {
				saveSet.add(ref);
			}
		}
		saveSet.stream().forEach(ref -> {
			String[] array = ref.split(",");
			this.saveRef(type, referrerId, array[0], array[1]);
		});
		
		deleteSet.stream().forEach(ref -> {
			String[] array = ref.split(",");
			this.removeRef(type, referrerId, array[0], array[1]);
			// 删除之后判断文件是否还存在别的引用
			boolean noOthersRef = ! this.existOssRef(array[0], array[1]);
			if (noOthersRef) {
				Oss.FileHandler.DELETE.delete(array[0], array[1]);
			}
		});
	}

	
}
