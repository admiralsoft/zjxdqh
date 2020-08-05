package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.LogWarningProtectCode;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 *         Date: 2018/8/3.
 *         Time: 下午2:58.
 * @Title:
 * @Description:
 */
@Service("warningAndGuardParseImpl")
public class WarningAndGuardParseImpl  extends BaseParse implements TCPParseService<LogWarningProtectCode>{


    @Override
    public LogWarningProtectCode getInfo(byte[] data) {
        int num = SUBSCRIPT;
        LogWarningProtectCode protectCode = new LogWarningProtectCode();
        /*枪口号*/
        protectCode.setGunNum(data[num++]);
        /*告警码1*/
        byte[] warning = {data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]};
        /*码表标识*/
        //todo:码表二次迭代具体处理
        String mark = String.valueOf(warning[0]);
        StringBuffer buffer = DataUtil.parseCode(warning);
        protectCode.setWarningCode1(buffer.toString());
        /*告警码2*/
        byte[] warning2 = {data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]};
        String mark2 = String.valueOf(warning2[0]);
        StringBuffer warningBuff = DataUtil.parseCode(warning2);
        protectCode.setWarningCode2(warningBuff.toString());
        /*保护码1*/
        byte[] protect = {data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]};
        String mark3 = String.valueOf(protect[0]);
        StringBuffer protectBuff = DataUtil.parseCode(protect);
        protectCode.setProtectCode1(protectBuff.toString());
        /*保护码2*/
        byte[] protectTwo = {data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]};
        String mark4 = String.valueOf(protectTwo[0]);
        StringBuffer protectTwoBuff = DataUtil.parseCode(protectTwo);
        protectCode.setProtectCode2(protectTwoBuff.toString());

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), protectCode);
        return protectCode;
    }
}
