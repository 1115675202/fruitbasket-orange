package com.fruitbasket.orange.module.core.controller;

import com.fruitbasket.orange.module.core.pojo.query.LoginQuery;
import com.fruitbasket.orange.module.core.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("user")
@RestController
public class UserController {

	private final UserService userService;

	/**
	 * 登录接口
	 * @param query 登录查询
	 * @return
	 */
	@PostMapping("login")
	public String login(@RequestBody LoginQuery query) {
		return userService.login(query);
	}

	public UserController(UserService userService) {
		this.userService = userService;
	}
}
