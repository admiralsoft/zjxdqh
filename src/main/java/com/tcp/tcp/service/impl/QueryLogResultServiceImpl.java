package com.tcp.tcp.service.impl;

import com.tcp.bean.LogQuery;
import com.tcp.core.code.MQCode;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.DataUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @Author Xu
 */
@Service("queryLogResultServiceImpl")
@Slf4j
public class QueryLogResultServiceImpl extends BaseService<Object> implements TCPService {
    @Resource
    RabbitMqSender rabbitMqSender;
    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        byte[] bytes1 = pileNum.getBytes();
        int a = bytes.length;
        byte[] b = new byte[bytes.length+16];
        System.arraycopy(bytes,0,b,0,bytes.length);
        for (int i = 0; i < bytes1.length; i++) {
            b[a++] = bytes1[i];
        }
        LogQuery info = queryLogParseImpl.getInfo(b);
        if(null != info){
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "查询回复记录成功", MQCode.QUERY_LOG,true));
            log.info("桩号:{}查询回复记录:查询成功",pileNum);
        }else{
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "查询回复记录失败", MQCode.QUERY_LOG,false));
            log.info("桩号:{}查询回复记录:查询失败",pileNum);

        }
    }

}
