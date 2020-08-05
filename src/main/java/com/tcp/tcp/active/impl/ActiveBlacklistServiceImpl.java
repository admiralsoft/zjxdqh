package com.tcp.tcp.active.impl;

import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.vo.send.vo.SendBlacklistVo;
import com.tcp.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 *  0x0115 运营平台下发/更新⿊名单
 *
 * @author yorking
 */
@Service("activeBlacklistServiceImpl")
public class ActiveBlacklistServiceImpl extends AbStracActiveService<SendBlacklistVo> {
    @Override
    public DictCodeEnum.SendMessageResult activeSend(SendBlacklistVo configVo, String pileNum) {
        DictCodeEnum.SendMessageResult res = sendMessage(MessageToByteConvert.convertWrapReport(configVo, pileNum, getCmd()), pileNum, getCmd());

        configVo.setId(StringUtil.get32UUID());
        blacklistMapper.insert(configVo);

        return res;
    }

    @Override
    public short getCmd() {
        return TCPCode.BLACKLIST;
    }
}
