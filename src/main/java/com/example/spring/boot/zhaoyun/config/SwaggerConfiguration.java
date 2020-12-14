package com.example.spring.boot.zhaoyun.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 在线文档配置
 *
 * @author LiuBing
 * @date 2020/12/14
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration {

	@Value("${custom.swagger.oauth.client.client-id}")
	public String clientId;

	@Value("${custom.swagger.oauth.client.client-secret}")
	public String clientSecret;

	@Value("${custom.swagger.oauth.auth-server-url}")
	public String authServer;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// true-开放
				.enable(true)
				// 组名
				.groupName("default")
				.apiInfo(apiInfo())
				.select()
				// 加了 @Api 注解的控制器生成文档
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				// 过滤需要生成文档的请求路径，可以使用any()、none()、regex()或 ant()
				.paths(PathSelectors.any())
				.build()
				// OAuth安全API的Swagger UI配置
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts())
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

	/***
	 * 认证配置
	 **/
	private List<SecurityScheme> securitySchemes() {
		SecurityScheme securityScheme = new OAuthBuilder()
				.name("oauth2").grantTypes(grantTypes()).scopes(Arrays.asList(scopes())).build();
		return Collections.singletonList(securityScheme);
	}

	/**
	 * swagger2 认证的安全上下文
	 **/
	private List<SecurityContext> securityContexts() {
		List<SecurityReference> securityReferences = Collections
				.singletonList(new SecurityReference("oauth2", scopes()));
		SecurityContext securityContext = SecurityContext.builder()
				.securityReferences(securityReferences).forPaths(PathSelectors.any()).build();
		return Collections.singletonList(securityContext);
	}


	/**
	 * 设置认证cope
	 **/
	private AuthorizationScope[] scopes() {
		return new AuthorizationScope[]{
				new AuthorizationScope("ALL", "All scope is trusted!")
		};
	}


	/**
	 * 认证方式，密码模式
	 **/
	List<GrantType> grantTypes() {
		String tokenUrl = authServer + "/oauth/token";
		List<GrantType> grantTypes = new ArrayList<>(1);
		grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl));
		return grantTypes;
	}

	/**
	 * swagger默认使用的客户端账户
	 **/
	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.scopeSeparator("ALL")
				.useBasicAuthenticationWithAccessCodeGrant(true)
				.build();
	}
}
