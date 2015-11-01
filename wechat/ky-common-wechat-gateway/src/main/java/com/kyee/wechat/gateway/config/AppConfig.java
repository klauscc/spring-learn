package com.kyee.wechat.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 程峰
 * 创建时间：2015-08-07 14:11
 * 任务号：MOBILEDEVELOP-9901
 * 创建说明：声明通用的bean
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}