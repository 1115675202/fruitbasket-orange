package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.module.core.pojo.query.LoginQuery;
import com.fruitbasket.orange.module.core.repository.UserRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class UserService {

	private final UserRep userRep;

	public String login(LoginQuery query) {
		log.info("asd", 1);
		return null;
	}

	public UserService(UserRep userRep) {
		this.userRep = userRep;
	}
}
