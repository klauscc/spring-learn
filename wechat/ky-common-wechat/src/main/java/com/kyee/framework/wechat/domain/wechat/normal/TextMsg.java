package com.kyee.framework.wechat.domain.wechat.normal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 程峰
 * 创建时间:15/8/20 上午9:20
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:文本消息bean
 */
public class TextMsg extends BaseNormalMsg {
    /**
     * 文本消息内容
     */
    @JsonProperty("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
