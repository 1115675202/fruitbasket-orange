package com.example.spring.boot.zhaoyun.module.core.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登陆
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Data
@Accessors(chain = true)
public class LoginQuery {

	/**
	 * 账户标识/账号
	 */
	private String identifier;

	/**
	 * 凭证/密码
	 */
	private String credential;
}
