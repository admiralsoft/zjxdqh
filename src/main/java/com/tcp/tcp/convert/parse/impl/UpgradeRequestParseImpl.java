package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 上午11:37.
 * @Title:
 * @Description: 升级请求回复
 */
@Service("upgradeRequestParseImpl")
public class UpgradeRequestParseImpl extends BaseParse implements TCPParseService<Integer> {


    @Override
    public Integer getInfo(byte[] data) {

        int num = SUBSCRIPT;

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), data[num]);
        /*回复结果 1. 可以升级 2. 禁止升级 */
        return (int) data[num];
    }
}
