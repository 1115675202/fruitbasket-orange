package cn.fruitbasket.orange.config.response;

/**
 * 统一响应静态操作类
 *
 * @author LiuBing
 * @date 2020/11/28
 */
public class Responses {

    public static final ResponseVO<?> SUCCESS = new UnmodifiableResponseVO<>(ResponseCode.SUCCESS);

    public static final ResponseVO<?> FAILURE = new UnmodifiableResponseVO<>(ResponseCode.FAILURE);

    public static final ResponseVO<?> ERROR = new UnmodifiableResponseVO<>(ResponseCode.ERROR);

    public static <T> ResponseVO<T> of(ResponseCode responseCode) {
        return new ResponseVO<>(responseCode, null);
    }

    public static <T> ResponseVO<T> of(ResponseCode responseCode, T data) {
        return new ResponseVO<>(responseCode, data);
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

    /**
     * 为常量对象设计，不允许修改属性
     *
     * @param <T>
     */
    private static class UnmodifiableResponseVO<T> extends ResponseVO<T> {

        @Override
        public ResponseVO<T> setData(T data) {
            throw new UnsupportedOperationException();
        }

        protected UnmodifiableResponseVO(ResponseCode responseCode) {
            super(responseCode, null);
        }

        @Override
        public String toString() {
            return "UnmodifiableResponseVO{" +
                    "code='" + super.getCode() + '\'' +
                    ", message='" + super.getMessage() + '\'' +
                    ", data=" + super.getData() +
                    '}';
        }
    }

    private Responses() {
    }
}