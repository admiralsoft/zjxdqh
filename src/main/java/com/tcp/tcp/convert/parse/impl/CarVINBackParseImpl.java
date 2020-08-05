package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.LogVinResult;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.AddData;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author yaweiXu
 */
@Service("carVINBackParseImpl")
public class CarVINBackParseImpl extends BaseParse implements TCPParseService<LogVinResult> {
    @Override
    public LogVinResult getInfo(byte[] data) {
        int num = SUBSCRIPT;
        LogVinResult carVINInfoVo = new LogVinResult();
        //返回结果
        carVINInfoVo.setVerificationResult(data[num++]);
        //枪号
        carVINInfoVo.setGunNum(DataUtil.oneBytesToInt((data[num++])));
        //订单号
        carVINInfoVo.setOrderNum(String.valueOf(new AddData().appendNext(data,32,2)));
        //充电金额
        carVINInfoVo.setRechargeAmount(new BigDecimal(new AddData().appendNext(data,4,34).toString()));

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), carVINInfoVo);
        return carVINInfoVo;
    }
}
