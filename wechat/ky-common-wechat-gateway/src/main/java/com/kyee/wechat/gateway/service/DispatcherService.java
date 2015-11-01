package com.kyee.wechat.gateway.service;

import com.kyee.framework.web.HttpEntityHelper;
import com.kyee.wechat.gateway.domain.businessinteraction.BusinessRequest;
import com.kyee.wechat.gateway.domain.businessinteraction.MessageType;
import com.kyee.wechat.gateway.domain.businessinteraction.ResponseToBusiness;
import com.kyee.wechat.gateway.domain.businessinteraction.SessionType;
import com.kyee.wechat.gateway.service.jedis.ISessionService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 夏之阳
 * 创建时间：2015-08-04 14:11
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：消息分发接口
 */

/**
 * 修改人：程峰
 * 修改时间：2015-08-06 13:11
 * 任务号：MOBILEDEVELOP-9901
 * 修改说明：整合session控制块
 * */

/**
 * 修改人：程峰
 * 修改时间：2015-08-12 14:56
 * 任务号:MOBILEDEVELOP-10006
 * 修改说明：消息类型控制添加 NO_MESSAGE
 * */

/**
 * 修改人：夏之阳
 * 修改时间：2015-08-16 11:17
 * 任务号：MOBILEDEVELOP-10032
 * 修改说明：新增session暂存用户回复数据功能
 */

@Service
public class DispatcherService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private PassiveResponseService passiveResponseService;

    @Autowired
    @Qualifier("text")
    private ActiveMQTopic activeMQTopicText;

    @Autowired
    @Qualifier("event")
    private ActiveMQTopic activeMQTopicEvent;

    /**
     * 微信请求分发函数
     *
     * @param map 微信请求map
     * @return 返回给微信的值 （空代表不回复给用户 否则调用被动回复接口，回复默认消息）
     */
    public String wechatDispatcher(Map<String,String> map){
        String openId = map.get("FromUserName");
        String type = map.get("MsgType");
        if("text".equals(type)) {
            if (sessionService.isSessionValid(openId)) {  //session存在
                sessionService.setContent(openId,"content",map.get("Content")); //暂存用户回复的数据
                jmsTemplate.convertAndSend(activeMQTopicText, map);
                return "";
            }else{
                return passiveResponseService.createDefaultResponse(openId); //默认回复消息
            }
        }else if("event".equals(type)){
            //事件消息一律下发
            jmsTemplate.convertAndSend(activeMQTopicEvent, map);
            return "";
        }else{
            return "";
        }
    }

    /**
     * 创建者：程峰
     * 任务号：MOBILEDEVELOP-9901
     * 分发h端向用户发送的消息
     * @param request h端发送的请求
     * @return 微信服务器返回的消息
     * */

    /**
     * 修改人：夏之阳
     * 修改时间：2015-08-16 11:17
     * 任务号：MOBILEDEVELOP-10032
     * 修改说明：新增更新session，更新session剩余时间
     */
    public String businessDispacher(BusinessRequest request){
        //发送给微信服务器的实体
        HttpEntity<Object> requestEntity = HttpEntityHelper.createJsonEntity(request.getContent());
        switch (request.getSession()){//session控制
            case SessionType.CREATE_SESSION://创建session
                sessionService.createSession(request.getOpenId(),Long.parseLong(request.getLiveTime()));
                break;
            case SessionType.DESTROY_SESSION://销毁session
                sessionService.destroySession(request.getOpenId());
                break;
            case SessionType.UPDATE_SESSION: //更新session
                sessionService.setExpire(request.getOpenId(), Long.parseLong(request.getLiveTime()));
            default:  //其它值不管session
                break;
        }
        switch (request.getType()){//消息类型控制
            case MessageType.CUSTOM_MESSAGE:   //发送客服消息
                return sendMessageService.sendCustomMessage(requestEntity);
            case MessageType.TEMPLATE_MESSAGE:   //发送模板消息
                return sendMessageService.sendTemplateMessage(requestEntity);
            case MessageType.NO_MESSAGE: //不向用户发送消息，用于销毁session时
                ResponseToBusiness responseToBusiness = new ResponseToBusiness();
                responseToBusiness.setErrcode("0");
                responseToBusiness.setErrmsg("OK");
                return responseToBusiness.toString();
            default:
                ResponseToBusiness responseToBusiness1 = new ResponseToBusiness();
                responseToBusiness1.setErrcode("1");
                responseToBusiness1.setErrmsg("消息类型type错误");
                return responseToBusiness1.toString();
        }
    }
}
