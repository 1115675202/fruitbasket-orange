package com.example.spring.boot.zhaoyun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加此注解的接口不需要登陆授权
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WithoutAuthorization {
}
