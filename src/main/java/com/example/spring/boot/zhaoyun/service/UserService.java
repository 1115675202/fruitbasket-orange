package com.example.spring.boot.zhaoyun.service;

import com.example.spring.boot.zhaoyun.query.LoginQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Service
public class UserService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String login(LoginQuery query) {
		return null;
	}
}
