package com.bloom.domain.petal.factory;

import java.util.Date;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalProgress;
import com.bloom.dao.po.RetentionCurve;

/**
 * 
 * @author xuliangliang
 *
 */
public class PetalProgressFactory {
	
	private static final long MILLISECOND_PER_MINUTE = 60000;
	
	public static PetalProgress create(Petal petal, RetentionCurve curve) {
		Date now = new Date();
		PetalProgress progress = new PetalProgress();
		progress.setPetalId(petal.getId());
		progress.setGardenerId(petal.getGardenerId());
		progress.setRetentionCurveId(curve.getId());
		progress.setFireTime(new Date(now.getTime()+curve.getIntervalMinutes()*MILLISECOND_PER_MINUTE));
		progress.setCt(now);
		progress.setFire((byte)0);
		return progress;
	}
	

}
