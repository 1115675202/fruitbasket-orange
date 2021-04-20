package com.fruitbasket.orange.advice;

import com.fruitbasket.orange.response.ResponseCode;
import com.fruitbasket.orange.response.ResponseVO;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常捕获
 *
 * @author LiuBing
 * @date 2020/11/28
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

	/**
	 * 参数校验异常
	 **/
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseVO<List<String>> transformMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return convertFrom(e.getBindingResult());
	}

	/**
	 * 参数校验异常
	 **/
	@ExceptionHandler(value = BindException.class)
	public ResponseVO<List<String>> transformBindingResult(BindException e) {
		return convertFrom(e.getBindingResult());
	}

//	/**
//	 * 参数校验异常
//	 **/
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	public ResultVO<String> transformConstraintViolationException(ConstraintViolationException e) {
//		return ResultVO.build(ResponseCodeEnum.CLIENT_ERROR, e.getMessage());
//	}

	private ResponseVO<List<String>> convertFrom(BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			return null;
		}

		List<String> errorMessageList = bindingResult.getAllErrors()
				.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
		return ResponseVO.of(ResponseCode.CLIENT_ERROR, errorMessageList);
	}
}
