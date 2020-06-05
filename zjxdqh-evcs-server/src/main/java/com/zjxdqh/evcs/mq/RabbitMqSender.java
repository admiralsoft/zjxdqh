package com.zjxdqh.evcs.mq;

import com.zjxdqh.evcs.mq.config.RabbitMqDelayConfig;
import com.zjxdqh.evcs.mq.config.RabbitMqExchangeConfig;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.param.StartChargeParam;
import com.zjxdqh.tools.mq.JsonObject;
import com.zjxdqh.tools.mq.MqObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 充电流程 消息
 *
 * @author Yorking
 */
@Component
@Lazy
@Log4j2
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitMqExchangeConfig rabbitMqExchangeConfig;

    @Autowired
    private RabbitMqDelayConfig mqDelayConfig;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(@Qualifier("xdRabbitTemplate") RabbitTemplate xdRabbitTemplate) {
        this.rabbitTemplate = xdRabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发送消息至 采集队列
     *
     * @param obj
     */
    public void sendOpsSendQueue(JsonObject obj) {
        obj.setMqId(UUID.randomUUID().toString());
        obj.setTimestemp(System.currentTimeMillis());
        CorrelationData correlationData = new CorrelationData(obj.getMqId());
        HashMap param = JsonUtils.parse(JsonUtils.toJsonString(obj), HashMap.class);
        this.rabbitTemplate.convertAndSend(rabbitMqExchangeConfig.getOpsSendQueue(), param, correlationData);

    }

    public void send(String str) {
        this.rabbitTemplate.convertAndSend(str);
    }


    /**
     * 发送信息 至 超时退款队列
     *
     * @param orderNo
     */
    public void sendTimeoutDelayQueue(String orderNo) {
        MqObject mq = new MqObject();
        mq.setOrderNo(orderNo);
        rabbitTemplate.convertAndSend(mqDelayConfig.delayTimeoutExchange, "", mq, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            // 毫秒为单位，指定此消息的延时时长
            System.out.println(mqDelayConfig.delayTimeoutMillsec);
            message.getMessageProperties().setDelay(mqDelayConfig.delayTimeoutMillsec);
            return message;
        });
    }

    /**
     * 请求开始充电
     *
     * @param chargeParam
     * @return
     */
    public void start(StartChargeParam chargeParam) {

        // cmd:3 开始充电
        this.sendOpsSendQueue(getStartStopMq(chargeParam, 3));

        // 延时 超时队列（3分钟）
        this.sendTimeoutDelayQueue(chargeParam.getOrderNo());
    }

    /**
     * 请求结束充电
     *
     * @param chargeParam
     * @return
     */
    public void stop(StartChargeParam chargeParam) {

        // cmd:4 结束充电
        this.sendOpsSendQueue(getStartStopMq(chargeParam, 4));
    }

    private JsonObject getStartStopMq(StartChargeParam chargeParam, int cmd) {
        JsonObject obj = new JsonObject();
        obj.setCode(101);
        obj.setPileNum(chargeParam.getPileNum());
        obj.setTimestemp(System.currentTimeMillis());

        Map<String, Object> param = new HashMap<>();
        param.put("count", chargeParam.getUserId());
        param.put("balance", 0);
        param.put("preBalance", chargeParam.getAmount());
        param.put("gunnum", chargeParam.getGunNum());
        param.put("assistPowerOption", 12);
        param.put("pileNum", chargeParam.getPileNum());
        param.put("freeBalance", 0);
        // 结束充电
        param.put("cmd", cmd);
        param.put("orderNum", chargeParam.getOrderNo());
        obj.setObj(param);

        return obj;
    }


    /**
     * 回调方法
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (correlationData != null) {
            log.info("confirm: " + correlationData.getId() + " ; ack=" + ack + " ; cause=" + cause);
        }
    }

}
