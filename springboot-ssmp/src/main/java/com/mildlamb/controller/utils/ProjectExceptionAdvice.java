package com.mildlamb.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 作为springmvc的异常处理器
// 声明它是一个controller的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 指定处理什么异常
    @ExceptionHandler(Exception.class)
    public ReturnData doException(Exception ex){
        ex.printStackTrace();
        return new ReturnData("服务器出现异常，请稍后重试");
    }
}
