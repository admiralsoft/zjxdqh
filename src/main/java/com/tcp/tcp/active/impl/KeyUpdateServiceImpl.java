package com.tcp.tcp.active.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.active.AbStracActiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送密钥更新命令
 * @Author Xu
 */
@Service("keyUpdateServiceImpl")
@Slf4j
public class KeyUpdateServiceImpl extends AbStracActiveService<String> {

    @Resource
    RabbitMqSender rabbitMqSender;

    @Override
    public short getCmd() {
        return TCPCode.KEY_UPDATE;
    }

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String o, String pileNum) {
        DictCodeEnum.SendMessageResult res = sendMessage(timeUpdateCommandImpl.getByteInfo(o,pileNum,getCmd()),pileNum,getCmd());
        if (res == DictCodeEnum.SendMessageResult.NO_CHANNEL) {
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, MQResultEnum.MQ_RESULT_FAIL.getDesc(),MQCode.KEY_UPDATE,false));
        }
        return res;
    }
}
