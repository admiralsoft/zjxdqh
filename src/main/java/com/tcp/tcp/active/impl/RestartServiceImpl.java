package com.tcp.tcp.active.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 0x0113 运营平台重启充电桩命令
 * @Author Xu
 */
@Slf4j
@Service("restartServiceImpl")
public class RestartServiceImpl extends AbStracActiveService<String> {

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String str, String pileNum) {
        //桩下发消息
        return sendMessage(activeRestartCommandImpl.getByteInfo("1",pileNum,getCmd()),pileNum,getCmd());
    }

    @Override
    protected int getMqCode() {
        return MQCode.RESTART_PILE;
    }

    @Override
    public short getCmd() {
        return TCPCode.RESTART_CHARGE;
    }
}
