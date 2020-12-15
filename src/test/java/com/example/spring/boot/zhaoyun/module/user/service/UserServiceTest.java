package com.example.spring.boot.zhaoyun.module.user.service;

import com.example.spring.boot.zhaoyun.module.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void test1() {
		userService.test();
//		userRepository.delete(userRepository.findUserByRealName("zhaoyun"));
	}
}