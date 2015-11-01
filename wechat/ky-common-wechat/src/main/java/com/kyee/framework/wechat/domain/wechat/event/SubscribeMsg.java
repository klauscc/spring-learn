package com.kyee.framework.wechat.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 程峰
 * 创建时间:15/8/20 上午9:23
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明: 订阅，取消订阅，扫描二维码bean
 */
public class SubscribeMsg extends BaseEventMsg{
    /**
     * 事件KEY值
     * 当扫描二维码关注时，event为subscribe, eventKey: qrscene_为前缀，后面为二维码的参数值
     * 已经关注扫描二维码, event为SCAN, eventKey: 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    @JsonProperty("EventKey")
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @JsonProperty("Ticket")
    private String ticket;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
