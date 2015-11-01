package com.kyee.framework.web;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author 程峰
 * 创建时间：15/8/7 下午2:28
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：创建httpEntity
 */
public class HttpEntityHelper {

    /**
     * 创建json头的httpEntity
     * @param object HttpEntity的body
     * @return HttpEntity实体
     * */
    public static  <T> HttpEntity<T> createJsonEntity(T object){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<T>(object,headers);
    }

    /**
     * 创建json头 BasicHttp 认证的httpEntity
     * @param object HttpEntity的body
     * @return HttpEntity
     * */
    public static <T> HttpEntity<T> createJsonEntityWithBasicAuthorization(T object,String username, String password){
        HttpHeaders headers = new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
                setContentType(MediaType.APPLICATION_JSON);
            }
        };
        return new HttpEntity<>(object,headers);
    }

    /**
     * 创建xml头的httpEntity
     * @param object HttpEntity的body
     * @return HttpEntity实体
     * */
    public static <T> HttpEntity<T> createXMLEntity(T object){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return new HttpEntity<T>(object,headers);
    }

    /**
     * 创建自定义类型头的httpEntity
     * @param object HttpEntity的body
     * @return HttpEntity实体
     * */
    public static <T> HttpEntity<T> createEntity(T object, MediaType mediaType){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new HttpEntity<T>(object,headers);
    }
}
