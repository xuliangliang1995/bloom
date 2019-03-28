package com.bloom.domain.petal.listener;

public interface PetalFireListener {
	
	void listen();
	
	void notListen();
	
	void accept(PetalFireEvent fireEvent);

}
