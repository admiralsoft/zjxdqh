package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 *         Date: 2018/7/24.
 *         Time: 上午11:51.
 * @Title:
 * @Description: 远程配置充电数据回复解析
 */
@Service("remoteChargingConfigParseImpl")
public class RemoteChargingConfigParseImpl extends BaseParse implements TCPParseService<Integer> {

    @Override
    public Integer getInfo(byte[] data) {

        int index = SUBSCRIPT;
        //程序对这个指令不操作,默认是0
        int result = (int) data[index];
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), result);
        return result;
    }
}
