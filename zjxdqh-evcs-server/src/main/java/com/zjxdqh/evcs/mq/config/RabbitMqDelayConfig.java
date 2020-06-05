package com.zjxdqh.evcs.mq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yaweiXu
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqDelayConfig {


    @Value("${spring.rabbitmq.delay_timeout_exchange_name}")
    public String delayTimeoutExchange;

    @Value("${spring.rabbitmq.delay_timeout_queue_name}")
    public String delayTimeoutQueue;

    @Value("${spring.rabbitmq.delay_timeout_millsec:180000}")
    public int delayTimeoutMillsec;

    @Bean
    public Queue delayTimeoutQueue() {
        return new Queue(delayTimeoutQueue, true);
    }


    /**
     * 定义广播模式的延时交换机 无需绑定路由
     * @return
     */
    @Bean
    FanoutExchange delayTimeoutExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(delayTimeoutExchange, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    /**
     * 绑定延时队列与交换机
     * @return
     */
    @Bean
    public Binding delayPayBind() {
        return BindingBuilder.bind(delayTimeoutQueue()).to(delayTimeoutExchange());
    }



}