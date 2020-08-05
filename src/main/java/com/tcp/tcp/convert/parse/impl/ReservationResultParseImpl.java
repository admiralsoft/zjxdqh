package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TcT
 *         Date: 2018/8/4.
 *         Time: 下午10:47.
 * @Title:
 * @Description: 0x0202充电桩回复预约指令功能码
 */
@Service("reservationResultParseImpl")
public class ReservationResultParseImpl extends BaseParse implements TCPParseService<Map<String, Integer>> {

    @Override
    public Map<String, Integer> getInfo(byte[] data) {
        int index = SUBSCRIPT;
        Map<String, Integer> map = new HashMap<>();
        int result = data[index++];
        int gunNum = data[index];
        map.put("result",result);
        map.put("gunNum",gunNum);
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), map);
        return map;
    }
}
