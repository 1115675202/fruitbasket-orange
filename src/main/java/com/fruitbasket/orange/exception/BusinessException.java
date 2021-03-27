package com.fruitbasket.orange.exception;

/**
 * 业务逻辑处理过程中，如果想中断直接返回错误可通过抛出此异常实现
 * 不追踪堆栈信息，性能损耗少
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
