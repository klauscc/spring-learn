package com.kyee.framework.wechat.domain.wechat;

/**
 * @author 叶丹
 * 创建时间：2015-08-07 13:48
 * 任务号：MOBILEDEVELOP-9899
 * 创建说明：微信模板推送消息返回结果
 */
public class WechatResponse {
    /**
     * 返回结果码
     */
    private int errcode;
    /**
     * 返回信息提示
     */
    private String errmsg;
    /**
     * 消息id
     */
    private int msgid;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getMsgid() {
        return msgid;
    }

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
