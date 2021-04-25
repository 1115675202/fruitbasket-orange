package com.fruitbasket.orange.module.rbac.service;

import com.fruitbasket.orange.module.rbac.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRep userRep;

//	@Autowired
//	private UserMenuRep userMenuRep;


//	@Test
//	void test1() {
//		User user = new User().setAvatarLink("").setSex(1).setRealName("zhaoyun").setBirthday(LocalDate.now()).setIdCardNo("123");
//		userAccount.setUser(user);
//		userRepository.save(user);
//		System.out.println("idididi" + user.getId());
//		userAccountRepository.save(userAccount);
//	}
//
//	@Test
//	void test2() {
//		User user = userRepository.findById(1).orElse(null);
//		System.out.println(user.getRealName());
//		System.out.println(user.getUserAccounts().size());
//	}
}