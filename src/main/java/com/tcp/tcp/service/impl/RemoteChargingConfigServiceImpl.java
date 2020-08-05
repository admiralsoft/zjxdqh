package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.ResultCode;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @author TcT
 *         Date: 2018/7/24.
 *         Time: 上午10:16.
 * @Title:
 * @Description: 发送远程配置充电系统
 */
@Service("remoteChargingConfigServiceImpl")
@Slf4j
public class RemoteChargingConfigServiceImpl extends BaseService<Object> implements TCPService{

    @Resource
    private RabbitMqSender rabbitMqSender;


    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        /*解析*/
        Integer resultCode = remoteChargingConfigParseImpl.getInfo(bytes);
        log.info(pileNum+"发送远程配置充电系统");

        if (resultCode != ResultCode.result_success){
            JsonObject jsonObject = getResultObj(pileNum, "配置失败", MQCode.CONFIG_SYS,false);
            rabbitMqSender.sendRabbitmqCollectDirect(jsonObject);
            log.info("发送远程配置充电系统:配置失败");
        }else {
            JsonObject jsonObject = getResultObj(pileNum, "配置成功", MQCode.CONFIG_SYS,true);
            rabbitMqSender.sendRabbitmqCollectDirect(jsonObject);
            log.info("发送远程配置充电系统:配置成功");
        }
    }
}
