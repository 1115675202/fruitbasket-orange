//package com.example.rest.advice;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
///**
// * 接收数据解密
// *
// * @author LiuBing
// * @date 2020/12/7
// */
//@ControllerAdvice(annotations = {RestController.class})
//public class RequestHandlerAdvice extends RequestBodyAdviceAdapter {
//
//	@Override
//	public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//		return true;
//	}
//
//	@Override
//	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
//		inputMessage = decodeInputMessage(inputMessage);
//		return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
//	}
//
//	@Override
//	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
//		return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
//	}
//
//	private HttpInputMessage decodeInputMessage(HttpInputMessage inputMessage) {
//		try {
//			inputMessage = new DecodeHttpInputMessage(inputMessage)
//					.decodeWithThe("JXg8JJvI7TDAhe5AQvRS/oMMD9cxcUrptjqXsfFy7Mo=");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return inputMessage;
//	}
//}
