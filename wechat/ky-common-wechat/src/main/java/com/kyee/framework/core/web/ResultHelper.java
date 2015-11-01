package com.kyee.framework.core.web;

import com.kyee.framework.core.exception.KyeeRuntimeException;

/**
 * @author 梁志艳
 * 创建时间：2015-07-22 下午3:22 
 * 任务号：MOBILEDEVELOP-9620
 * 创建说明：简易创建返回结果的helper类
 */
public class ResultHelper {
    /*--成功的返回--*/

    /**
     * 只生成返回值
     * @param value 返回的value
     * @return 生成的返回值
     */
    public static Result newSuccessResult(Object value){
        return newSuccessResult(value,"");
    }

    /**
     * 生成返回值和消息内容
     * @param value 返回的value
     * @param message 返回的message
     * @return 生成的返回值
     */
    public static Result newSuccessResult(Object value,String message){
        Result result=new Result();
        result.setSuccess(true);
        result.setValue(value);
        result.setMessage(message);
        result.setState(ResultState.SUCCESS);
        return result;
    }
    /*--失败的的返回--*/

    /**
     * 包含消息
     * @param message 消息
     * @return 生成的返回值
     */
    public static Result newFailureResult(String message){
        Result result=new Result();
        result.setSuccess(false);
        result.setValue(null);
        result.setMessage(message);
        result.setState(ResultState.FAILURE);
        return result;
    }

    /*--异常的返回--*/

    /**
     * 返回外部异常结果
     * @param message 消息
     * @param e 异常
     * @return 生成的返回值
     */
    public static Result newExceptionResult(String message,Throwable e){
        if(e instanceof KyeeRuntimeException){
            return newExceptionResult(message,e,ResultState.KNOWN_ERROR);
        }else {
            return newExceptionResult(message,e,ResultState.UNKNOWN_ERROR);
        }
    }

    /**
     * 返回异常结果
     * @param message 消息
     * @param e 异常
     * @param status 状态
     * @return 生成的返回值
     */
    public static Result newExceptionResult(String message,Throwable e,int status){
        Result result=new Result();
        result.setSuccess(false);
        result.setValue(null);
        result.setMessage(message);
        result.setState(status);
        return result;
    }
}
