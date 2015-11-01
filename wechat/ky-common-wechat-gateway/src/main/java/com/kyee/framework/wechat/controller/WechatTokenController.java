package com.kyee.framework.wechat.controller;

import com.kyee.framework.wechat.WechatToken;
import com.kyee.framework.wechat.service.token.TokenGetTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程峰
 * 创建时间：15/8/11 下午2:21
 * 任务号：MOBILEDEVELOP-9958
 * 创建说明：提供给微信业务端或者测试时token的接口
 */

/**
 * 修改人：夏之阳
 * 任务号：MOBILEDEVELOP-10005
 * 修改时间：2015-08-12 16:53
 * 修改原因：添加apidoc的注释
 */

@RestController
public class WechatTokenController {

    @Autowired
    WechatToken wechatToken;
    @Autowired
    TokenGetTask tokenGetTask;

    /**
     * @api {get} /token/get 获取微信access_token接口
     * @apiName GetToken
     * @apiGroup WechatToken
     * @apiVersion 0.1.0
     *
     * @apiSuccess {String} token 当前时刻的token值
     */

    /**
     * 获取token
     * @return token
     * */
    @RequestMapping("/token/get")
    public String getToken(){
        return wechatToken.getToken();
    }


    /**
     * @api {get} /token/renew 刷新微信access_token接口
     * @apiName RenewToken
     * @apiGroup WechatToken
     * @apiVersion 0.1.0
     *
     * @apiSuccess {String} token 当前时刻的token值
     */

    /**
     * 更新并获取token。如果测试的时候向微信重新获取了token,调用这个接口更新token
     * @return 返回更新后的token
     * */
    @RequestMapping("/token/renew")
    public String refreshToken(){
        tokenGetTask.run();
        return wechatToken.getToken();
    }
}
