package cn.fruitbasket.orange.config.exception;

import cn.fruitbasket.orange.config.response.ResponseCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import static cn.fruitbasket.orange.config.response.ResponseCode.FAILURE;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;

/**
 * 错误信息会发送给客户端的异常，不追踪堆栈信息，性能损耗少
 * 业务逻辑处理过程中，如果想中断直接返回错误可通过抛出此异常实现
 *
 * @author LiuBing
 * @date 2020/6/24
 */
@Data
@Accessors(chain = true)
public class ShowToClientException extends RuntimeException {

    /**
     * 响应码
     */
    private final ResponseCode responseCode;

    /**
     * 异常详细信息
     */
    private final List<String> detailMessages;

    public ShowToClientException(String detailMessage) {
        this(FAILURE, singletonList(requireNonNull(detailMessage)), false);
    }

    public ShowToClientException(ResponseCode responseCode) {
        this(requireNonNull(responseCode), emptyList(), false);
    }

    public ShowToClientException(List<String> detailMessages) {
        this(FAILURE, requireNonNull(detailMessages), false);
    }

    public ShowToClientException(ResponseCode responseCode, String detailMessage) {
        this(requireNonNull(responseCode), singletonList(requireNonNull(detailMessage)), false);
    }

    public ShowToClientException(ResponseCode responseCode, List<String> detailMessages) {
        this(requireNonNull(responseCode), requireNonNull(detailMessages), false);
    }

    /**
     * @param responseCode     响应码
     * @param detailMessages   异常详细信息
     * @param recordStackTrace true-记录栈跟踪记录，很耗性能
     */
    private ShowToClientException(ResponseCode responseCode, List<String> detailMessages, boolean recordStackTrace) {
        super(responseCode.getMessage(), null, false, recordStackTrace);
        this.responseCode = responseCode;
        this.detailMessages = detailMessages;
    }
}
