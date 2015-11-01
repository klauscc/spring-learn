package com.kyee.framework.wechat.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 程峰
 * 创建时间:15/8/20 上午9:38
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:点击菜单bean
 */
public class ClickMsg extends BaseEventMsg{
    /**
     * 自定义菜单的点击
     *  event:  CLICK
     *  eventKey: 事件KEY值，与自定义菜单接口中KEY值对应
     *
     * 点击菜单跳转链接
     *      event: VIEW
     *   eventKey:事件KEY值，设置的跳转URL
     */
    @JsonProperty("EventKey")
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
