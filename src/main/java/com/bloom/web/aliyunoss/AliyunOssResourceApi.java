/**
 * 
 */
package com.bloom.web.aliyunoss;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bloom.exception.FlowBreakException;
import com.bloom.manager.aliyunoss.Oss;
import com.bloom.manager.aliyunoss.constant.OssStipulation;
import com.bloom.manager.aliyunoss.util.OssUtils;

/**
 * <p>Title: AliyunOssResourceApi.java<／p>
 * <p>Description: 阿里云OSS对象存储<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月4日
 * @version 1.0
*/
@RestController
@RequestMapping("/aliyun/oss")
public class AliyunOssResourceApi {
	
	/**
	 * 
	 * <p>Title: uploadImage</p>
	 * <p>Description: 上传图片</p>
	 * @param file
	 * @return
	 * ResponseEntity<?>
	 */
	@PostMapping("/upload/image")
	public ResponseEntity<?> uploadImage(
			@RequestParam(value = "file")MultipartFile file,
			HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String objectName = OssUtils.generateOssKeyName().concat(suffix);
		try {
			Oss.FileHandler.UPLOAD.upload(OssStipulation.DEFAULT_BUCKET_NAME, objectName, file.getBytes());
			return ResponseEntity.ok(
					OssUtils.replenishOssUrl(OssStipulation.DEFAULT_BUCKET_NAME, objectName, OssStipulation.DefaultBucketDisposeStyle.TARGET)
					);
		} catch (IOException e) {
			throw new FlowBreakException("图片上传失败！");
		}
	}
}
