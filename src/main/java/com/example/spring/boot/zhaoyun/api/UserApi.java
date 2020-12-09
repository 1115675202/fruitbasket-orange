package com.example.spring.boot.zhaoyun.api;

import com.example.spring.boot.zhaoyun.query.LoginQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("user")
public interface UserApi {

	@PostMapping("login")
	String login(LoginQuery loginQuery);
}
