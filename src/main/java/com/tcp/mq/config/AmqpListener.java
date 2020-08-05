package com.tcp.mq.config;

import com.rabbitmq.client.Channel;
import com.tcp.bean.JsonObject;
import com.tcp.biz.pile.PileService;
import com.tcp.core.retry.RetryRecord;
import com.tcp.core.service.TcpSendServer;
import com.tcp.log.CustomerLogger;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.mq.handle.MQMessageHandle;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.util.DataUtil;
import com.tcp.util.DateUtil;
import com.tcp.util.JsonUtils;
import com.tcp.util.OutUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Amqp消息注册监听
 * @Author yaweiXu
 *
 */
@Configuration
@AutoConfigureAfter(RabbitDelayConfig.class)
public class AmqpListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpListener.class);

    @Autowired
    protected MQMessageHandle mqMessageHandlel;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisTemplate byteRedisTemplate;


    @Autowired
    private TcpSendServer tcpSendServer;

    @Autowired
    private RabbitMqSender mqSender;

    @Autowired
    private PileService pileService;


    @RabbitListener(id = "rabbitListener",
                    containerFactory = "xdRabbitListenerContainerFactory",
                    bindings = @QueueBinding(
                            value = @Queue(value = "${spring.rabbitmq.queue_send_name}", durable = "true", autoDelete = "false"),
                            exchange = @Exchange(value = "${spring.rabbitmq.exchange_name}", type = ExchangeTypes.TOPIC, durable="true"),
                            key = "${spring.rabbitmq.send_route_key}"
                             )
                    )
    public void handleMessage( Message message,  @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) {
        String mess = new String(message.getBody());

        try{
            if("exception".equals(mess)){
            }

            OutUtil.println("队列消息：" + mess);
            /* MQ数据 我这是用的数组 也可以用JSON的方式 这个看个人需求 */
            JsonObject messObj = JsonUtils.toObject(mess, JsonObject.class);
            OutUtil.println("桩号：" + messObj.getPileNum());
            OutUtil.println("队列消息校验结果：" + (messObj.getCode() > 0 ? "校验通过" : "校验失败"));
            CustomerLogger.printMqLogger(messObj, CustomerLogger.MQ_RECIEVE, message.getMessageProperties().getCorrelationIdString());
            LOGGER.info("consumer--:"+message.getMessageProperties()+":"+mess);
            System.out.println("tag ::: " + message.getMessageProperties().getDeliveryTag());
            // deliveryTag是消息传送的次数，我这里是为了让消息队列的第一个消息到达的时候抛出异常，处理异常让消息重新回到队列，然后再次抛出异常，处理异常拒绝让消息重回队列
//                    if (message.getMessageProperties().getDeliveryTag() == 1 || message.getMessageProperties().getDeliveryTag() == 2)
//                    {
//                        throw new Exception();
//                    }
            //业务处理代码
            mqMessageHandlel.process(messObj);
            // false只确认当前一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(tag, false);
        }catch(Exception e){
            LOGGER.error("MQ消费发生异常：", e);
            //处理代码
            try {
                channel.basicNack(tag, false,false);
            } catch (IOException e1) {
                LOGGER.error("MQ消费发生异常11：", e);
            }
        }
    }

    @RabbitListener(id = "rabbitDelayListener",
            containerFactory = "xdRabbitListenerContainerFactory",
           queues = "${spring.rabbitmq.send_pile_retry_queue_name}"
    )
    public void handleDelayMessage( Message message,  @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) {
        String mess = new String(message.getBody());

        try {
            System.out.println(DateUtil.getNow() + "  .... " + mess);
            LOGGER.debug("指令延迟重发消息:" +mess);

            RetryRecord retryRecord = JsonUtils.toObject(mess, RetryRecord.class);

            if (retryRecord != null && retryRecord.getCurrent() < retryRecord.getMaxCount()) {

                byte[] cmdContext = (byte[]) byteRedisTemplate.opsForValue().get(retryRecord.getRetryKey());
                if (cmdContext != null && cmdContext.length > 0) {
                    String pileNum = ByteToMessageConvert.unWrapConvertDeviceNo(cmdContext);
                    int cmd = ByteToMessageConvert.unWrapConvertCmd(cmdContext);
                    tcpSendServer.sendMessage(cmdContext, pileNum, cmd);

                    int next = retryRecord.getCurrent();
                    if (next < retryRecord.getMaxCount()) {
                        retryRecord.setCurrent(next+1);
                        mqSender.sendDelay(retryRecord);
                        LOGGER.debug("指令延迟第[{}]次重发消息:{}", next, JsonUtils.toJson(retryRecord));

                    } else {
                        LOGGER.error("桩号[{}]延迟指令[{}]下发失败",pileNum, DataUtil.cmdTohexString(cmd));
                        redisTemplate.delete(retryRecord.getRetryKey());
                    }
                }

            }

            channel.basicAck(tag, false);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                channel.basicNack(tag, false,false);
            } catch (IOException e1) {
                LOGGER.error("MQ延时消费发生异常11：", e);
            }
        }

    }


    @RabbitListener(id = "offlineRabbitDelayListener",
            containerFactory = "xdRabbitListenerContainerFactory",
            queues = "${spring.rabbitmq.off_line_send_pile_retry_queue_name}"
    )
    public void offlineHandleDelayMessage(Message message,  @Header(AmqpHeaders.CHANNEL) Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) {
        String mess = new String(message.getBody());
        try {
            LOGGER.info("offlineHandleDelayMessage:"+DateUtil.getNow() + "消息：" + mess);

            RetryRecord retryRecord = JsonUtils.toObject(mess, RetryRecord.class);

            LOGGER.info("getCurrent:"+retryRecord.getCurrent());
            LOGGER.info("getMaxCount:"+retryRecord.getMaxCount());

            if (retryRecord != null && retryRecord.getCurrent() <= retryRecord.getMaxCount()) {
                //桩长时间离线检查
                LOGGER.info("RetryKey:"+retryRecord.getRetryKey());
                boolean flag=pileService.offLineService(retryRecord.getRetryKey(),retryRecord.getCurrent());
                    int next = retryRecord.getCurrent() + 1;
                    if (next <=retryRecord.getMaxCount() && flag) {
                        retryRecord.setCurrent(next);
                        mqSender.offLinesendDelay(retryRecord);
                    }
            }
            channel.basicAck(tag, false);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                channel.basicNack(tag, false,false);
            } catch (IOException e1) {
                LOGGER.error("MQ延时消费发生异常：", e1);
            }
            LOGGER.error("MQ延时消费发生异常", e);
        }
    }

    public static void main(String[] args) {

        String str="{\"current\":1,\"maxCount\":5,\"retryKey\":\"全季酒店负1楼_测试别删|3330000000000123\"}";

        RetryRecord retryRecord = JsonUtils.toObject(str, RetryRecord.class);

        System.out.print(retryRecord);
        System.out.println(JsonUtils.toJson(retryRecord));
        System.out.println(JsonUtils.toJson(JsonUtils.toJson(retryRecord)));


    }



}
