package com.tcp.mq.base;

import com.tcp.bean.JsonObject;
import com.tcp.core.enums.MQ2QueneEnum;
import com.tcp.core.enums.MQCodeEnum;
import com.tcp.core.retry.RetryRecord;
import com.tcp.log.CustomerLogger;
import com.tcp.mq.config.RabbitDelayConfig;
import com.tcp.mq.config.RabbitMqExchangeConfig;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Lazy
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitMqExchangeConfig rabbitMqExchangeConfig;

    @Autowired
    private RabbitDelayConfig delayConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqSender.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(@Qualifier("xdRabbitTemplate") RabbitTemplate xdRabbitTemplate) {
        this.rabbitTemplate = xdRabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 所有发送到Topic Exchange的消息被转发到所有关心RouteKey中指定Topic的Queue上
     * @param obj
     */
    public void sendRabbitmqCollectDirect(JsonObject obj) {
        obj.setMqId(StringUtil.get32UUID());
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MQCodeEnum codeEnum = MQCodeEnum.getCodeEnum(obj.getCode());
        if (codeEnum == null) {
            LOGGER.error("MQ CODE:{} 未获取到对应 发送消息的队列。", obj.getCode());
            return;
        }
        if (codeEnum.getToQuene() == MQ2QueneEnum.ToOperation) {
            // 运营平台的消息队列
            System.out.println("运营平台消息："+rabbitMqExchangeConfig.getReceiveNextQueue());
            this.rabbitTemplate.convertAndSend(rabbitMqExchangeConfig.getReceiveNextQueue(), obj,correlationData);
        }else if (codeEnum.getToQuene() == MQ2QueneEnum.ToOperationCharging) {
            // 运营平台的消息队列
            System.out.println("运营平台充电中消息："+rabbitMqExchangeConfig.getReceiveNextQueue());
            this.rabbitTemplate.convertAndSend(rabbitMqExchangeConfig.getReceiveChargingQueue(), obj,correlationData);
        }else if (codeEnum.getToQuene() == MQ2QueneEnum.ToMainTain) {
            // 运维系统 的消息
            System.out.println("运维平台消息："+rabbitMqExchangeConfig.getReceiveQueue());
            this.rabbitTemplate.convertAndSend(rabbitMqExchangeConfig.getReceiveQueue(), obj,correlationData);
        } else if (codeEnum.getToQuene() == MQ2QueneEnum.ToOperTain) {
            // 运营 运维都要发消息
            System.out.println("运维、运营平台消息："+rabbitMqExchangeConfig.getReceiveRoute());
            this.rabbitTemplate.convertAndSend(rabbitMqExchangeConfig.getExchangeName(), rabbitMqExchangeConfig.getReceiveRoute(), obj, correlationData);
        } else {
            LOGGER.error("MQ CODE:{} 未获取到对应 发送消息的队列。", obj.getCode());
            return;
        }
        System.out.println(obj.getCode() + "  发送消息至 " +codeEnum.toString() + " : " +codeEnum.getToQuene().getToname());
        CustomerLogger.printMqLogger(obj, CustomerLogger.MQ_SEND, correlationData.getId());
    }

    public void send(String str){
        this.rabbitTemplate.convertAndSend(str);
    }

    /**
     * 发送 延时消费队列
     * @param msg       消息内容
     */
    public void sendDelay(Object msg){
        long delay =0;
        if (msg instanceof RetryRecord) {
            RetryRecord retry = (RetryRecord) msg;
            if (retry.getCurrent() > retry.getMaxCount()) {
                return;
            }
            delay = retry.getExpire();
        }
        // 默认延迟5秒
        int finalDelay = delay > 0 ? (int)delay : 5000;
        rabbitTemplate.convertAndSend(delayConfig.getDeLayExchangeName(), "", msg, message ->{
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            // 毫秒为单位，指定此消息的延时时长
            message.getMessageProperties().setDelay(finalDelay);
            return message;
        });
    }

    /**
     * 发送 延时消费队列 桩长时间离线
     * @param msg       消息内容
     */
    public void offLinesendDelay(Object msg){
        rabbitTemplate.convertAndSend(delayConfig.getOffLineDeLayExchangeName(), "", msg, message ->{
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            RetryRecord retryRecord =(RetryRecord)msg;
            //3,6,12,24,48小时提醒
            Integer delay=10800000;
            if(retryRecord.getCurrent()==1)
            {
                delay=10800000;
            }else if(retryRecord.getCurrent()==2)
            {
                delay=21600000;
            }
            else if(retryRecord.getCurrent()==3)
            {
                delay=43200000;
            }else if(retryRecord.getCurrent()==4)
            {
                delay=86400000;
            }else if(retryRecord.getCurrent()==5)
            {
                delay=172800000;
            }
            // 毫秒为单位，指定此消息的延时时长
            message.getMessageProperties().setDelay(delay);
            return message;
        });
    }



    /** 回调方法 */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (correlationData != null) {
            LOGGER.info("confirm: " + correlationData.getId() +  " ; ack=" + ack + " ; cause=" + cause);
        }
    }
}
