package com.zjxdqh.evcs.mq;

import com.rabbitmq.client.Channel;
import com.zjxdqh.evcs.service.EvcsService;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.tools.mq.MqObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;


/**
 * Amqp消息注册监听
 *
 * @Author yaweiXu
 */
@Configuration
@Log4j2
public class AmqpListener {

    @Autowired
    private EvcsService evcsService;

    @RabbitListener(id = "rabbitListener",
            containerFactory = "xdRabbitListenerContainerFactory",
            bindings = @QueueBinding(
                    value = @Queue(value = "${spring.rabbitmq.queue_name}", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = "${spring.rabbitmq.exchange_name}", type = ExchangeTypes.TOPIC, durable = "true"),
                    key = "${spring.rabbitmq.receive_route_key}"
            )
    )
    public void handleMessage(Message message, @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) {
        String mess = new String(message.getBody());

        try {
            MqObject mqObj = JsonUtils.parse(mess, MqObject.class);
            if (mqObj == null) {
                log.error(" MQ消息格式解析失败: [{}], 请使用JSON字符串，例:{}", mess, "{\"code\":4,\"orderNo\":\"XXXX\",\"deviceNo\":\"2XX01\",\"gunNo\":\"1\"}");
            } else {
                List<OperatorInfo> infos = null;
                if (!StringUtils.isEmpty(mqObj.getDeviceNo())) {
                    infos = evcsService.getSupersiveInfoByPilenum(mqObj.getDeviceNo());
                    if (CollectionUtils.isEmpty(infos)) {
                        log.error("未找到 桩[{}]对应的 对接方信息", mqObj.getDeviceNo());
                    }
                    SuperviseUtils.OperatorInfoList.set(infos);
                } else if (mqObj.getCode() == MqObject.CODE_PRICE_CHANGE
                            && !StringUtils.isEmpty(mqObj.getExt())) {
                    String sid = mqObj.getExt().split(",")[0].trim();
                    infos = evcsService.getSupersiveInfoBySid(sid);
                    if (CollectionUtils.isEmpty(infos)) {
                        log.error("未找到 场站[{}]对应的 对接方信息", sid);
                    }
                }
                SuperviseUtils.OperatorInfoList.set(infos);
                switch (mqObj.getCode()) {
                    case MqObject.CODE_ORDER_INFO:
                        evcsService.pushOrderInfo(mqObj.getOrderNo());
                        break;
                    case MqObject.CODE_ORDER_STATE:
                        evcsService.pushOrderStat(mqObj.getOrderNo());
                        break;
                    case MqObject.CODE_ORDER_STOP:
                        evcsService.pushOrderStop(mqObj.getOrderNo());
                        break;
                    case MqObject.CODE_PILE_STATE:
                        evcsService.pushPileState(mqObj.getDeviceNo(), mqObj.getGunNo());
                        break;
                    case MqObject.CODE_START_CHARGE_RESULT:
                        evcsService.pushStartChargingResult(mqObj.getOrderNo());
                        break;
                    case MqObject.CODE_PRICE_CHANGE:
                        evcsService.pushSiteChange(mqObj.getExt().split(","));
                        break;
                    default:
                        log.error(" MQ消息CODE 处理失败: {}", mess);
                        break;
                }

                log.info("MQ消息 处理成功: {}", mess);

            }

            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("MQ消费发生异常：", e);
            //处理代码
            try {
                channel.basicNack(tag, false, false);
            } catch (IOException e1) {
                log.error("MQ消费发生异常11：", e);
            }
        }
    }


//    @RabbitListener(id = "rabbitTimeoutDelayListener",
//            containerFactory = "xdRabbitListenerContainerFactory",
//            queues = "${spring.rabbitmq.delay_timeout_queue_name}"
//    )
//    public void handleDelayMessage( Message message,  @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) {
//        String mess = new String(message.getBody());
//
//
//        try {
//            MqObject mqObj = JsonUtils.parse(mess, MqObject.class);
//            if (mqObj != null) {
//                String orderNo = mqObj.getOrderNo();
//
//                log.debug("超时退款订单号: {}", orderNo);
//                int res = evcsService.cancelOrderBackMoney(orderNo);
//                if (res != 1) {
//                    log.error("订单[{}]超时退款失败.", orderNo);
//                } else {
//                    log.info("订单[{}]超时退款完成." , orderNo);
//                }
//            }
//
//            channel.basicAck(tag, false);
//        }catch(Exception e){
//            log.error("MQ 订单超时消费发生异常：", e);
//            //处理代码
//            try {
//                channel.basicNack(tag, false,false);
//            } catch (IOException e1) {
//                log.error("MQ 订单超时消费发生异常11：", e);
//            }
//        }
//    }

    public static void main(String[] args) {


    }


}
