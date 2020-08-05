package com.tcp.tcp.service.impl;

import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 0x1013 发送时间同步指令信息
 * @Author Xu
 */
@Service("timeSynchronousServiceImpl")
@Slf4j
public class TimeSynchronousServiceImpl extends BaseService<Object> implements TCPService {
    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        //发送校时命令
        DictCodeEnum.SendMessageResult res = sendMessage(activeSendSetUpdateTimeCommandImpl.getByteInfo(null, pileNum,TCPCode.synchronization_code), pileNum, TCPCode.synchronization_code);
        if (res == DictCodeEnum.SendMessageResult.NO_CHANNEL) {
            log.info("桩暂时不在线....");
        }else if (res == DictCodeEnum.SendMessageResult.SEND_OK) {
            log.info("下发成功....");
        }
    }
}
