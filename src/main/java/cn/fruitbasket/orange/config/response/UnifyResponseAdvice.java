package cn.fruitbasket.orange.config.response;

import cn.fruitbasket.orange.config.exception.ShowToClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * 统一响应
 * 用 @RestController 标记的控制器 或者 @UnifyResponse 标记的控制器方法返回结果会进行包装
 *
 * @author LiuBing
 * @date 2020/11/28
 */
@RestControllerAdvice(annotations = {RestController.class, UnifyResponse.class})
public class UnifyResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    /**
     * 判断是否拦截处理
     *
     * @param methodParameter
     * @param aClass
     * @return true/false-执行/不执行 beforeBodyWrite
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Type type = methodParameter.getGenericParameterType();
        return !ResponseVO.class.equals(type);
    }

    /**
     * 返回前包装数据
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return data instanceof String ? stringResponseConvert(data) : ResponseVO.successOf(data);
    }

    /**
     * String 类型需要特殊处理，否则会抛出异常 java.lang.ClassCastException
     *
     * @param data
     * @return 包装后的 JSON 数据
     */
    private String stringResponseConvert(Object data) {
        try {
            return objectMapper.writeValueAsString(ResponseVO.successOf(data));
        } catch (JsonProcessingException e) {
            throw new ShowToClientException("");
        }
    }

    public UnifyResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}