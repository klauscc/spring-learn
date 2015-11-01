package com.kyee.framework.wechat;

import com.kyee.framework.wechat.service.token.TokenGetTask;
import com.kyee.framework.wechat.bean.WechatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Timer;

/**
 * @author 程峰
 * 创建时间：2015-07-22 上午10:59
 * 任务号:MOBILEDEVELOP-9751
 * 创建说明：声明 Webchat Bean
 */
@Configuration
@EnableScheduling
public class WechatConfig {

}
