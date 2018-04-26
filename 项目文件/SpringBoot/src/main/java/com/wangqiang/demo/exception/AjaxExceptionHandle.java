package com.wangqiang.demo.exception;


import com.wangqiang.demo.util.JsonAndStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AjaxExceptionHandle {

    @ExceptionHandler
    public JsonAndStatus defultError(HttpServletRequest request, HttpServletResponse response,Exception e)throws  Exception{

       e.printStackTrace();

       return JsonAndStatus.errorException(e.getMessage());
    }
}
