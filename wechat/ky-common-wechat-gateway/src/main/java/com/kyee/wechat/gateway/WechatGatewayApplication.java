package com.kyee.wechat.gateway;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
/**
 * @author 夏之阳
 * 创建时间：2015-08-05 13:15
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：Spring boot启动程序
 * 修改说明: 添加添加jms服务配置
 */

@SpringBootApplication
@ComponentScan("com.kyee")
@EnableJms
public class WechatGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WechatGatewayApplication.class, args);
    }
}
