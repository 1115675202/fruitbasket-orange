package com.example.spring.boot.zhaoyun.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 包装请求
 *
 * @author LiuBing
 * @date 2020/12/2
 */
@Component
public class RequestWrapperFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (isPost(httpServletRequest) && !isFile(httpServletRequest)) {
			httpServletRequest = new MultiReadHttpServletRequestWrapper(httpServletRequest);
		}
		filterChain.doFilter(httpServletRequest, response);
	}

	private static boolean isPost(HttpServletRequest request) {
		return HttpMethod.POST.matches(request.getMethod());
	}

	private static boolean isFile(ServletRequest request) {
		return request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE);
	}
}
