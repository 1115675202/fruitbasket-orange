package com.fruitbasket.orange.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.response.ResponseVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

import static com.fruitbasket.orange.response.ResponseCodeEnum.ACCESS_DENIED;
import static com.fruitbasket.orange.response.ResponseCodeEnum.LOGIN_ERROR;

/**
 * 登陆认证配置
 *
 * @author LiuBing
 * @date 2021/4/17
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;

    private final CustomUserDetailsService customUserDetailsService;

    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .httpBasic()
                // 未登录
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                // 必须授权的范围
                .anyRequest().authenticated()
                .and()
                // 使用自带的登录
                .formLogin()
                .permitAll()
                // 登录失败
                .failureHandler(authenticationFailureHandler())
                // 登录成功
                .successHandler(authenticationSuccessHandler())
                .and()
                // 无访问权限
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and().logout()
                // 登出成功
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll();

        // 开启跨域访问
        http.cors().disable();
        // 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        // 对于在 header 里面增加 token 等类似情况，放行所有 OPTIONS 请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    /**
     * @return 登出成功
     */
    LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, e) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.successOf("登出成功")));
            out.close();
        };
    }

    /**
     * @return 无访问权限
     */
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.of(ACCESS_DENIED)));
            out.close();
        };
    }

    /**
     * @return 登陆成功响应扩展点
     */
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.success()));
            out.close();
        };
    }

    /**
     * @return 登陆失败响应扩展点
     */
    AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, e) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.of(LOGIN_ERROR, e.getMessage())));
            out.close();
        };
    }

    /**
     * @return 未登录响应扩展点
     */
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(ResponseVO.of(LOGIN_ERROR, "未登录")));
            out.close();
        };
    }

    /**
     * @return 登陆认证器，用自定义的类进行覆盖
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(customPasswordEncoder);
        return authenticationProvider;
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
