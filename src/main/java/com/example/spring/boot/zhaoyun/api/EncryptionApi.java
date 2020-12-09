package com.example.spring.boot.zhaoyun.api;

import com.example.spring.boot.zhaoyun.vo.SecretKeyOneOffVO;
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
