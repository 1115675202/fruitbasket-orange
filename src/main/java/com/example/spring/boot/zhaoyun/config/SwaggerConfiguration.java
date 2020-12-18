package com.example.spring.boot.zhaoyun.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 在线文档配置
 *
 * @author LiuBing
 * @date 2020/12/14
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
				// 组名
				.groupName("default")
				.apiInfo(apiInfo())
				.select()
				/**
				 * 加了 @RestController 注解的控制器生成文档
				 * 这个配置下，swagger 只能识别当前类上的注解，而不能识别父类的
				 */
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				// 过滤需要生成文档的请求路径，可以使用any()、none()、regex()或 ant()
//				.paths(PathSelectors.any())
				.build()
				;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("XX模块")
				.description("XX模块描述")
//                .contact(new Contact("名字", "网址", "邮箱"))
//                服务条款网址
//                .termsOfServiceUrl("http://xxxx")
//                .license("license")
//                .licenseUrl("url")
				.build();
	}
}
