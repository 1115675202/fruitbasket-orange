package com.fruitbasket.orange.config.security;

import com.fruitbasket.orange.module.core.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义用户登陆信息处理
 *
 * @author LiuBing
 * @date 2021/4/17
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new CustomUserDetails()
                .setUserId(1)
                .setPassword("admin")
                .setPassword("admin");
    }

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }
}
