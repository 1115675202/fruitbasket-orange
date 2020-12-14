package com.example.spring.boot.zhaoyun.module.user.pojo.query;

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

	private String identifier;

	private String credential;
}
