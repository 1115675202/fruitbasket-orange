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

	private UserService userService;

	@PostMapping("login")
	public String login(@RequestBody LoginQuery query) {
		return userService.login(query);
	}

	public UserController(UserService userService) {
		this.userService = userService;
	}
}
