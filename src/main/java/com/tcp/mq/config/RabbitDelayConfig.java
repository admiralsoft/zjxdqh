package com.tcp.mq.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 配置延迟消费队列
 */
@Configuration
@AutoConfigureAfter(RabbitMqExchangeConfig.class)
public class RabbitDelayConfig {


    @Value("${spring.rabbitmq.delay_exchange_name}")
    private String deLayExchangeName;

    @Value("${spring.rabbitmq.send_pile_retry_queue_name}")
    private String sendPileRetryQueue;


    @Value("${spring.rabbitmq.off_line_delay_exchange_name}")
    private String offLineDeLayExchangeName;

    @Value("${spring.rabbitmq.off_line_send_pile_retry_queue_name}")
    private String offLineSendPileRetryQueue;

    public String getOffLineDeLayExchangeName() {
        return offLineDeLayExchangeName;
    }

    public String getOffLineSendPileRetryQueue() {
        return offLineSendPileRetryQueue;
    }

    public String getDeLayExchangeName() {
        return deLayExchangeName;
    }

    public String getSendPileRetryQueue() {
        return sendPileRetryQueue;
    }

    @Bean
    public Queue sendPileRetryQueue() {
        return new Queue(sendPileRetryQueue, true);
    }

    @Bean
    public Queue offLinesendPileRetryQueue() {
        return new Queue(offLineSendPileRetryQueue, true);
    }


    /**
     *
     // 定义广播模式的延时交换机 无需绑定路由
     * @return
     */
    @Bean
    FanoutExchange delayExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(deLayExchangeName, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    /**
     *     // 绑定延时队列与交换机
     * @return
     */
    @Bean
    public Binding delayPayBind() {
        return BindingBuilder.bind(sendPileRetryQueue()).to(delayExchange());
    }


    /**
     *
     // 定义广播模式的延时交换机 无需绑定路由
     * @return
     */
    @Bean
    FanoutExchange offLinedelayExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(offLineDeLayExchangeName, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    /**
     *     // 绑定延时队列与交换机
     * @return
     */
    @Bean
    public Binding offLinedelayPayBind() {
        return BindingBuilder.bind(offLinesendPileRetryQueue()).to(offLinedelayExchange());
    }


}