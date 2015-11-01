package com.kyee.framework.wechat.controller;

import com.kyee.framework.wechat.bean.CheckSignature;
import com.kyee.wechat.gateway.service.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 夏之阳
 * 创建时间：2015-08-04 下午1:43
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：微信端与中间件通信接口
 */

/**
 * 修改人：夏之阳
 * 任务号：MOBILEDEVELOP-10005
 * 修改时间：2015-08-12 16:53
 * 修改原因：添加apidoc的注释
 */
@RestController
public class WechatController {
    @Autowired
    private DispatcherService dispatcherService;

    /**
     * 与微信端通信的接收函数
     *
     * @param map 微信发过来的参数
     * @return 处理结果返回值
     */
    @RequestMapping(value = "/wechatInterface",method = RequestMethod.POST)
    public String wechatDispatcher(@RequestBody Map<String,String> map){
        return dispatcherService.wechatDispatcher(map);
    }

    /**
     * 微信验证签名接口
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串 验证成功则返回该字符串
     * @return 验证成功则返回echostr，否则返回空串
     */
    @RequestMapping(value = "/wechatInterface",method = RequestMethod.GET)
    public String check(String signature,String timestamp,String nonce,String echostr){
        if(CheckSignature.checkSignature(signature,timestamp,nonce)){
            return echostr;
        }else {
            return "";
        }
    }
}
