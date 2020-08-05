package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * @Author Xu
 */
@Service("activeSendSetUpdateTimeCommandImpl")
public class ActiveSendSetUpdateTimeCommandImpl extends BaseCommand implements CommandService<Object> {
    @Override
    public byte[] getByteInfo(Object o, String pileNum, short cmd) {
        /* 数据段头部 */
        byte[] data = getSendHead(pileNum, cmd, 1);
        int num = headLength;
        //发送校准时间
        byte[] time = DataUtil.getTime();
        System.arraycopy(time, 0, data, num, 4);
        return getData(data, num);
    }
}
