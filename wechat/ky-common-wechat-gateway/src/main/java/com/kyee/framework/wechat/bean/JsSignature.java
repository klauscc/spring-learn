package com.kyee.framework.wechat.bean;

/**
 * @author 汪伟滨
 * 任务号：MOBILEDEVELOP-10308【微信公共模块】集成微信签名计算到gateway中
 * 创建日期：2015-09-09 09:52:35
 * 创建原因：js端签名的bean类
 */
public class JsSignature {
    //随机生成的字符串
    private String nonceStr;
    //时间戳
    private String timestamp;
    //签名的URL地址
    private String url;
    //生成的签名
    private String signature;

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
