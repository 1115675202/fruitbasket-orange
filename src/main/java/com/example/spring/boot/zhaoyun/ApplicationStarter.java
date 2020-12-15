package com.example.spring.boot.zhaoyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 服务启动类
 *
 * @author LiuBing
 * @date 2020/12/7
 */
@EnableJpaAuditing
@SpringBootApplication
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}
}
