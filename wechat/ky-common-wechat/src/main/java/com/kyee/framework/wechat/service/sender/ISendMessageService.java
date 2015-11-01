package com.kyee.framework.wechat.service.sender;

import com.kyee.framework.wechat.domain.gateway.SessionType;
import com.kyee.framework.wechat.domain.wechat.WechatResponse;

/**
 * @author 程峰
 * 创建时间:15/8/21 上午9:07
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:通过微信服务器向用户发送消息接口，同时管理session
 */
public interface ISendMessageService {

    /**
     * 发送客服消息
     *
     * @param session 是否创建session
     * @param openId 用户openId
     * @param content 消息正文
     * @return 微信端返回消息
     */
     WechatResponse sendCustomMessage(SessionType session,String openId,String content);

    /**
     * 发送模板消息，不创建session
     * @param openId 用户openId
     * @param postData 模板消息内容
     * @return 微信端返回值
     */
     WechatResponse sendTemplateMessage(String openId,Object postData);

    /**
     * 发送模板消息，自定义session处理
     * @param session session
     * @param openId 用户openId
     * @param postData 模板消息内容
     * @return 微信端返回值
     */
     WechatResponse sendTemplateMessage(SessionType session,String openId,Object postData);
}
