package com.tcp.tcp.active.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.vo.send.vo.SendPileLocalConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 0x0104 运营平台发送远程配置充电设备本地参数命令
 *
 * @author yorking
 */
@Service("activePileLocalConfigServiceImpl")
@Slf4j
public class ActivePileLocalConfigServiceImpl extends AbStracActiveService<SendPileLocalConfigVo> {
    @Override
    public DictCodeEnum.SendMessageResult activeSend(SendPileLocalConfigVo configVo, String pileNum) {
        return  sendMessage(MessageToByteConvert.convertWrapReport(configVo, pileNum, getCmd()), pileNum, getCmd());
    }

    @Override
    public short getCmd() {
        return TCPCode.PILE_LOCAL_CONFIG;
    }

    @Override
    protected int getMqCode(){
        return MQCode.CONFIG;
    }
}
