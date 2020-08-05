package com.tcp.mq.config;



import com.tcp.mq.config.RabbitMqConfig;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 直连交换机：Direct exchange 直连交换机是一种带路由功能的交换机，一个队列会和一个交换机绑定，
 * 除此之外再绑定一个routing_key，当消息被发送的时候，需要指定一个binding_key，
 * 这个消息被送达交换机的时候，就会被这个交换机送到指定的队列里面去。
 * 同样的一个binding_key也是支持应用到多个队列中的。
 * 扇形交换机：Fanout exchange  发送广播
 * 主题交换机：Topic exchange
 * 直连交换机的routing_key方案非常简单，如果我们希望一条消息发送给多个队列，
 * 那么这个交换机需要绑定上非常多的routing_key，
 * 假设每个交换机上都绑定一堆的routing_key连接到各个队列上。那么消息的管理就会异常地困难。
 * 首部交换机：Headers exchange
 * 首部交换机是忽略routing_key的一种路由方式。路由器和交换机路由的规则是通过Headers信息来交换的，
 * 这个有点像HTTP的Headers。将一个交换机声明成首部交换机，绑定一个队列的时候，定义一个Hash的数据结构，
 * 消息发送的时候，会携带一组hash数据结构的信息，当Hash的内容匹配上的时候，消息就会被写入队列
 * 新增业务时，两个步骤
 * 1、增加 queue bean ，参照queueXXXX方法
 * 2增加 queue 和 exchange的binding,参照 bindingExchangeXXXX方法(topic类似于模糊查询，对不同的routekey中特定关键词进行消费)
 * @author yaweiXu
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class RabbitMqExchangeConfig {


    @Value("${spring.rabbitmq.exchange_name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queue_name}")
    private String receiveQueue;

    @Value("${spring.rabbitmq.queue_next_name}")
    private String receiveNextQueue;
    @Value("${spring.rabbitmq.queue_charging_name}")
    private String receiveChargingQueue;

    @Value("${spring.rabbitmq.queue_send_name}")
    private String sendQueue;


    @Value("${spring.rabbitmq.receive_route_key}")
    private String receiveRoute;
    @Value("${spring.rabbitmq.send_route_key}")
    private String sendRoute;


    public String getExchangeName() {
        return exchangeName;
    }

    public String getReceiveRoute() {
        return receiveRoute;
    }

    public String getReceiveQueue() {
        return receiveQueue;
    }

    public String getReceiveNextQueue() {
        return receiveNextQueue;
    }
    public String getReceiveChargingQueue() {
        return receiveChargingQueue;
    }
    @Bean
    public Queue sendMessage() {
        return new Queue(sendQueue, true);
    }

    @Bean
    public Queue recieve1Message() {
        return new Queue(receiveQueue, true);
    }
    @Bean
    public Queue recieveCharginMessage() {
        return new Queue(receiveChargingQueue, true);
    }

    @Bean
    public Queue receiveNextMessage() {
        return new Queue(receiveNextQueue, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }


    @Bean
    Binding bindingExchangeMessage(Queue sendMessage, TopicExchange exchange) {
        return BindingBuilder.bind(sendMessage).to(exchange).with(sendRoute);
    }
    @Bean
    Binding bindingExchange1Message(Queue recieve1Message, TopicExchange exchange) {
        return BindingBuilder.bind(recieve1Message).to(exchange).with(receiveRoute);
    }
    @Bean
    Binding bindingExchangeChargingMessage(Queue recieveCharginMessage, TopicExchange exchange) {
        return BindingBuilder.bind(recieveCharginMessage).to(exchange).with(receiveRoute);
    }
    @Bean
    Binding bindingExchange2Message(Queue receiveNextMessage, TopicExchange exchange) {
        return BindingBuilder.bind(receiveNextMessage).to(exchange).with(receiveRoute);
    }

}