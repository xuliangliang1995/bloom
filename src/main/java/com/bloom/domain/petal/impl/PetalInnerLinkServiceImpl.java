package com.bloom.domain.petal.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bloom.dao.ext.PetalInnerLinkExtDao;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerLink;
import com.bloom.domain.petal.PetalInnerLinkService;
import com.bloom.exception.ServiceException;
@Service
public class PetalInnerLinkServiceImpl implements PetalInnerLinkService {
	@Autowired
	private PetalInnerLinkExtDao petalInnerLinkExtDao;

	@Override
	public PetalInnerLink addPetalLink(Petal petal, String link) {
		Date now = new Date();
		PetalInnerLink innerLink = new PetalInnerLink();
		innerLink.setPetalId(petal.getId());
		innerLink.setLink(link);
		innerLink.setCt(now);
		innerLink.setUt(now);
		petalInnerLinkExtDao.insert(innerLink);
		return innerLink;
	}

	@Override
	public PetalInnerLink findByPetalId(int petalId) {
		return Optional.ofNullable(petalInnerLinkExtDao.findByPetalId(petalId))
				.orElseThrow(() -> new ServiceException("资源不存在或已被删除！"));
	}
	
	@Override
	@Transactional
	public void deletePetalInnerLink(int petalId) {
		petalInnerLinkExtDao.deleteByPetalId(petalId);
	}

}
