package com.example.spring.boot.zhaoyun.module.user.service;

import com.example.spring.boot.zhaoyun.module.user.pojo.entity.User;
import com.example.spring.boot.zhaoyun.module.user.pojo.entity.UserAccount;
import com.example.spring.boot.zhaoyun.module.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void test1() {
//		userService.test();
		User user = new User().setAvatarLink("").setSex(1).setRealName("zhaoyun").setBirthday(LocalDate.now()).setIdCardNo("123");
		user.setUserAccountList(new ArrayList<UserAccount>(1) {{
			add(new UserAccount().setCredential("123").setIdentifier("1233").setIdentityType(1));
		}});
		userRepository.save(user);
	}
}