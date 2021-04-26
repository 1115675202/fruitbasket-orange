package com.fruitbasket.orange.config.response;

/**
 * 响应状态
 *
 * @author LiuBing
 * @date 2020/11/25
 */
public enum ResponseCode {

	SUCCESS("00000", "OK"),
	CLIENT_ERROR("A0001", "客户端错误"),
	FAILURE("A0002", "处理失败"),
	ERROR("A0003", "服务器异常"),
	LOGIN_ERROR("A0200", "登陆异常"),
	ACCESS_DENIED("A0301", "无访问权限"),
	;


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

	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
