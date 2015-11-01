package com.kyee.framework.wechat.config;

import com.kyee.framework.wechat.service.receiver.ReceiveMsgDispacher;
import com.kyee.framework.wechat.service.sender.SendMessageService;
import com.kyee.framework.wechat.service.token.GatewayToken;
import com.kyee.framework.wechat.service.token.TokenGetTask;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 程峰
 * 创建时间:15/8/21 下午1:16
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明: 导入wechat相关的一些Bean
 */
@Configuration
@EnableScheduling
@Import({ReceiveMsgDispacher.class, SendMessageService.class,GatewayToken.class,TokenGetTask.class})
public class WechatConfiguration {
}
