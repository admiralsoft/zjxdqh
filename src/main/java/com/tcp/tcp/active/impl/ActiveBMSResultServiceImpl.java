package com.tcp.tcp.active.impl;

import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.convert.SimpleByteMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 0x0016 运营平台返回BMS返回数据
 * @Author yorking
 */
@Service("activeBMSResultServiceImpl")
@Slf4j
public class ActiveBMSResultServiceImpl extends AbStracActiveService<SimpleByteMessageResult> {


    @Override
    public DictCodeEnum.SendMessageResult activeSend(SimpleByteMessageResult result, String pileNum) {
        //运营平台返回BMS返回数据
        return sendMessage(MessageToByteConvert.convertWrapReport(result, pileNum, getCmd()), pileNum, getCmd());
    }

    @Override
    public short getCmd() {
        return TCPCode.pile_bms_message_back;
    }
}
