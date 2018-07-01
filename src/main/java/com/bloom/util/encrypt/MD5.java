package com.bloom.util.encrypt;

import org.springframework.util.DigestUtils;

/**
 * MD5加密
 * @author 83554
 *
 */
public class MD5 {
	private static final String EXTRA_TEXT = "xol4l2y2xx";
	/**
	 * MD5加密
	 * @param originalText
	 * @return 返回加密后的字符串
	 */
	public static String encrypt(String originalText) {
		return DigestUtils.md5DigestAsHex(originalText.concat(EXTRA_TEXT).getBytes());
	}
}
