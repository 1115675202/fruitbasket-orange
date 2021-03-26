package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.module.core.pojo.query.LoginQuery;
import com.fruitbasket.orange.module.core.repository.UserRepository;
import com.fruitbasket.orange.util.LoggerMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Service
public class UserService {

	private static final LoggerMediator logger = LoggerMediator.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public String login(LoginQuery query) {
		return null;
	}

	public void test() {
	}
}
