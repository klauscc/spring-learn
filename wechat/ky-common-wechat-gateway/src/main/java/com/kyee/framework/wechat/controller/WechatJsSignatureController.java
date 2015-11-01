package com.kyee.framework.wechat.controller;

import com.kyee.framework.web.Result;
import com.kyee.framework.web.ResultHelper;
import com.kyee.framework.wechat.bean.JsapiTicket;
import com.kyee.framework.wechat.service.jsSignature.JsSignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 汪伟滨
 * 任务号：MOBILEDEVELOP-10308【微信公共模块】集成微信签名计算到gateway中
 * 创建时间：2015-09-09 09:40:15
 * 创建原因：添加jsapiTicket和signature相关的接口
 */
@RestController
public class WechatJsSignatureController {
    @Autowired
    private JsapiTicket jsapiTicket;
    @Autowired
    private JsSignatureService jsSignatureService;

    /**
     * @api {get} /jsapi-ticket 获取微信jsapi-ticket的接口
     * @apiName getJsapiTicket
     * @apiGroup WechatJsapi
     * @apiVersion 0.1.0
     *
     * @apiSuccessExample {json} Success-Response:
     * {
     *  "success":true,
     *  "value":"sM4AOVdWfPE4DxkXGEs8VKvveQurRuyxqLsr-Li_eAgcpWVrHweWkrcXJ1wbBq02M-xQYRwy3e1890J9-CVqjw",
     *  "state":0,
     *  "message":""
     * }
     */

    /**
     * 获取当前缓存的jsapi-ticket
     * @return Result
     */
    @RequestMapping(value = "/jsapi-ticket", method = RequestMethod.GET)
    public Result getJsapiTicket(){
        return ResultHelper.newSuccessResult(jsapiTicket.getTicket());
    }


    /**
     * @api {get} /signature 获取微信js端签名的接口
     * @apiName getSignature
     * @apiGroup WechatJsapi
     * @apiVersion 0.1.0
     *
     * @apiSuccessExample {json} Success-Response:
     * {
     *  "success":true,
     *  "value":{
     *      "nonceStr":"ac95f9f8-10d3-40ca-a9c5-67e4cf8d6ace",
     *      "timestamp":"1441769651",
     *      "url":"wxdemo.baidu.com",
     *      "signature":"09c884a089d4526217923c947db109ccff1a4b0e"
     *      },
     *  "state":0,
     *  "message":""
     * }
     */

    /**
     * 获取js端的签名
     * @param url 需要签名的url地址
     * @return Result
     */
    @RequestMapping(value = "/signature", method = RequestMethod.GET)
    public Result getSignature(@RequestParam String url) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return ResultHelper.newSuccessResult(jsSignatureService.generateJsSignature(url));
    }
}
