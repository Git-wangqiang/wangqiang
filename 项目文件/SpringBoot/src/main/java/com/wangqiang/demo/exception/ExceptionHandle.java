package com.wangqiang.demo.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice //发生异常时会被这个类捕获
public class ExceptionHandle {

    public static  final  String ZIDING_ERROR_VIEW = "error";

   // @ExceptionHandler
    public Object errorHandle(HttpServletRequest request , HttpServletResponse response,Exception e)throws  Exception{

        e.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e);
        mav.addObject("url",request.getRequestURL());
        mav.setViewName(ZIDING_ERROR_VIEW);
        return mav;
    }
}
