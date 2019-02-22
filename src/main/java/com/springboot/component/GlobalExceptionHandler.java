package com.springboot.component;

import com.springboot.exception.GlobalException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)

    public void exceptionHandler(HttpServletRequest request,Exception e) throws Exception{
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException)e;
            throw globalException;
        }else if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException  = (MethodArgumentNotValidException)e;
            throw methodArgumentNotValidException;
        }else {
            throw e;
        }
    }

}