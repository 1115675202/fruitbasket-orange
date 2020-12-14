package com.example.spring.boot.zhaoyun.module.encrypt.controller;

import com.example.spring.boot.zhaoyun.module.encrypt.api.EncryptionApi;
import com.example.spring.boot.zhaoyun.module.encrypt.pojo.query.SecretKeyOneOffVO;
import com.example.spring.boot.zhaoyun.module.encrypt.service.EncryptionService;
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
