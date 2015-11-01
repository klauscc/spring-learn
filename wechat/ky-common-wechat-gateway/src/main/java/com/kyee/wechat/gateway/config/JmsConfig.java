package com.kyee.wechat.gateway.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * @author 夏之阳
 * 创建时间：2015-08-07 9:39
 * 任务号：中间件与业务后台接口
 * 创建说明：
 */

@Configuration
public class JmsConfig {
    @Value("${activeMq.brokerUrl}")
    private String jmsBrokerUrl;

    @Value("${activeMq.text-destination}")
    private String textDestination;

    @Value("${activeMq.event-destination}")
    private String eventDestination;

    /**
     * 定义jms connectionFactory，覆盖spring boot的默认配置
     *
     * @return ConnectionFactory 连接工厂
     * @throws JMSException
     */
    @Bean
    public ConnectionFactory connectionFactory() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.createTopicConnection();
        activeMQConnectionFactory.setBrokerURL(jmsBrokerUrl);
        return activeMQConnectionFactory;
    }

    @Bean(name = "text")
    public ActiveMQTopic activeMQTopicText(){
        return new ActiveMQTopic(textDestination);
    }

    @Bean(name = "event")
    public ActiveMQTopic activeMQTopicEvent(){
        return new ActiveMQTopic(eventDestination);
    }
}
