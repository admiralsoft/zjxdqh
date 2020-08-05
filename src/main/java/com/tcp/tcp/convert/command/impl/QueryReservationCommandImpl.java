package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 *         Date: 2018/8/6.
 *         Time: 上午8:56.
 * @Title:
 * @Description: 查询预约结果
 */
@Service("queryReservationCommandImpl")
public class QueryReservationCommandImpl extends BaseCommand implements CommandService<Integer> {

    @Override
    public byte[] getByteInfo(Integer gunNum, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        /*枪号*/
        int gun = gunNum;
        data[num++] = (byte) gun;

        return getData(data, num);
    }
}
