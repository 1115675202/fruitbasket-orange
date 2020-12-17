package com.example.spring.boot.zhaoyun.module.core.service;

import com.example.spring.boot.zhaoyun.module.core.pojo.entity.User;
import com.example.spring.boot.zhaoyun.module.core.pojo.entity.UserAccount;
import com.example.spring.boot.zhaoyun.module.core.repository.UserAccountRepository;
import com.example.spring.boot.zhaoyun.module.core.repository.UserMenuRepository;
import com.example.spring.boot.zhaoyun.module.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMenuRepository userMenuRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;


	@Test
	void test1() {
		User user = new User().setAvatarLink("").setSex(1).setRealName("zhaoyun").setBirthday(LocalDate.now()).setIdCardNo("123");
		UserAccount userAccount = new UserAccount().setCredential("123").setIdentifier("1233").setIdentityType(1);
		userAccount.setUser(user);
		userRepository.save(user);
		System.out.println("idididi" + user.getId());
		userAccountRepository.save(userAccount);
	}

	@Test
	void test2() {
		User user = userRepository.findById(6).orElse(null);
		System.out.println(user.getRealName());
		System.out.println(user.getUserAccounts().size());
	}
}