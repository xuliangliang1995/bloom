package com.bloom.domain.petal.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloom.dao.ext.PetalInnerTextExtDao;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerText;
import com.bloom.domain.petal.PetalInnerTextService;
import com.bloom.exception.FlowBreakException;
@Service
public class PetalInnerTextServiceImpl implements PetalInnerTextService {
	@Autowired
	private PetalInnerTextExtDao petalInnerTextExtDao;

	@Override
	public PetalInnerText addPetalText(Petal petal, String text) {
		Date now = new Date();
		PetalInnerText innerText = new PetalInnerText();
		innerText.setPetalId(petal.getId());
		innerText.setText(text);
		innerText.setCt(now);
		innerText.setUt(now);
		petalInnerTextExtDao.insert(innerText);
		return innerText;
	}

	@Override
	public PetalInnerText findByPetalId(int petalId) {
		return Optional.ofNullable(
				petalInnerTextExtDao.findByPetalId(petalId)
				).orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
	}

}
