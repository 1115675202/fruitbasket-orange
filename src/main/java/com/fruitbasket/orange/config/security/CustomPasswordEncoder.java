package com.fruitbasket.orange.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码编码器
 *
 * @author LiuBing
 * @date 2021/4/17
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
