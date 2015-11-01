package com.kyee.framework.wechat.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author 程峰
 * 创建时间:15/8/20 上午9:25
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:微信端发送事件消息基础bean
 */
public class BaseEventMsg {
    /**
     * 开发者微信号
     */
    @JsonProperty( "ToUserName")
    private String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @JsonProperty( "FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @JsonProperty( "CreateTime")
    private Date createTime;
    /**
     * 消息类型，event
     */
    @JsonProperty("MsgType")
    private String msgType;
    /**
     * 事件类型
     * subscribe(订阅)、unsubscribe(取消订阅)
     * 扫描带参数二维码事件，未关注为subscribe,关注后为SCAN
     */
    @JsonProperty( "Event")
    private String event;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
