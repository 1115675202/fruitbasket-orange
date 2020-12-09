package com.example.spring.boot.zhaoyun.response;

/**
 * 响应状态
 *
 * @author LiuBing
 * @date 2020/11/25
 */
public enum ResponseCodeEnum {

	/**
	 * 一切 OK
	 */
	SUCCESS("00000", "处理成功"),

	/**
	 * 宏观错误
	 */
	CLIENT_ERROR("A0001", "客户端错误"),

	/**
	 * 宏观错误
	 */
	FAILURE("A0002", "处理失败"),

	/**
	 * 宏观错误
	 */
	ERROR("A0003", "服务器异常");

	/**
	 * 响应码
	 */
	private final String code;

	/**
	 * 响应消息
	 */
	private final String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	ResponseCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
