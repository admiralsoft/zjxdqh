package com.tcp.tcp.convert.command.impl;

import com.tcp.bean.MPreCommand;
import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;
/**
 * @author TcT
 *         Date: 2018/8/4.
 *         Time: 下午9:51.
 * @Title:
 * @Description: 0x0102 运营平台发送预约命令
 */
@Service("activeReservationCommandImpl")
public class ActiveReservationCommandImpl extends BaseCommand implements CommandService<MPreCommand>{

    @Override
    public byte[] getByteInfo(MPreCommand mPreCommand, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        /*预约枪号*/
        data[num++] = DataUtil.intToHexByte(mPreCommand.getGunNum());
        /*1. 开始预约 2. 停止预约*/
        data[num++] = mPreCommand.getPreType();
        /*预约帐号*/
        byte[] bytes = DataUtil.StringToAsciiByte(mPreCommand.getPreAccount(), 32);
        for (int i = 0; i < bytes.length; i++) {
            data[num++] = bytes[i];
        }
        int preType = (int) mPreCommand.getPreType();
        if (preType == 1) {
            /*预约开始时间*/
            long star = mPreCommand.getPreStartTime().getTime();
            byte[] fourBytes = DataUtil.longToFourBytes(star);
            for (int i = 0; i < fourBytes.length; i++) {
                data[num++] = bytes[i];
            }
            /*预约结束时间*/
            long end = mPreCommand.getPreEndTime().getTime();
            byte[] fourBytes2 = DataUtil.longToFourBytes(end);
            for (int i = 0; i < fourBytes2.length; i++) {
                data[num++] = bytes[i];
            }
        }else {
            /*预约开始\结束时间*/
            for (int i = 0; i < 8; i++) {
                data[num++] = 0;
            }
        }
        return getData(data, num);
    }
}
