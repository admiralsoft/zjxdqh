package com.tcp.tcp.service.impl;

import com.tcp.core.code.MQCode;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 0x0206充电桩回复校时功能码
 * @Author yaweiXu
 */
@Service("schoolTimeBackServiceImpl")
@Slf4j
public class SchoolTimeBackServiceImpl  extends BaseService<Object> implements TCPService {
    @Resource
    RabbitMqSender rabbitMqSender;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        /*解析*/
        //1配置成功
        //2配置失败
        String resultCode = schoolTimeBackParseImpl.getInfo(bytes);
        log.info("充电桩回复校时功能码 桩号:{}, 结果：{}",pileNum, resultCode);
        if(StringUtils.isNotBlank(resultCode)){
            if(resultCode.equals("1")){
                log.info("充电桩回复校时功能码指令下发成功");
                //通知mq下发成功了
                rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "充电桩回复校时功能码指令下发成功", MQCode.UPDATE_TIME,true));
            }else if(resultCode.equals("2")){
                //重新发送？
                //通知mq下发失败了
                rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "充电桩回复校时功能码指令下发失败", MQCode.UPDATE_TIME,false));
            }else {
                rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "充电桩回复校时功能码指令下发失败", MQCode.UPDATE_TIME,false));
            }
        }
    }
}
