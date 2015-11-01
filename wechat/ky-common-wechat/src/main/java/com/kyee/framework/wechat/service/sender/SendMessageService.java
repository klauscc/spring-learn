package com.kyee.framework.wechat.service.sender;

import com.kyee.framework.wechat.domain.gateway.GatewayRequestHelper;
import com.kyee.framework.wechat.domain.gateway.SessionType;
import com.kyee.framework.wechat.service.token.GatewayToken;
import com.kyee.framework.core.web.HttpEntityHelper;
import com.kyee.framework.wechat.domain.wechat.WechatResponse;
import com.kyee.framework.wechat.domain.gateway.GatewayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author 夏之阳
 * 创建时间：2015-08-05 13:15
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：向用户发送消息
 */

/**
 * 修改人:程峰
 * 修改时间：2015-08-17 17:20
 * 任务号:MOBILEDEVELOP-10067
 * 修改说明：给Url路径添加token
 */
@Service
public class SendMessageService {
    @Autowired
    GatewayToken gatewayToken;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private  RestTemplate restTemplate = new RestTemplate();

    /**
     * 发送客服消息
     *
     * @param session 是否创建session
     * @param openId 用户openId
     * @param content 消息正文
     * @return 微信端返回消息
     */
    public WechatResponse sendCustomMessage(SessionType session,String openId,String content){
        String url = gatewayUrl+gatewayToken.getToken();
        GatewayRequest request = GatewayRequestHelper.createCustomMessage(session.toString(), openId, content);
        HttpEntity requestEntity = HttpEntityHelper.createJsonEntity(request);
        return restTemplate.postForObject(url, requestEntity, WechatResponse.class);
    }

    /**
     * 发送模板消息，不创建session
     * @param openId 用户openId
     * @param postData 模板消息内容
     * @return 微信端返回值
     */
    public WechatResponse sendTemplateMessage(String openId,Object postData){
        String url = gatewayUrl+gatewayToken.getToken();
        //模板消息不创建会话Session
        GatewayRequest request = GatewayRequestHelper.createTemplateMessage(SessionType.NO_SESSION.toString(), openId, postData);
        HttpEntity requestEntity = HttpEntityHelper.createJsonEntity(request);
        return restTemplate.postForObject(url, requestEntity, WechatResponse.class);
    }

    /**
     * 发送模板消息，自定义session处理
     * @param session session
     * @param openId 用户openId
     * @param postData 模板消息内容
     * @return 微信端返回值
     */
    public WechatResponse sendTemplateMessage(SessionType session,String openId,Object postData){
        String url = gatewayUrl+gatewayToken.getToken();
        //模板消息不创建会话Session
        GatewayRequest request = GatewayRequestHelper.createTemplateMessage(session.toString(), openId, postData);
        HttpEntity requestEntity = HttpEntityHelper.createJsonEntity(request);
        return restTemplate.postForObject(url, requestEntity, WechatResponse.class);
    }
}
