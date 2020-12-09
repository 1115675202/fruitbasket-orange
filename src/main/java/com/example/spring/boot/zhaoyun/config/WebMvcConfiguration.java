package com.example.spring.boot.zhaoyun.config;

import com.example.spring.boot.zhaoyun.filter.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @author LiuBing
 * @date 2020/12/2
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
				.addPathPatterns("/**")
		;
	}
}
