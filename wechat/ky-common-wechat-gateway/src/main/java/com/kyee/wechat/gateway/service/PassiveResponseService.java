package com.kyee.wechat.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 夏之阳
 * 创建时间：2015-08-06 15:44
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：被动回复消息服务
 */

@Service
public class PassiveResponseService {
    @Value("${text.defaultResponse}")
    private String defaultResponse;

    @Value("${wechat.wechatAccount}")
    private String wechatAccount;

    /**
     * 创建默认被动回复消息
     *
     * @param openId 用户openId
     * @return 被动回复消息
     */
    public String createDefaultResponse(String openId){
        Long currentTime = System.currentTimeMillis() / 1000L;
        String content = defaultResponse;
        String fromUser = wechatAccount;
        String template = "<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%s</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[%s]]></Content>\n" +
                "</xml>";

        return String.format(template,openId,fromUser,currentTime.toString(),content);
    }
}
