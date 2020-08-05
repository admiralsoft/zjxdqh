package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Xu
 */
@Service("restartChargeParseImpl")
@Slf4j
public class RestartChargeParseImpl extends BaseParse implements TCPParseService<Byte> {
    @Override
    public Byte getInfo(byte[] data) {
        int num = SUBSCRIPT;

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), data[num]);
        return data[num];
    }
}
