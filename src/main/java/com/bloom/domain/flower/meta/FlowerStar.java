package com.bloom.domain.flower.meta;
/***
 * 花er星级
 * @author 83554
 *
 */
public enum FlowerStar {
	Star_1(1),
	Star_2(2),
	Star_3(3),
	Star_4(4),
	Star_5(5);
	private byte value;
	
	private FlowerStar(int value) {
		this.value = (byte)value;
	}

	public byte value() {
		return value;
	}
	

}
