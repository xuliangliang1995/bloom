package com.bloom.util.general;
/**
 * 对Null值进行转化
 * @author 83554
 *
 */
public class NotNull {
	
	public static int of(Integer number) {
		return null == number
				?0:number.intValue();
	}
	
	public static String of(String text) {
		return null == text
				?"":text.trim();
	}
	
	public static boolean of(Boolean bo) {
		return null == bo
				?false:bo;
	}
	
}
