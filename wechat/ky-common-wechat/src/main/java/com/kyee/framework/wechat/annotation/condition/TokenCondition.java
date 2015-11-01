package com.kyee.framework.wechat.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author 程峰
 * 创建时间:15/8/21 下午1:31
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:控制获取token的开启与关闭
 */
public class TokenCondition implements Condition{

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("gateway.token.enabled").equals("true");
    }
}
