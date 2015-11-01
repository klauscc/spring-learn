package com.kyee.framework.wechat;

import org.springframework.stereotype.Component;

/**
 * @author 程峰
 * 创建时间：2015-07-22 上午10:59
 * 任务号：MOBILEDEVELOP-9751
 * 创建说明：提供外部获取token的接口
 */
@Component
public class WechatToken {

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
