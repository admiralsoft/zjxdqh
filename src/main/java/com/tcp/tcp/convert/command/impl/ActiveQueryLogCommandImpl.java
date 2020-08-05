package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import org.springframework.stereotype.Service;

/**
 * 0x0114 运营平台查询记录命令
 * @Author Xu
 */
@Service("activeQueryLogCommandImpl")
public class ActiveQueryLogCommandImpl extends BaseCommand implements CommandService<Byte> {
    @Override
    public byte[] getByteInfo(Byte aByte, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        data[num++] = aByte;
        return getData(data,num);
    }
}
