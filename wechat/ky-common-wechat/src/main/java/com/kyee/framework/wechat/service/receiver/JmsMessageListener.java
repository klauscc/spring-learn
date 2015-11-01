package com.kyee.framework.wechat.service.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author 夏之阳
 * 创建时间：2015-08-04 16:14
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：jms接收端
 */

@Component
public class JmsMessageListener {

    @Autowired
    IReceiveMsgDispacher receiveMsgDispacher;


    /**
     * jms接收函数（接收文本消息）
     *
     * @param map 中间件转发的map格式数据(文本类型消息)
     */
    @JmsListener(destination = "${activeMq.text-destination}",containerFactory = "jmsContainerFactory")
    public void receiveTextMessage(Map<String,String> map) throws IOException {
        this.receiveMsgDispacher.normalMsgProcessor(map);
    }

    /**
     * jms接收函数（接收事件消息）
     *
     * @param map 中间件转发的map格式数据(事件类型消息)
     */
    @JmsListener(destination = "${activeMq.event-destination}",containerFactory = "jmsContainerFactory")
    public void receiveEventMessage(Map<String,String> map) throws IOException {
        this.receiveMsgDispacher.eventMsgProcessor(map);
    }
}
