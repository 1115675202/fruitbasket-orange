package com.fruitbasket.orange.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加上此注解的controller方法返回结果将会被包装
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnifyResponse {
}
