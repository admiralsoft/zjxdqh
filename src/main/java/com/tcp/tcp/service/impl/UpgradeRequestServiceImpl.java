package com.tcp.tcp.service.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 上午11:17.
 * @Title:
 * @Description: 升级请求回复
 */
@Service("upgradeRequestServiceImpl")
@Slf4j
public class UpgradeRequestServiceImpl extends BaseService<Object> implements TCPService {

    @Resource
    private RabbitMqSender rabbitMqSender;


    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        /*解析数据*/
        Integer result = upgradeRequestParseImpl.getInfo(bytes);

        if (result == 1){
            //不需要回复消费端做处理请求
            //在这里发送升级操作
            boolean exists = redisUtil.exists(RedisCode.FTP, pileNum);
            if(exists){
                String str = (String)redisUtil.get(RedisCode.FTP, pileNum);
                DictCodeEnum.SendMessageResult messageResult = sendMessage(updateFTPCommandImpl.getByteInfo(str,pileNum,TCPCode.FTP_UPDATE), pileNum, TCPCode.FTP_UPDATE);
                //下发指令无论成功失败要把对应的Key删除
                redisUtil.remove(RedisCode.FTP,pileNum);
                if(messageResult == DictCodeEnum.SendMessageResult.NO_CHANNEL){
                    log.info("运营平台发送充电桩软件升级命令（FTP升级）:"+"下发失败");
                    //在这一步并不是桩回复失败了而是下发指令失败了
                    rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"软件升级命令（FTP升级）下发失败",MQCode.NET_UPDATE,false));
                }
                if(messageResult == DictCodeEnum.SendMessageResult.SEND_OK){
                    log.info("运营平台发送充电桩软件升级命令（FTP升级）:"+"下发成功");
                }
            }else {
                log.info("运营平台发送充电桩软件升级命令（FTP升级）:"+"下发失败");
                rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"软件升级命令（FTP升级）禁止升级", MQCode.NET_UPDATE,false));
            }
        }else {
            log.info("运营平台发送充电桩软件升级命令（FTP升级）:"+"下发失败");
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"软件升级命令（FTP升级）禁止升级", MQCode.NET_UPDATE,false));
        }
    }

}
