package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.SendOfflineInfo;
import com.tcp.tcp.vo.send.vo.SendOfflineInfoVo;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 下午11:24.
 * @Title:
 * @Description: 离线回复
 */
@Service("offlineInfoResultCommandImpl")
public class OfflineInfoResultCommandImpl extends BaseCommand implements CommandService<SendOfflineInfoVo>{

    @Override
    public byte[] getByteInfo(SendOfflineInfoVo sendOfflineInfoVo, String pileNum, short cmd) {
        /* 数据段头部 */
        byte[] bytes = getSendHead(pileNum, cmd, 1);
        /*数据包下标*/
        int num = headLength;
        /*记录个数*/
        bytes[num++] = DataUtil.intToHexByte(sendOfflineInfoVo.getNum());
        /*记录数据段*/
        LinkedList<SendOfflineInfo> infos = sendOfflineInfoVo.getSendOfflineInfos();
        for (SendOfflineInfo info : infos) {
            bytes[num++] = DataUtil.intToHexByte(info.getSerialNum());
            bytes[num++] = DataUtil.intToHexByte(info.getReusltCode());
        }
        /*数据填充及包尾填充*/
        return getData(bytes,num);
    }
}
