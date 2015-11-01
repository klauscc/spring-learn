package com.kyee.framework.web;

/**
 * @author 梁志艳
 * 创建时间：2015-07-22 上午9:48 
 * 任务号：MOBILEDEVELOP-9620
 * 创建说明：返回结果的封装
 */
public class Result {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回结果
     */
    private Object value;
    /**
     * 状态
     */
    private int state;
    /**
     * 消息
     */
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
