package com.kyee.framework.core.exception;

import com.kyee.framework.core.web.Result;
import com.kyee.framework.core.web.ResultHelper;
import com.kyee.framework.core.web.ResultState;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁志艳
 * 创建时间：2015-07-22 上午10:59 
 * 任务号：MOBILEDEVELOP-9620
 * 创建说明：全局异常处理类
 */
@ControllerAdvice
public class KyeeExceptionHandler {
    @ResponseBody
    @ExceptionHandler(KyeeRuntimeException.class)
    public Result handleKyeeException(HttpServletRequest request,KyeeRuntimeException ex){
        return ResultHelper.newExceptionResult(String.format("[程序内部错误异常]:%s,信息:%s", ex.getClass().getSimpleName(), ex.getMessage()), ex);
    }
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleUnknownException(HttpServletRequest request,Throwable ex){
        return ResultHelper.newExceptionResult(String.format("[未知错误]:%s,信息:%s",ex.getClass().getSimpleName(),ex.getMessage()),ex);
    }
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpServletRequest request,Throwable ex){
        return ResultHelper.newExceptionResult("传参不能解析",ex, ResultState.KNOWN_ERROR);
    }
}
