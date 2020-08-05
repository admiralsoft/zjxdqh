package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.ReservationResultVo;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 下午4:10.
 * @Title:
 * @Description: 0x0212充电桩回复预约查询命令
 */
@Service("preReservationResultParseImpl")
public class PreReservationResultParseImpl extends BaseParse implements TCPParseService<ReservationResultVo> {


    @Override
    public ReservationResultVo getInfo(byte[] data) {

        int index = SUBSCRIPT;
        ReservationResultVo resultVo = new ReservationResultVo();
        resultVo.setResult((int)data[index++]);
        byte[] bytes = new byte[32];
        for (int i = 0; i < 32; i++) {
            bytes[i] = data[index++];
        }
        resultVo.setPreAccount(DataUtil.byteAsciiToString(bytes));
        resultVo.setPreStarTime(new Date(DataUtil.fourByteToLong(new byte[]{data[index++],data[index++],data[index++],data[index++]})));
        resultVo.setPreEndTime(new Date(DataUtil.fourByteToLong(new byte[]{data[index++],data[index++],data[index++],data[index++]})));
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), resultVo);
        return resultVo;
    }
}
