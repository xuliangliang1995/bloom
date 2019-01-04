/**
 * 
 */
package com.bloom.web.aliyunoss;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bloom.manager.aliyunoss.Oss;

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
	
	private static final String DEFAULT_IMAGE_BUCKET = "grasswort-petals-img";
	
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
			@RequestParam(value = "file")MultipartFile file) {
		try {
			return ResponseEntity.ok(
					Oss.FileHandler.UPLOAD.upload(DEFAULT_IMAGE_BUCKET, file.getName(), file.getBytes())
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
