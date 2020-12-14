package com.example.spring.boot.zhaoyun.module.encrypt.api;

import com.example.spring.boot.zhaoyun.module.encrypt.pojo.query.SecretKeyOneOffVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 加密
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("encrypt")
public interface EncryptionApi {

	@GetMapping("secret")
	SecretKeyOneOffVO getSecret() throws Exception;
}
