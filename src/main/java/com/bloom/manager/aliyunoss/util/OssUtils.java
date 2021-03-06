/**
 * 
 */
package com.bloom.manager.aliyunoss.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

import com.bloom.manager.aliyunoss.constant.OssStipulation;
import com.bloom.manager.aliyunoss.dto.OssRefDTO;
import com.bloom.util.encrypt.MD5;

/**
 * <p>Title: OssUrlResolver.java<／p>
 * <p>Description: OSS地址解析<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
public class OssUtils {
	/**
	 *  已经编译好的正则表达式存放处
	 */
	private static Map<String, Pattern> precompilePatternsMap = new HashMap<>();
	/**
	 * 
	 * <p>Title: generateOssKeyName</p>
	 * <p>Description: 生成OSS的objectName（不包含后缀以及“.”）</p>
	 * @return
	 * String
	 */
	public static String generateOssKeyName() {
		StringBuffer sbf = new StringBuffer();
		sbf.append(System.currentTimeMillis());
		sbf.append(Thread.currentThread().getName());
		sbf.append(RandomStringUtils.randomAlphabetic(10));
		return MD5.encrypt(sbf.toString());
	}
	/**
	 * 
	 * <p>Title: replenishOssUrl</p>
	 * <p>Description: 补全OSSurl</p>
	 * @return
	 * String
	 */
	public static String replenishOssUrl(String bucketName, String objectName, String disposeStyle) {
		return String.format(OssStipulation.OSS_URL_TEMPLATE, bucketName, objectName, disposeStyle);
	}

	/**
	 * 
	 * <p>Title: findOssUrlFromText</p>
	 * <p>Description: 从文本中寻找OSSurl</p>
	 * @param text
	 * @param bucketName
	 * @param disposeStyle
	 * @return
	 * List<String>
	 */
	public static List<OssRefDTO> findOssUrlFromText(String text, String bucketName, String disposeStyle) {
		String reg = replenishOssUrl(bucketName, OssStipulation.OSS_KEY32_NAME_REGEX, disposeStyle);
		Pattern pattern = precompilePatternsMap.get(reg);
		if (null == pattern) {
			pattern = Pattern.compile(reg);
			precompilePatternsMap.put(reg, pattern);
		}
		// 获取匹配器
		Matcher matcher = pattern.matcher(text); 
	    List<String> list = new ArrayList<>();
	    while (matcher.find()) { 
	      list.add(matcher.group());
	    } 
		return list.stream().map(url -> resolverUrl(url, bucketName, disposeStyle)).collect(Collectors.toList());
	}
	/**
	 * 
	 * <p>Title: resolverUrl</p>
	 * <p>Description: 解析OSS Url,私有方法保证url的格式</p>
	 * @param url
	 * @return
	 * OssRefDTO
	 */
	private static OssRefDTO resolverUrl(String url, String bucketName, String disposeStyle) {
		String prefix = String.format(OssStipulation.OSS_URL_PREFIX_TEMPLATE, bucketName);
		String objectName = url.replaceFirst(prefix, "").replaceFirst(disposeStyle, "");
		OssRefDTO ref = new OssRefDTO();
		ref.setBucketName(bucketName);
		ref.setObjectKey(objectName);
		ref.setUrl(url);
		return ref;
	}
	
	static {
		// 预编译经常用的正则表达式
		String reg1 = replenishOssUrl(OssStipulation.DEFAULT_BUCKET_NAME, OssStipulation.OSS_KEY32_NAME_REGEX, OssStipulation.DefaultBucketDisposeStyle.TARGET);
		Pattern p1  = Pattern.compile(reg1);
		precompilePatternsMap.put(reg1, p1);
		
		String reg2 = replenishOssUrl(OssStipulation.DEFAULT_BUCKET_NAME, OssStipulation.OSS_KEY32_NAME_REGEX, OssStipulation.DefaultBucketDisposeStyle.COMPRESS);
		Pattern p2  = Pattern.compile(reg2);
		precompilePatternsMap.put(reg2, p2);
	}
}
