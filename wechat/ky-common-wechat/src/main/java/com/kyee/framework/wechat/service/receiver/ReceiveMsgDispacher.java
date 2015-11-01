package com.kyee.framework.wechat.service.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyee.framework.wechat.domain.wechat.event.ClickMsg;
import com.kyee.framework.wechat.domain.wechat.event.LocateMsg;
import com.kyee.framework.wechat.domain.wechat.event.SubscribeMsg;
import com.kyee.framework.wechat.domain.wechat.normal.TextMsg;
import com.kyee.framework.wechat.IEventMsgHandler;
import com.kyee.framework.wechat.INormalMsgHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * @author 程峰
 * 创建时间:15/8/19 下午5:03
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:分发接收到的微信端消息
 */
@Service
public class ReceiveMsgDispacher implements IReceiveMsgDispacher{

    @Autowired(required = false)
    private INormalMsgHandler normalMsgHandler;
    @Autowired(required = false)
    private IEventMsgHandler eventMsgHandler;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Log LOGGER = LogFactory.getLog(ReceiveMsgDispacher.class);

    @PostConstruct
    public void checkImplements(){
        if(normalMsgHandler == null){
            LOGGER.warn("没有找到 "+INormalMsgHandler.class+"的实现!");
            LOGGER.warn("如果想处理微信端发送的事件消息，请实现该接口并注册bean");
        }
        if(eventMsgHandler == null){
            LOGGER.warn("没有找到"+IEventMsgHandler.class+"的实现!");
            LOGGER.warn("如果想处理微信端发送的普通消息，请实现该接口并注册bean");
        }
    }

    @Override
    public void normalMsgProcessor(Map<String,String > map) throws IOException {
        if(normalMsgHandler == null){
            LOGGER.warn("接收到微信发送过来的请求"+map);
            LOGGER.warn("没有找到 "+INormalMsgHandler.class+"的实现!");
            LOGGER.warn("如果想处理微信端发送的普通消息，请实现该接口并注册bean");
            return;
        }
        String msgType = map.get("MsgType");
        byte[] request = objectMapper.writeValueAsBytes(map);
        switch (msgType){
            case "text":
                TextMsg textMsg = objectMapper.readValue(request, new TypeReference<TextMsg>() {
                });
                normalMsgHandler.onTxtMsg(textMsg);
                break;
            default:
                //here you can generate more normal msg handlers
        }
    }

    @Override
    public void eventMsgProcessor(Map<String ,String > map) throws IOException {
        if(eventMsgHandler == null){
            LOGGER.warn("接收到微信发送过来的请求"+map);
            LOGGER.warn("没有找到"+IEventMsgHandler.class+"的实现!");
            LOGGER.warn("如果想处理微信端发送的事件消息，请实现该接口并注册bean");
            return;
        }
        String event = map.get("Event");
        byte[] request = objectMapper.writeValueAsBytes(map);
        switch (event){
            case "CLICK":
                ClickMsg clickMsg = objectMapper.readValue(request, new TypeReference<ClickMsg>() {});
                eventMsgHandler.onClick(clickMsg);
                break;
            case "SCAN":
                SubscribeMsg scanMsg = objectMapper.readValue(request, new TypeReference<SubscribeMsg>() {});
                eventMsgHandler.onScan(scanMsg);
                break;
            case "subscribe":
                SubscribeMsg subscribeMsg = objectMapper.readValue(request, new TypeReference<SubscribeMsg>() {});
                eventMsgHandler.onSubscribe(subscribeMsg);
                break;
            case "unsubscribe":
                SubscribeMsg unSubscribeMsg = objectMapper.readValue(request, new TypeReference<SubscribeMsg>() {});
                eventMsgHandler.onUnsubscribe(unSubscribeMsg);
                break;
            case "LOCATION":
                LocateMsg locateMsg =objectMapper.readValue(request, new TypeReference<LocateMsg>() {});
                eventMsgHandler.onLocate(locateMsg);
                break;
            default:
        }
    }
}
