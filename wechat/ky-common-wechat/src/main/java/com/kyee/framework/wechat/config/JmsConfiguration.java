package com.kyee.framework.wechat.config;

import com.kyee.framework.wechat.service.receiver.JmsMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * @author 夏之阳
 * 创建时间：2015-08-07 9:39
 * 任务号：MOBILEDEVELOP-9900
 * 创建说明：jms配制类
 */

@Configuration
@EnableJms
@Import(JmsMessageListener.class)
public class JmsConfiguration {
    @Value("${activeMq.brokerUrl}")
    private String jmsBrokerUrl;

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

    /**
     * 设置接收端的container
     *
     * @param connectionFactory 连接工厂
     * @return jms listener 容器
     */
    @Bean
    JmsListenerContainerFactory<?> jmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
}
