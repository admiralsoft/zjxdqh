package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.send.vo.SendPrescribedRateVo;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 *         Date: 2018/7/20.
 *         Time: 上午11:59.
 * @Title: 充电设备费率及时间段回复解析
 * @Description:
 */
@Service("prescribedRateParseImpl")
public class PrescribedRateParseImpl extends BaseParse implements TCPParseService<SendPrescribedRateVo> {


    @Override
    public SendPrescribedRateVo getInfo(byte[] data) {
        int index = SUBSCRIPT;
        SendPrescribedRateVo rateVo = new SendPrescribedRateVo();
        /*回复值*/
        rateVo.setCode(data[index]);

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), rateVo);
        return rateVo;
    }
}
