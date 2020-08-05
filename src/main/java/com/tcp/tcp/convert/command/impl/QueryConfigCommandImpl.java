package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/10.
 * Time: 下午5:24.
 * @Title:
 * @Description: 0x0107 运营平台发送查询充电桩配置信息命令
 */
@Service("queryConfigCommandImpl")
public class QueryConfigCommandImpl extends BaseCommand implements CommandService<byte[]> {

    @Override
    public byte[] getByteInfo(byte[] bytes, String pileNum, short cmd) {
        /* 数据段头部 */
        byte[] data = getSendHead(pileNum, cmd, 1);
        int num = headLength;
        return getData(data, num);
    }
}
