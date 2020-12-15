package com.example.spring.boot.zhaoyun.module.example.api;

import com.example.spring.boot.zhaoyun.module.example.controller.Hello;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author LiuBing
 * @date 2020/11/28
 */
@Api
public interface HelloApi {

	@GetMapping("string")
	String string();

	@GetMapping("time")
	LocalDateTime time();

	@GetMapping("date")
	Date date(Date date);

	@GetMapping("hello")
	Hello hello(Hello hello);

	@PostMapping("encry")
	Hello encry(@RequestBody Hello hello);
}
