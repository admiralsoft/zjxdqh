package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.LogVinCharge;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.AddData;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * 0x1014 车辆VIN验证充电
 * @Author yaweiXu
 */
@Service("carVINParseImpl")
public class CarVINParseImpl extends BaseParse implements TCPParseService<LogVinCharge> {
    @Override
    public LogVinCharge getInfo(byte[] data) {
        int num = SUBSCRIPT;
        LogVinCharge logVinCharge = new LogVinCharge();
        //vin
        logVinCharge.setVinNum(new AddData().appendNext(data,17,0).toString());
        //枪号
        logVinCharge.setGunNum((byte)(DataUtil.oneBytesToInt((data[17+num++]))));

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), logVinCharge);
        return logVinCharge;
    }
}
