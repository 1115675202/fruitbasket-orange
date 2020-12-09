package com.example.spring.boot.zhaoyun.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 一次性秘钥
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Data
@Accessors(chain = true)
public class SecretKeyOneOffVO {

	/**
	 * 秘钥ID
	 */
	private String SecretKeyId;

	/**
	 * 秘钥
	 */
	private String SecretKey;
}
