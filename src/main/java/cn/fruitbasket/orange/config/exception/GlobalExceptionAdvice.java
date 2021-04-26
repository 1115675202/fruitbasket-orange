package cn.fruitbasket.orange.config.exception;

import cn.fruitbasket.orange.config.response.ResponseVO;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static cn.fruitbasket.orange.config.response.ResponseCode.CLIENT_ERROR;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * 全局异常捕获
 *
 * @author LiuBing
 * @date 2020/11/28
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 参数校验异常
     **/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseVO<List<String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return convertFrom(e.getBindingResult());
    }

    /**
     * 参数校验异常，校验注解写在实体类中的这种
     **/
    @ExceptionHandler(value = BindException.class)
    public ResponseVO<List<String>> bindException(BindException e) {
        return convertFrom(e.getBindingResult());
    }

    /**
     * 参数校验异常，校验注解写在方法中的这种
     **/
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseVO<List<String>> constraintViolationException(ConstraintViolationException e) {
        List<String> errorMessages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(toList());
        return ResponseVO.of(CLIENT_ERROR, errorMessages);
    }

    /**
     * 业务处理异常
     **/
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVO<List<String>> businessException(BusinessException e) {
        return ResponseVO.of(e.getResponseCode(), e.getDetailMessages());
    }

    /**
     * 其他异常
     **/
    @ExceptionHandler(value = Exception.class)
    public ResponseVO<List<String>> otherException(Exception e) {
        return ResponseVO.failureOf(singletonList(e.getMessage()));
    }

    /**
     * 参数校验异常信息转换
     *
     * @param bindingResult -
     * @return 接口返回对象
     */
    private ResponseVO<List<String>> convertFrom(BindingResult bindingResult) {
        List<String> errorMessages = bindingResult.getAllErrors()
                .stream().map(ObjectError::getDefaultMessage).collect(toList());
        return ResponseVO.of(CLIENT_ERROR, errorMessages);
    }
}
