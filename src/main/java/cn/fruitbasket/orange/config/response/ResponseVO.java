package cn.fruitbasket.orange.config.response;

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
    private final String code;

    /**
     * 响应信息，用来说明响应情况
     */
    private String message;

    /**
     * 响应的具体数据
     */
    private T data;

    public ResponseVO<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseVO<T> setMessage(String message) {
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

    protected ResponseVO(ResponseCode responseStatus, T data) {
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