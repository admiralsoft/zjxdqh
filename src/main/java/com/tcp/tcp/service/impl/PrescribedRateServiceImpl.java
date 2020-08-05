package com.tcp.tcp.service.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.ResultCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.vo.send.vo.SendPrescribedRateVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @author TcT
 * Date: 2018/7/20.
 * Time: 上午11:26.
 * @Title: 充电设备费率及时间段回复
 * @Description:
 */
@Service("prescribedRateServiceImpl")
@Slf4j
public class PrescribedRateServiceImpl extends BaseService<Object> implements TCPService {

    @Resource
    private RabbitMqSender rabbitMqSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        /*解析*/
        SendPrescribedRateVo info = prescribedRateParseImpl.getInfo(bytes);

        if (info.getCode() != ResultCode.result_success) {
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"配置失败!",MQCode.CONFIG_TIME_PRICE,false));
            log.info("充电设备费率及时间段回复:配置失败");
        } else {

            short sendCode = DictCodeEnum.SendRecievCodeMap.getSendCode(TCPCode.prescribed_rate);
            if (sendCode  > 0) {
                String pileCodeKey = "PILECODE:" + pileNum + ":" + sendCode;
                Boolean has = redisTemplate.hasKey(pileCodeKey);
                if (has != null && has) {
                    redisTemplate.delete(pileCodeKey);
                    log.debug("删除重试缓存信息, 指令[{}],桩[{}]", sendCode, pileNum);
                }
            }

            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"配置成功!",MQCode.CONFIG_TIME_PRICE,true));
            log.info("充电设备费率及时间段回复:配置成功");
        }
    }
}
