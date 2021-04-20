package com.fruitbasket.orange.response;

/**
 * 统一响应
 *
 * @author LiuBing
 * @date 2020/11/28
 */
public class ResponseVO<T> {

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

    public static final ResponseVO SUCCESS = new ResponseVO<>(ResponseCode.SUCCESS, null);

    public static final ResponseVO FAILURE = new ResponseVO<>(ResponseCode.FAILURE, null);

    public static final ResponseVO ERROR = new ResponseVO<>(ResponseCode.ERROR, null);

    public static <T> ResponseVO<T> of(ResponseCode responseStatus, T data) {
        return new ResponseVO<>(responseStatus, data);
    }

    public static ResponseVO of(ResponseCode responseStatus) {
        return new ResponseVO<>(responseStatus, null);
    }

    public static <T> ResponseVO<T> successOf(T data) {
        return new ResponseVO<>(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseVO<T> failureOf(T data) {
        return new ResponseVO<>(ResponseCode.FAILURE, data);
    }

    public static <T> ResponseVO<T> errorOf(T data) {
        return new ResponseVO<>(ResponseCode.ERROR, data);
    }

    public ResponseVO<T> responseStatus(ResponseCode responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        return this;
    }

    public ResponseVO<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseVO<T> message(String message) {
        this.message = message;
        return this;
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

    private ResponseVO(ResponseCode responseStatus, T data) {
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