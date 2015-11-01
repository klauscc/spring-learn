package com.kyee.framework.exception;

/**
 * @author 梁志艳
 * 创建时间：2015-07-22 上午10:56 
 * 任务号：MOBILEDEVELOP-9620
 * 创建说明：项目异常基类
 */
public class KyeeRuntimeException extends RuntimeException{
    public KyeeRuntimeException(String message) {
        super(message);
    }

    public KyeeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public KyeeRuntimeException(Throwable cause) {
        super(cause);
    }
}
