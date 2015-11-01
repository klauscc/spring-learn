package com.kyee.framework.wechat.service.receiver;

import java.io.IOException;
import java.util.Map;

/**
 * @author 程峰
 * 创建时间:15/8/19 下午5:06
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:分发微信发送过来的消息
 */
public interface IReceiveMsgDispacher {

    /**
     * 分发微信发送过来的普通消息
     * @param map 微信发送过来的消息
     * @throws IOException 解析消息时出现IO异常
     */
    void normalMsgProcessor(Map<String,String> map) throws IOException;

    /**
     * 分发微信发送过来的事件消息
     * @param map 微信发送过来的消息
     * @throws IOException 解析消息时出现IO异常
     */
    void eventMsgProcessor(Map<String ,String> map) throws IOException;
}
