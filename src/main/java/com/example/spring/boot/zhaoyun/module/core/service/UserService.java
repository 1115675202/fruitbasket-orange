package com.example.spring.boot.zhaoyun.module.core.service;

import com.example.spring.boot.zhaoyun.module.core.pojo.entity.User;
import com.example.spring.boot.zhaoyun.module.core.pojo.query.LoginQuery;
import com.example.spring.boot.zhaoyun.module.core.repository.UserRepository;
import com.example.spring.boot.zhaoyun.util.LoggerMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
