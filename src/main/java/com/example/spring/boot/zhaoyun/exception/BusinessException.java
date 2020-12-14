package com.example.spring.boot.zhaoyun.exception;

/**
 * 业务异常，不追踪堆栈信息
 *
 * @author LiuBing
 * @date 2020/6/24
 */
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		this(message, false);
	}

	private BusinessException(String message, boolean recordStackTrace) {
		super(message, null, false, recordStackTrace);
	}
}
