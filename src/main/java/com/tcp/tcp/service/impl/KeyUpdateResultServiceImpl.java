package com.tcp.tcp.service.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 充电桩回复密钥更新
 * @Author yaweiXu
 */
@Service("keyUpdateResultServiceImpl")
public class KeyUpdateResultServiceImpl  extends BaseService<Byte> implements TCPService {
    @Resource
    RabbitMqSender rabbitMqSender;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        Byte info = keyUpdateResultParseImpl.getInfo(bytes);
        //通知mq更新结果
        if(info ==1){
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "充电桩回复密钥更新成功", MQCode.KEY_UPDATE,true));
        }else {
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "充电桩回复密钥更新失败", MQCode.KEY_UPDATE,false));
        }
    }
}
