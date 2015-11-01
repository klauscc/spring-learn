package com.kyee.framework.wechat.domain.wechat.normal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author 程峰
 * 创建时间:15/8/19 下午5:15
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:普通消息类型bean
 */
public class BaseNormalMsg {
    /**
     * 开发者微信号
     */
    @JsonProperty("ToUserName")
    private String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @JsonProperty("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @JsonProperty("CreateTime")
    private Date createTime;
    /**
     * 消息类型 text,image ...
     */
    @JsonProperty("MsgType")
    private String msgType;
    /**
     * 消息id，64位整型
     */
    @JsonProperty("MsgId")
    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
