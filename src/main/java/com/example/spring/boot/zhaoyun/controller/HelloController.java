package com.example.spring.boot.zhaoyun.controller;

import com.example.spring.boot.zhaoyun.api.HelloApi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author LiuBing
 * @date 2020/11/28
 */
@RestController
public class HelloController implements HelloApi {

	@Override
	public String string() {
		return "中文";
	}

	@Override
	public LocalDateTime time() {
		return LocalDateTime.now();
	}

	@Override
	public Date date(Date date) {
		return date;
	}

	@Override
	public Hello hello(Hello hello) {
		return hello;
	}

	@Override
	public Hello encry(@RequestBody Hello hello) {
		return hello;
	}
}
