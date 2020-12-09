package com.example.spring.boot.zhaoyun.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.spring.boot.zhaoyun.response.BusinessException;
import com.example.spring.boot.zhaoyun.advice.WithoutAuthorization;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 验签
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (withoutAuthorization(handler)) {
			return true;
		}
		return authentication(request);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	private boolean withoutAuthorization(Object handler) {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(WithoutAuthorization.class)) {
			return true;
		}

		return false;
	}

	private boolean authentication(HttpServletRequest request) {
		//检查有没有需要用户权限的注解
		String token = request.getHeader("token");

		if (Objects.isNull(token)) {
			throw new BusinessException("The token cannot be empty.");
		}

		String identifier = JWT.decode(token).getAudience().get(0);
		if (!StringUtils.hasText(identifier)) {
			throw new BusinessException("The user does not exist");
		}

		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("credentials")).build();
		jwtVerifier.verify(token);
		return true;
	}
}
