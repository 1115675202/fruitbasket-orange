package com.fruitbasket.orange.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.response.ResponseVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.fruitbasket.orange.response.ResponseCode.ACCESS_DENIED;
import static com.fruitbasket.orange.response.ResponseCode.LOGIN_ERROR;

/**
 * 登陆认证配置
 *
 * @author LiuBing
 * @date 2021/4/17
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    private final ObjectMapper objectMapper;

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    public void configure(WebSecurity web) {
        // 放行的请求
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(customPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
                // 未登录
                .authenticationEntryPoint(authenticationEntryPoint())

                .and().formLogin()
                // 登录失败
                .failureHandler(authenticationFailureHandler())
                // 登录成功
                .successHandler(authenticationSuccessHandler())
                .permitAll()

                .and().exceptionHandling()
                // 无访问权限
                .accessDeniedHandler(accessDeniedHandler())
        ;

        // 登出
        http.logout().permitAll().logoutSuccessHandler(logoutSuccessHandler());

        // 开启跨域访问
        http.cors().disable();
        // 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    /**
     * @return 登出成功
     */
    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, e) -> {
            response.setContentType(CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.SUCCESS));
            out.close();
        };
    }

    /**
     * @return 无访问权限
     */
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            response.setContentType(CONTENT_TYPE_JSON);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.of(ACCESS_DENIED)));
            out.close();
        };
    }

    /**
     * @return 登陆成功响应扩展点
     */
    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setContentType(CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.SUCCESS));
            out.close();
        };
    }

    /**
     * @return 登陆失败响应扩展点
     */
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, e) -> {
            response.setContentType(CONTENT_TYPE_JSON);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.of(LOGIN_ERROR, e.getMessage())));
            out.close();
        };
    }

    /**
     * @return 未登录响应扩展点
     */
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setContentType(CONTENT_TYPE_JSON);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.of(LOGIN_ERROR, "未登录")));
            out.close();
        };
    }

    public WebSecurityConfiguration(
            ObjectMapper objectMapper,
            CustomUserDetailsService customUserDetailsService,
            CustomPasswordEncoder customPasswordEncoder) {
        this.objectMapper = objectMapper;
        this.customUserDetailsService = customUserDetailsService;
        this.customPasswordEncoder = customPasswordEncoder;
    }
}
