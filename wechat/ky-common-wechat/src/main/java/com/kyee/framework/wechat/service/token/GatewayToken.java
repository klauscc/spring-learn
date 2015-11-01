package com.kyee.framework.wechat.service.token;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 程峰
 * 创建时间:15/8/17 下午4:44
 * 任务号:MOBILEDEVELOP-10067
 * 创建说明:提供获取微信中间件token的接口
 */
@Service
public class GatewayToken {
    /**
     * access_token  会自动设置。
     * */
    private String token;

    /**
     * 获取access_token
     * */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
