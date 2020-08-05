package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.vo.KeyUpdateVo;
import com.tcp.util.DataUtil;
import com.tcp.util.JsonUtils;
import org.springframework.stereotype.Service;

/**
 * @Author Xu
 */
@Service("timeUpdateCommandImpl")
public class TimeUpdateCommandImpl extends BaseCommand implements CommandService<String> {
    @Override
    public byte[] getByteInfo(String keyUpdateVo, String pileNum, short cmd) {
        /* 数据段头部 */
        KeyUpdateVo keyUpdateVo1 = JsonUtils.toObject(keyUpdateVo, KeyUpdateVo.class);
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        data[num++] = (byte)Integer.parseInt(keyUpdateVo1.getGunNum().toString(),16);
        byte[] bytes= DataUtil.StringToAsciiByte(keyUpdateVo1.getPass(),16);
        for (int i = 0; i < bytes.length; i++) {
            data[num++] = bytes[i];
        }
        byte[] time = DataUtil.getTime();
        System.arraycopy(time, 0, data, num, 4);
        return data;
    }
}
