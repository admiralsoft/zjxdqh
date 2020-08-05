package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import org.springframework.stereotype.Service;

/**
 * 0x0206充电桩回复校时功能码
 * @Author yaweiXu
 */
@Service("schoolTimeBackParseImpl")
public class SchoolTimeBackParseImpl extends BaseParse implements TCPParseService<String> {
    @Override
    public String getInfo(byte[] data) {
        int num = SUBSCRIPT;
        //1配置成功
        //2配置失败
        //不管成功失败都会回复0
        String result = String.valueOf(data[num++]);
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), result);
        return result;
    }
}
