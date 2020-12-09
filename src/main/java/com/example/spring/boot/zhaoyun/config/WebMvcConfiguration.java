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

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
//				.allowedOrigins("http://localhost")
//				.allowedOriginPatterns("/**")
				.allowedHeaders("*")
				.allowedMethods("*")
				.maxAge(3600)
				.allowCredentials(true);
	}
}
