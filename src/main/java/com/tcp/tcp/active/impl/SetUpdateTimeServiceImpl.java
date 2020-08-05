package com.tcp.tcp.active.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.core.code.TCPCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 0x0106运营平台发送校时命令
 * @Author yaweiXu
 */
@Service("setUpdateTimeServiceImpl")
public class SetUpdateTimeServiceImpl  extends AbStracActiveService<String[]> {

    private final static Logger LOGGER = LoggerFactory.getLogger(SetUpdateTimeServiceImpl.class);

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String[] strings, String pileNum) {

        //发送校时命令
        return sendMessage(activeSendSetUpdateTimeCommandImpl.getByteInfo(strings, pileNum, getCmd()), pileNum, getCmd());

    }

    @Override
    protected int getMqCode(){
        return MQCode.UPDATE_TIME;
    }
    @Override
    public short getCmd() {
        return TCPCode.PILE_SCHOOL_TIME;
    }
}
