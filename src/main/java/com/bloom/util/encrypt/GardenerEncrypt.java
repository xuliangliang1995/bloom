package com.bloom.util.encrypt;
/**
 * Gardener专用加密方式
 * @author 83554
 *
 */
public class GardenerEncrypt {
	/**
	 * 用户名加密
	 * @return
	 */
	public static String encryptUsername(String originalUsername) {
		return MD5.encrypt(originalUsername);
	}
	/**
	 * 用户名密码加密
	 * @param key 数据库中主键key
	 * @param originalUsername 用户名（未加密）
	 * @param originalPassword 密码（未加密）
	 * @return 加密后密码
	 */
	public static String encryptPassword(Integer key,String originalUsername,String originalPassword) {
		return MD5.encrypt(String.format("%s%d%s", originalUsername,
				Integer.valueOf(originalUsername.getBytes()[0])*key*Integer.valueOf(originalPassword.getBytes()[0]),
				originalPassword));
	}
}
