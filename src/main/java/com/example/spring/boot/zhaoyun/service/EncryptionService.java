package com.example.spring.boot.zhaoyun.service;

import com.example.spring.boot.zhaoyun.util.encrypt.Encryptor;
import com.example.spring.boot.zhaoyun.vo.SecretKeyOneOffVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 加密
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Service
public class EncryptionService {

	Map<String, String> secretKeyMap = new HashMap<>();

	public SecretKeyOneOffVO saveSecretKey() throws Exception {
		String secretKeyId = UUID.randomUUID().toString();
		String secretKey = Encryptor.initKey();
		secretKeyMap.put(secretKeyId, secretKey);
		return new SecretKeyOneOffVO().setSecretKeyId(secretKeyId).setSecretKey(secretKey);
	}
}
