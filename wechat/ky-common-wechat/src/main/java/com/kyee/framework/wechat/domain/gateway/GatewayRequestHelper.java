package com.kyee.framework.wechat.domain.gateway;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 夏之阳
 * 创建时间：2015-08-06 13:27
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：生成向中间件发送的消息bean
 */

public class GatewayRequestHelper {
    /**
     * 生成客服消息
     *
     * @param session 是否创建session
     * @param openId 用户openId
     * @param content 发送内容
     * @return 发给中间件的消息bean
     */
    public static GatewayRequest createCustomMessage(String session,String openId,String content){
        GatewayRequest gatewayRequest = new GatewayRequest();
        gatewayRequest.setOpenId(openId);
        gatewayRequest.setType("1"); //类型为 客服消息
        gatewayRequest.setSession(session);
        gatewayRequest.setLiveTime("1800");
        //编辑消息正文格式
        Map<String,Object> request = new HashMap<String,Object>();
        request.put("touser", openId);
        request.put("msgtype", "text");
        Map<String,String> subMap = new HashMap<String,String>();
        subMap.put("content", content);
        request.put("text", subMap);
        gatewayRequest.setContent(request);

        return gatewayRequest;
    }

    /**
     * 创建模板消息
     * @param session session
     * @param openId 用户openId
     * @param postData 模板消息内容
     * @return 发给中间件的消息bean
     */
    public static GatewayRequest createTemplateMessage(String session,String openId, Object postData){
        GatewayRequest gatewayRequest = new GatewayRequest();
        gatewayRequest.setOpenId(openId);
        gatewayRequest.setType("2"); //类型为 模板消息
        gatewayRequest.setSession(session);
        gatewayRequest.setLiveTime("0");
        gatewayRequest.setContent(postData);

        return gatewayRequest;
    }
}
