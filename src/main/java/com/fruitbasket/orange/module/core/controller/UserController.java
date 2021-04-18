package com.fruitbasket.orange.module.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.config.security.CustomUserDetails;
import com.fruitbasket.orange.module.core.pojo.vo.MenuTreeNodeVO;
import com.fruitbasket.orange.module.core.service.UserService;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("user")
@RestController
public class UserController {

	private final ObjectMapper objectMapper;

	private final UserService userService;

	@GetMapping("login/info")
	public CustomUserDetails login() {
		return userService.getLoginInfo();
	}

	@GetMapping("permission/tree")
	public List<MenuTreeNodeVO> getPermissionTree() {
		return userService.getPermissionTree();
	}

	public UserController(ObjectMapper objectMapper, UserService userService) {
		this.objectMapper = objectMapper;
		this.userService = userService;
	}
}
