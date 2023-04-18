package com.example.xblog.exception;

import com.example.xblog.common.Result;
import com.example.xblog.enums.ResultType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 捕获自定义异常
    @ExceptionHandler(value = ServiceException.class)
    public Result exceptionHandler(ServiceException e) {
        log.error("错误原因为:" + e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(HttpServletResponse httpServletResponse, MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        return Result.error(ResultType.ERROR.getCode(), defaultMessage);
    }

}
