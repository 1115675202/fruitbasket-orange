package com.example.spring.boot.zhaoyun.response;

/**
 * 统一响应
 *
 * @author LiuBing
 * @date 2020/11/28
 */
public class ResultVO<T> {

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 响应信息，用来说明响应情况
	 */
	private String message;

	/**
	 * 响应的具体数据
	 */
	private T data;

	public ResultVO<T> responseStatus(ResponseCodeEnum responseStatus) {
		this.code = responseStatus.getCode();
		this.message = responseStatus.getMessage();
		return this;
	}

	public ResultVO<T> data(T data) {
		this.data = data;
		return this;
	}

	public ResultVO<T> message(String message) {
		this.message = message;
		return this;
	}

	public static <T> ResultVO<T> build(ResponseCodeEnum responseStatus, T data) {
		return new ResultVO(responseStatus, data);
	}

	public static <T> ResultVO<T> success() {
		return new ResultVO(ResponseCodeEnum.SUCCESS, null);
	}

	public static <T> ResultVO<T> failure() {
		return new ResultVO(ResponseCodeEnum.FAILURE, null);
	}

	public static <T> ResultVO<T> error() {
		return new ResultVO(ResponseCodeEnum.ERROR, null);
	}

	public static <T> ResultVO<T> successOf(T data) {
		return new ResultVO(ResponseCodeEnum.SUCCESS, data);
	}

	public static <T> ResultVO<T> failureOf(T data) {
		return new ResultVO(ResponseCodeEnum.FAILURE, data);
	}

	public static <T> ResultVO<T> errorOf(T data) {
		return new ResultVO(ResponseCodeEnum.ERROR, data);
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	private ResultVO(ResponseCodeEnum responseStatus, T data) {
		this.code = responseStatus.getCode();
		this.message = responseStatus.getMessage();
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultVO{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}