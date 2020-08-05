package com.tcp.tcp.convert.command.impl;


import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/6.
 * Time: 下午7:15.
 * @Title:
 * @Description: 升级请求
 */
@Service("upgradeRequestCommandImpl")
public class UpgradeRequestCommandImpl extends BaseCommand implements CommandService<Integer> {


    @Override
    public byte[] getByteInfo(Integer type, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        int itype = type;
        data[num++] = (byte) itype;

        return getData(data, num);
    }
}
