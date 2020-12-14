package com.example.spring.boot.zhaoyun.module.user.controller;

import com.example.spring.boot.zhaoyun.module.user.api.UserApi;
import com.example.spring.boot.zhaoyun.module.user.pojo.query.LoginQuery;
import com.example.spring.boot.zhaoyun.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RestController
public class UserController implements UserApi {

	@Autowired
	private UserService userService;

	@Override()
	public String login(@RequestBody LoginQuery query) {
		return userService.login(query);
	}
}
