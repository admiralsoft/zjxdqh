package com.tcp.tcp.service.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  0x0213充电桩回复重启命令
 * @Author Xu
 */
@Slf4j
@Service("restartChargeServiceImpl")
public class RestartChargeServiceImpl extends BaseService<Byte> implements TCPService {

    @Resource
    RabbitMqSender rabbitMqSender;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        Byte info = restartChargeParseImpl.getInfo(bytes);
        //查询信息不做入库处理,直接回复MQ
        if(info == 1){
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"充电桩回复重启命令成功", MQCode.RESTART_PILE,true));
            log.info("桩号:{},重启成功",pileNum);
        }else {
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"充电桩回复重启命令失败", MQCode.RESTART_PILE,false));
            log.info("桩号:{},重启失败",pileNum);
        }
    }
}
