package cn.fruitbasket.orange.config.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加上此注解的 controller 方法返回结果将会被包装
 * UnifyResponseAdvice 指明了加了 @RestController 会统一包装
 * 这里为了解决加了 @Controller 的类，只有部分方法需要包装的情况，因为 controller 中可能包含返回文件或者页面的接口
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnifyResponse {
}
