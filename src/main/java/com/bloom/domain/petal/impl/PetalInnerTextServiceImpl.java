package com.bloom.domain.petal.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.PetalInnerTextExtDao;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerTextWithBLOBs;
import com.bloom.domain.CachedName;
import com.bloom.domain.oss.AliyunOssReferenceService;
import com.bloom.domain.oss.meta.OssReferrerTypeEnum;
import com.bloom.domain.petal.PetalInnerTextService;
import com.bloom.exception.FlowBreakException;
import com.bloom.manager.aliyunoss.constant.OssStipulation;
import com.bloom.manager.aliyunoss.dto.OssRefDTO;
import com.bloom.manager.aliyunoss.util.OssUtils;
@Service
public class PetalInnerTextServiceImpl implements PetalInnerTextService {
	@Resource
	private PetalInnerTextExtDao petalInnerTextExtDao;
	
	@Resource
	private AliyunOssReferenceService aliyunOssReferenceServiceImpl;

	@Override
	@CachePut(cacheNames = CachedName.PETAL_TEXT, key = "#petal.id")
	public PetalInnerTextWithBLOBs addPetalText(Petal petal, String text, String raw) {
		Date now = new Date();
		PetalInnerTextWithBLOBs innerText = new PetalInnerTextWithBLOBs();
		innerText.setPetalId(petal.getId());
		innerText.setText(text);
		innerText.setRaw(raw);
		innerText.setCt(now);
		innerText.setUt(now);
		petalInnerTextExtDao.insert(innerText);
		
		// 添加Oss图片引用
		List<OssRefDTO> ossRefs = OssUtils.findOssUrlFromText(raw, 
				OssStipulation.DEFAULT_BUCKET_NAME, OssStipulation.DefaultBucketDisposeStyle.TARGET);
		ossRefs.stream().forEach(r -> {
			aliyunOssReferenceServiceImpl.saveRef(OssReferrerTypeEnum.PETAL, petal.getId(), 
					r.getBucketName(), r.getObjectKey());
		});
		return innerText;
	}

	@Override
	@Cacheable(cacheNames = CachedName.PETAL_TEXT, key = "#petalId")
	public PetalInnerTextWithBLOBs findByPetalId(int petalId) {
		return Optional.ofNullable(
				petalInnerTextExtDao.findByPetalId(petalId)
				).orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
	}

	@Override
	@CacheEvict(cacheNames = CachedName.PETAL_TEXT, key = "#petalId")
	@Transactional
	public void deletePetalInnerText(int petalId) {
		petalInnerTextExtDao.deleteByPetalId(petalId);
		aliyunOssReferenceServiceImpl.removeRefByReferrer(OssReferrerTypeEnum.PETAL, petalId);
	}

	/* (non-Javadoc)
	 * @see com.bloom.domain.petal.PetalInnerTextService#editPetalText(com.bloom.dao.po.Petal, java.lang.String, java.lang.String)
	 */
	@Override
	@CachePut(cacheNames = CachedName.PETAL_TEXT, key = "#petal.id")
	public PetalInnerTextWithBLOBs editPetalText(Petal petal, String text, String raw) {
		// 删除旧的
		petalInnerTextExtDao.deleteByPetalId(petal.getId());
		Date now = new Date();
		// 添加新的
		PetalInnerTextWithBLOBs innerText = new PetalInnerTextWithBLOBs();
		innerText.setPetalId(petal.getId());
		innerText.setText(text);
		innerText.setRaw(raw);
		innerText.setCt(now);
		innerText.setUt(now);
		petalInnerTextExtDao.insert(innerText);
		
		// 替换Oss图片引用
		List<OssRefDTO> ossRefs = OssUtils.findOssUrlFromText(raw, 
				OssStipulation.DEFAULT_BUCKET_NAME, OssStipulation.DefaultBucketDisposeStyle.TARGET);
		
		aliyunOssReferenceServiceImpl.replaceRefs(OssReferrerTypeEnum.PETAL, petal.getId(), ossRefs);
		return innerText;
	}
}
