package com.jiabin.shop.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice //定义统一异常处理类
public class GlobalExceptionHandle {

    private static final String DEAFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)//匹配具体异常类
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEAFAULT_ERROR_VIEW);

        return mav;
    }
}
