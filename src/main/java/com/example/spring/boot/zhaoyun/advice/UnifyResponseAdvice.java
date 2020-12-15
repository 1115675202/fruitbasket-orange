package com.example.spring.boot.zhaoyun.advice;

import com.example.spring.boot.zhaoyun.annotation.UnifyResponse;
import com.example.spring.boot.zhaoyun.exception.BusinessException;
import com.example.spring.boot.zhaoyun.response.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * 统一响应
 *
 * @author LiuBing
 * @date 2020/11/28
 */
@RestControllerAdvice(annotations = {UnifyResponse.class})
public class UnifyResponseAdvice implements ResponseBodyAdvice<Object> {

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * 判断是否拦截处理
	 *
	 * @param methodParameter
	 * @param aClass
	 * @return true/false-执行/不执行 beforeBodyWrite
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		Type type = methodParameter.getGenericParameterType();
		if (ResultVO.class.equals(type)) {
			return false;
		}
		return true;
	}

	/**
	 * 返回前包装数据
	 */
	@Override
	public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType,
								  Class<? extends HttpMessageConverter<?>> aClass,
								  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		if (data instanceof String) {
			return stringResponseConvert(data);
		}
		return ResultVO.successOf(data);
//		String str = null;
//		try {
//			str = new ResponseEncodeHandler(stringResponseConvert(data)).secretKeyStr("WW7Inwxq1rPPRMFSdJbD/3h270Qd5XrJmETeboDSy8g=").encodData().getEncodedData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(str);
//		return str;
	}

	/**
	 * String 类型需要特殊处理，否则会抛出异常 java.lang.ClassCastException
	 *
	 * @param data
	 * @return 包装后的 JSON 数据
	 */
	private String stringResponseConvert(Object data) {
		try {
			return objectMapper.writeValueAsString(ResultVO.successOf(data));
		} catch (JsonProcessingException e) {
			throw new BusinessException("");
		}
	}
}