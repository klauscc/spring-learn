package com.kyee.framework.wechat.service.jsSignature;

import com.kyee.framework.wechat.bean.JsSignature;
import com.kyee.framework.wechat.bean.JsapiTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

/**
 * @author 汪伟滨
 * 任务号：MOBILEDEVELOP-10308【微信公共模块】集成微信签名计算到gateway中
 * 创建日期：2015-09-09 10:41:51
 * 创建原因：生成js端签名的Service
 */
@Service
public class JsSignatureService {
    @Autowired
    private JsapiTicket jsapiTicket;

    /**
     * 生成签名
     * @param url 需要签名的url地址
     * @return JsSignature 签名的详细信息
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public JsSignature generateJsSignature(String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //检查ticket
        String ticket = jsapiTicket.getTicket();
        if(ticket == null || ticket.length() == 0){
            return null;
        }

        //拼装加密的字符串, 并进行签名
        String nonceStr = create_nonce_str();
        String timestamp = create_timestamp();
        String key = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=http://" + url;
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(key.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());

        //准备返回结果
        JsSignature jsSignature = new JsSignature();
        jsSignature.setNonceStr(nonceStr);
        jsSignature.setTimestamp(timestamp);
        jsSignature.setUrl(url);
        jsSignature.setSignature(signature);

        return jsSignature;
    }


    /**
     * 将byte转换为16进制字符串
     * @param hash
     * @return
     */
    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 创建随机字符串
     * @return 生成的随机字符串
     */
    private String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取当前的时间戳
     * @return 当前的时间戳
     */
    private String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
