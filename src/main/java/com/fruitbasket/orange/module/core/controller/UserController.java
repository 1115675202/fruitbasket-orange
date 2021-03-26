package com.fruitbasket.orange.module.core.controller;

import com.fruitbasket.orange.module.core.api.UserApi;
import com.fruitbasket.orange.module.core.pojo.query.LoginQuery;
import com.fruitbasket.orange.module.core.service.UserService;
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
