package com.kyee.framework.wechat.service.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyee.framework.exception.KyeeRuntimeException;
import com.kyee.framework.wechat.WechatToken;
import com.kyee.framework.wechat.bean.JsSignature;
import com.kyee.framework.wechat.bean.JsapiTicket;
import com.kyee.framework.wechat.bean.WechatProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @author 程峰
 * 创建时间：2015-07-22 上午10:59
 * 任务号：MOBILEDEVELOP-9751
 * 创建说明：从微信服务器获取token的定时器
 */

/**
 * 修改人：汪伟滨
 * 任务号：MOBILEDEVELOP-10308【微信公共模块】集成微信签名计算到gateway中
 * 修改时间：2015-09-09 09:42:21
 * 修改原因：在获取token后再去获取jsapi-ticket
 */

@Component
public class TokenGetTask{
    @Autowired
    private WechatProperties wechatProperties;
    @Autowired
    private WechatToken wechatToken;
    @Autowired
    private JsapiTicket jsapiTicket;
    @Value("${wechat.ticketUrl}")
    private String ticketUrl;

    private static final Logger LOG = LoggerFactory.getLogger(TokenGetTask.class);
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRateString= "${wechat.refreshInterval:6600000}")
    public void run() {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ wechatProperties.getAppId()+"&secret="+wechatProperties.getSecretKey();
        String token;
        String errCode;
        String errMsg=null;
        Map result = getForResult(tokenUrl);
        //如果微信服务端返回错误码，一般由于appId和secretId错误导致。抛出异常
        if((errCode=(String)result.get("errcode")) != null || (errMsg=(String)result.get("errmsg")) != null){
            String msg = "获取token失败。错误码:"+errCode+" 消息:"+errMsg;
            LOG.error(msg);
            throw new KyeeRuntimeException(msg);
        }else {
            while ((token = (String) result.get("access_token")) == null) {
                try {   //获取失败，等待1000ms重新获取
                    wait(1000);
                    result = getForResult(tokenUrl);
                } catch (InterruptedException e) {
                    LOG.warn("获取微信token失败时尝试等待1000ms再试，等待时线程被中断");
                    throw new KyeeRuntimeException("获取微信token失败时尝试等待1000ms再试，等待时线程被中断",e);
                }
            }
        }
        wechatToken.setToken(token);
        getJsapiTicket(token, 0);
    }

    private Map getForResult(String url){
        String result = restTemplate.getForObject(url,String.class);
        Map<String,String> resultMap;
        try {
            resultMap = objectMapper.readValue(result, new TypeReference<Map<String ,String>>(){});
        } catch (IOException e) {
            LOG.warn("获取微信token，处理返回数据时出现IOException");
            throw new KyeeRuntimeException("获取微信token，处理返回数据时出现IOException",e);
        }
        return resultMap;
    }


    /**
     * 根据accessToken获取jsapi-ticket，递归函数
     * @param accessToken 之前获取的accessToken
     * @param retryTimes 迭代的次数
     */
    private void getJsapiTicket(String accessToken, int retryTimes){
        if(retryTimes >= 3){
            throw new KyeeRuntimeException("获取jsapi-ticket失败");
        }
        //向微信发送请求，获取jsapi-ticket
        String targetUrl = String.format(ticketUrl, accessToken);
        Map result = getForResult(targetUrl);

        //检查返回值
        String errCode = (String) result.get("errcode");
        String errMsg = (String) result.get("errmsg");
        String ticket = (String) result.get("ticket");
        int expiresIn = Integer.parseInt((String) result.get("expires_in"));

        if(errCode.equals("0") && errMsg.equals("ok") && ticket.length() > 0){
            jsapiTicket.setTicket(ticket);
            jsapiTicket.setErrCode(errCode);
            jsapiTicket.setErrMsg(errMsg);
            jsapiTicket.setExpiresIn(expiresIn);
        }
        else{
            synchronized (this) {
                try {
                    wait(1000);
                    retryTimes++;
                    getJsapiTicket(accessToken, retryTimes);
                } catch (InterruptedException e) {
                    LOG.warn("获取jsapi-ticket失败时尝试等待1000ms再次尝试，等待时线程被中断");
                    throw new KyeeRuntimeException("获取jsapi-ticket失败时尝试等待1000ms再次尝试，等待时线程被中断", e);
                }
            }
        }
    }
}
