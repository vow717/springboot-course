package com.example.backendexamples.controller;

import com.example.backendexamples.exception.XException;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //这个注释意为这个类是一个全局异常处理类
@Slf4j
@Order(2) //这个注释意为这个类的优先级是2
public class ExceptionController {
    @ExceptionHandler(XException.class)//这个注释意为这个方法是处理XException的异常
    public ResultVO handleValidException(XException e){
       if(e.getCode()!=null){
           return ResultVO.error(e.getCode());
       }
         return ResultVO.error(e.getCodeN(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e){
        return ResultVO.error(Code.ERROR,e.getMessage());
    }
}
