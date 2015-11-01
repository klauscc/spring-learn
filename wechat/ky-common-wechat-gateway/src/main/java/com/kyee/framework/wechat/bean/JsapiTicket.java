package com.kyee.framework.wechat.bean;

import org.springframework.stereotype.Component;

/**
 * @author 汪伟滨
 * 任务号：MOBILEDEVELOP-10308【微信公共模块】集成微信签名计算到gateway中
 * 创建日期：2015-09-09 09:58:49
 * 创建原因：js端api调用的凭证
 */
@Component
public class JsapiTicket {
    //错误代码，若为0则无error
    private String errCode;
    //错误信息
    private String errMsg;
    //签名的凭证
    private String ticket;
    //过期时间
    private int expiresIn;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
