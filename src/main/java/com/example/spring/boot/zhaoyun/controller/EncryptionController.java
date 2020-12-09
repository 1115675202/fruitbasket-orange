package com.example.spring.boot.zhaoyun.controller;

import com.example.spring.boot.zhaoyun.api.EncryptionApi;
import com.example.spring.boot.zhaoyun.service.EncryptionService;
import com.example.spring.boot.zhaoyun.vo.SecretKeyOneOffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加密
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RestController
public class EncryptionController implements EncryptionApi {

	@Autowired
	private EncryptionService encryptionService;

	@Override
	public SecretKeyOneOffVO getSecret() throws Exception {
		return encryptionService.saveSecretKey();
	}
}
