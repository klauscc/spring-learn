package com.kyee.wechat.gateway.domain.businessinteraction;

/**
 * @author 程峰
 * 创建时间：2015-08-07 14:11
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：对业务端的回复消息
 */
public class ResponseToBusiness {

    /**
     * 错误码，与微信服务器返回错误码一致
     * 0代表成功
     * */
    public String errcode;

    /**
     * 错误消息
     * */
    public String errmsg;

    /**
     * 如果发送的是模板消息，返回值中会有msgId
     * */
    public String msgId;

    public String toString(){
        return "{\"errcode\":"+errcode+",\"errmsg\":"+errmsg+",\"msgId\":"+msgId+"}";
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
