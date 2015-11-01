package com.kyee.framework.wechat;

import com.kyee.framework.wechat.domain.wechat.normal.TextMsg;

/**
 * @author 程峰
 * 创建时间:15/8/19 下午5:12
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明: 微信普通消息处理handler
 */


public interface INormalMsgHandler {
    /**
     * 微信端发送过来的文本消息
     * @param textMsg 文本消息
     */
    void onTxtMsg(TextMsg textMsg);

}
