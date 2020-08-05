package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 0x0209充电桩软件升级命令功能码
 * @Author Xu
 */
@Service("fTPUpgradeServiceImpl")
@Slf4j
public class FTPUpgradeServiceImpl extends BaseService<Object> implements TCPService{
    @Resource
    RabbitMqSender rabbitMqSender;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        //1. 升级成功
        //2. 升级失败
        Byte info = fTPupgradeParseImpl.getInfo(bytes);
        if(info == 1){
            //通知运营平台升级成功了(升级结果 0207指令返回）
            redisUtil.setValueExpire(RedisCode.PILE_UPGRADE, pileNum, MQCode.PACKDATA_DOWN);
//            JsonObject resultObj = getResultObj(pileNum, "FTP升级成功", MQCode.NET_UPDATE, true);
//            rabbitMqSender.sendRabbitmqCollectDirect(resultObj);
            log.info("桩号:{}FTP升级数据接收成功!",pileNum);
        }
        if(info == 2){
            //通知运营平台升级失败了
            JsonObject resultObj = getResultObj(pileNum, "FTP升级失败", MQCode.NET_UPDATE, false);
            rabbitMqSender.sendRabbitmqCollectDirect(resultObj);
            log.info("桩号:{}FTP升级失败!",pileNum);
        }else{
            //通知运营平台升级失败了
            JsonObject resultObj = getResultObj(pileNum, "FTP升级失败", MQCode.NET_UPDATE, false);
        }

    }
}
