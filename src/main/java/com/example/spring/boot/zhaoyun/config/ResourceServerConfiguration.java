//package com.example.spring.boot.zhaoyun.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * 资源服务器配置
// *
// * @author LiuBing
// * @date 2020/12/14
// */
//@EnableResourceServer
//@Configuration
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId("zhaoyun").stateless(true);
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll() //跨域认证
//				.antMatchers(HttpMethod.POST,  "/user").permitAll() //注册
//				.antMatchers(
//						"/webjars/**",
//						"/resources/**",
//						"/swagger-ui.html",
//						"/swagger-resources/**",
//						"/v2/api-docs").permitAll() //swagger相关
//				.antMatchers("/actuator/**").permitAll() //健康检查、审计、统计和监控
//				.antMatchers("/favicon.ico").permitAll() //spring图标
//				.antMatchers("/example/**").permitAll() //示例接口
//				.antMatchers("/environment/**").hasAnyRole("ADMIN") //运行环境信息
//				.anyRequest().authenticated()//任何请求都需要身份认证
////                .anyRequest().permitAll()//任何请求都需要身份认证
//		;
//	}
//}
