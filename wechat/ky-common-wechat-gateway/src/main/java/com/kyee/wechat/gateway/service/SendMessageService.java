package com.kyee.wechat.gateway.service;

import com.kyee.framework.wechat.WechatToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 夏之阳
 * 创建时间：2015-08-05 13:15
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：向用户发送消息（客服消息）
 */

/**
 * 修改人：程峰
 * 修改时间：2015-08-06 15:00
 * 任务号：MOBILEDEVELOP-9901
 * 修改说明：修改发送消息函数内容
 * */


@Service
public class SendMessageService {
    @Autowired
    private WechatToken wechatToken;

    @Autowired
    private RestTemplate restTemplate;

    private static final String customMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    private static final String templateMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    /**
     * 调用微信客服接口发送文本消息
     *
     * @param request 发送给url的客服消息内容
     * @return 微信端返回数据
     */
    public String sendCustomMessage(HttpEntity request){
        String url = customMessageUrl+wechatToken.getToken();;
        return restTemplate.postForObject(url,request,String.class);
    }

    /**
     * 创建人：程峰
     * 任务号：MOBILEDEVELOP-9901
     * 调用微信模板消息接口发送模板消息
     * @param request 发送给url的模板消息内容
     * @return 返回微信那边的返回值
     * */
    public String sendTemplateMessage(HttpEntity request){
        String url = templateMessageUrl+ wechatToken.getToken();
        return restTemplate.postForObject(url,request,String.class);
    }
}
