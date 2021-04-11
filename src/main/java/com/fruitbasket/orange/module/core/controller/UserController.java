package com.fruitbasket.orange.module.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.module.core.pojo.query.LoginQuery;
import com.fruitbasket.orange.module.core.pojo.vo.PermissionVO;
import com.fruitbasket.orange.module.core.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * 登录接口
	 * @param query 登录查询
	 * @return
	 */
	@PostMapping("login")
	public String login(@RequestBody LoginQuery query) {
		return userService.login(query);
	}

	@GetMapping("test")
	public List<PermissionVO> test() throws JsonProcessingException {
		return objectMapper.readValue("[{\"id\":1,\"permissionName\":\"系统\",\"permissionUrl\":\"\",\"children\":[{\"id\":2,\"permissionName\":\"用户\",\"permissionUrl\":\"user\"},{\"id\":3,\"permissionName\":\"权限\",\"permissionUrl\":\"role\"}]},{\"id\":4,\"permissionName\":\"模块\",\"permissionUrl\":\"user\"}]"
				, new TypeReference<List<PermissionVO>>() { });
	}

	public UserController(ObjectMapper objectMapper, UserService userService) {
		this.objectMapper = objectMapper;
		this.userService = userService;
	}
}
