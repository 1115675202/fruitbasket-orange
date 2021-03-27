package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.module.core.repository.UserAccountRep;
import com.fruitbasket.orange.module.core.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRep userRep;

	@Autowired
	private UserMenuRep userMenuRep;

	@Autowired
	private UserAccountRep userAccountRep;


//	@Test
//	void test1() {
//		User user = new User().setAvatarLink("").setSex(1).setRealName("zhaoyun").setBirthday(LocalDate.now()).setIdCardNo("123");
//		UserAccount userAccount = new UserAccount().setCredential("123").setIdentifier("1233").setIdentityType(1);
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