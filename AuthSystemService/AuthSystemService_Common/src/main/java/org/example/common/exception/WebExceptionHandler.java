package org.example.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.common.web.SimpleResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 刘文轩
 * @Date 2024/4/11 0:12
 * 统一异常处理
 * 如果自己处理异常 就不会被捕获在这里
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public SimpleResponse exception(Exception e) {
        System.out.println("全局异常");
        e.printStackTrace();
        return new SimpleResponse.SimpleResponseBuilder().failure(e.getMessage()).build();
    }
}
