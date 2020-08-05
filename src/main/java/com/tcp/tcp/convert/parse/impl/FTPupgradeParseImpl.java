package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author Xu
 */
@Service("fTPupgradeParseImpl")
@Slf4j
public class FTPupgradeParseImpl extends BaseParse implements TCPParseService<Byte> {

    @Override
    public Byte getInfo(byte[] data) {
        log.info("充电桩软件升级命令功能码回复");
        int num = SUBSCRIPT;
        byte i = (byte)DataUtil.oneBytesToInt((data[num++]));

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), i);
        return i;
    }
}
