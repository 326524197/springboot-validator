package com.hsj.hsjValidTest.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.hsj.hsjValidTest.vo.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hsj.hsjValidTest.vo.JSONResultWrapper;

/**
 * @RequestBody检验统一异常抛出
 * @Auther heye
 * @Date 2019/9/28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.application.name}")
    private String serviceName;

    /***
     * 校验异常-@RequestBody类型
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONResultWrapper validationErrorHandler(MethodArgumentNotValidException ex) {
        // 同样是获取BindingResult对象，然后获取其中的错误信息
        // 如果前面开启了fail_fast，事实上这里只会有一个信息
        LOGGER.info("校验异常=MethodArgumentNotValidException.class:" + serviceName);
        // 如果没有，则可能又多个
        List<String> errorInformation = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        //return JSONResultWrapper.errorWithMessageAndObject(JSONResultWrapper.RESCODE_ERR, JSONResultWrapper.MESSAGE_ERROR, errorInformation
        switch (serviceName) {
            case "hsjValidatorTest":
                return JSONResultWrapper.errorWithMessageAndObject(ResultStatus.game_param_error.code(), ResultStatus.game_param_error.getReasonPhrase(), errorInformation);
            default:
                return null;
        }
    }

    /***
     * 校验异常-@PathVariable和@RequestParam类型
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JSONResultWrapper validationErrorHandlerOther(ConstraintViolationException ex) {
        List<String> errorInformation = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        switch (serviceName) {
            case "hsjValidatorTest":
                return JSONResultWrapper.errorWithMessageAndObject(ResultStatus.game_param_error.code(), ResultStatus.game_param_error.getReasonPhrase(), errorInformation);
            default:
                return null;
        }
    }

    /***
     * 其余异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public JSONResultWrapper validationErrorHandlerDefault(Exception e) {
        return JSONResultWrapper.errorWithMessageAndObject(ResultStatus.ERROR.code(), ResultStatus.ERROR.getReasonPhrase(), e.getMessage()
        );
    }
}

