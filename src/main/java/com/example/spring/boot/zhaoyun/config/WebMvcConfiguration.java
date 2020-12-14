package com.example.spring.boot.zhaoyun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @author LiuBing
 * @date 2020/12/2
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {



	/**
	 * 跨域请求
	 *
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// 生效的请求路径
				.addMapping("/**")
				// 允许的请求来源（域名/IP+端口号）
				.allowedOriginPatterns("/**")
				// 允许的请求头
				.allowedHeaders("*")
				// 允许的 HTTP 请求方法
				.allowedMethods("*")
				// 本次预检请求的有效期
				.maxAge(3600)
		;
	}
}
