package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.vo.RestartVo;
import com.tcp.util.DataUtil;
import com.tcp.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 0x0113 运营平台重启充电桩命令
 * @Author Xu
 */
@Slf4j
@Service("activeRestartCommandImpl")
public class ActiveRestartCommandImpl extends BaseCommand implements CommandService<String> {
    @Override
    public byte[] getByteInfo(String str, String pileNum, short cmd) {
        String s = JsonUtils.toObject(str, String.class);
        byte  aByte= Byte.valueOf(s);
        /* 数据段头部 */
        byte[] data = getSendHead(pileNum, cmd, 1);
        int num = headLength;
        data[num++]  =  aByte;
        return getData(data, num);
    }
}
