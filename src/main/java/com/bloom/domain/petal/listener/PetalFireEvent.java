package com.bloom.domain.petal.listener;

import com.bloom.dao.po.Petal;

/**
 * 
 * @author xuliangliang
 *
 */
public class PetalFireEvent {
	
	private Petal petal;

	public PetalFireEvent(Petal petal) {
		super();
		this.petal = petal;
	}

	public Petal getPetal() {
		return petal;
	}

	public void setPetal(Petal petal) {
		this.petal = petal;
	}
	
	
	

}
