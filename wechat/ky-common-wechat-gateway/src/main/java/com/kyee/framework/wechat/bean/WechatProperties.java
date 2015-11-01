package com.kyee.framework.wechat.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 程峰
 * 创建时间：2015-07-22 上午10:59
 * 任务号：MOBILEDEVELOP-9751
 * 创建说明：读取application.yaml(.properties)配置
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {
    /**
     *微信 appId
     * */
    private String appId;
    /**
     * 微信 secretKey
     * */
    private String secretKey;
    /**
     * 向微信服务器获取access_token时间间隔，默认110分钟.access_token有效期120分钟
     * */
    private int refreshInterval= 60*1000*110;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }
}
