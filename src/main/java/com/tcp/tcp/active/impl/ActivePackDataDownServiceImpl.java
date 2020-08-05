package com.tcp.tcp.active.impl;

import com.tcp.bean.MUpgradeResult;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.util.JsonUtils;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/15.
 * Time: 上午10:01.
 * @Title:
 * @Description: 数据包分包下载
 */
@Service("activePackDataDownServiceImpl")
public class ActivePackDataDownServiceImpl extends AbStracActiveService<String> {

    @Override
    public short getCmd() {
        return TCPCode.DOWN_PACKDATA_UPDATE;
    }

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String json, String pileNum) {

        MUpgradeResult mUpgradeResult = JsonUtils.toObject(json, MUpgradeResult.class);
        byte[] byteInfo = activePackDataDownCommandImpl.getByteInfo(mUpgradeResult, pileNum, getCmd());
        if (byteInfo == null){
            sender.sendRabbitmqCollectDirect(getResultObj(pileNum,"下载失败", MQCode.PACKDATA_DOWN,false));
            return DictCodeEnum.SendMessageResult.NO_CHANNEL;
        }else {
            return sendMessage(byteInfo,pileNum,getCmd());
        }
    }


}
