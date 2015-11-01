package com.kyee.framework.wechat.annotation;

import com.kyee.framework.wechat.config.JmsConfiguration;
import com.kyee.framework.wechat.config.WechatConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 程峰
 * 创建时间:15/8/21 下午1:15
 * 任务号: MOBILEDEVELOP-10110
 * 创建说明: 添加wechat bean到上下文
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({JmsConfiguration.class, WechatConfiguration.class})
public @interface EnableWechatConfiguration {
}
