package com.kyee.framework.wechat.domain.gateway;

/**
 * @author 程峰
 * 创建时间：15/8/6 上午11:54
 * 任务号：MOBILEDEVELOP-9901-hcrm
 * 创建说明：向中间件发送请求bean
 */
public class GatewayRequest {
    /**
     * HCRM端发送消息类型
     * 取值：
     *     客服消息 ：1
     *     模板消息 ：2
     * */
    private String type;
    /**
     * Session状态
     * 取值：
     *      创建Session : 1
     *      销毁Session : 2
     *      不创建Session: 3
     *
     * */
    private String session;
    /**
     * Session存活时间，单位秒
     * 默认7200秒，即2小时
     * */
    private String liveTime = "7200";

    /**
     * 推送的用户的openId
     * */
    private String openId;

    /**
     * 将要发给微信客户端的消息
     * */
    private Object content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
