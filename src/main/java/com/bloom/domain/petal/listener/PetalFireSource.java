package com.bloom.domain.petal.listener;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bloom.dao.po.Petal;

@Component
public class PetalFireSource {
	
	private final Set<PetalFireListener> listeners = new HashSet<PetalFireListener>();
	
	
	public void register(PetalFireListener listener) {
		listeners.add(listener);
	}
	
	public void delete(PetalFireListener listener) {
		listeners.remove(listener);
	}
	
	public void fire(Petal petal){
		final PetalFireEvent event = new PetalFireEvent(petal);
		listeners.stream().forEach(listener -> listener.accept(event));
	}
}
