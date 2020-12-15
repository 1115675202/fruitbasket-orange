package com.example.spring.boot.zhaoyun.module.user.service;

import com.example.spring.boot.zhaoyun.module.user.pojo.entity.User;
import com.example.spring.boot.zhaoyun.module.user.pojo.query.LoginQuery;
import com.example.spring.boot.zhaoyun.module.user.repository.UserRepository;
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

	@Autowired
	private UserRepository userRepository;

	public String login(LoginQuery query) {
		return null;
	}

	public void test() {
		userRepository.save(
				new User().setIdCardNo("1232").setRealName("zhaoyun2")
		.setAvatarLink("").setBirthday(LocalDate.now()).setSex(1));
	}
}
