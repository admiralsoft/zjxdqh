package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.LogFaultCode;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.LogFaultCodeVo;
import com.tcp.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author TcT
 * Date: 2018/8/3.
 * Time: 上午10:26.
 * @Title:
 * @Description: 桩体上报故障信息
 */
@Service("errorMessageParseImpl")
@Slf4j
public class ErrorMessageParseImpl extends BaseParse implements TCPParseService<List<LogFaultCode>> {


    @Override
    public List<LogFaultCode> getInfo(byte[] data) {
        int num = SUBSCRIPT;
        LogFaultCode faultCode;
        //枪号
        List<LogFaultCode> faultCodes = null;
        try {
            byte gunNum = data[num++]; //==2
            //当前索引到去掉包尾的区段,为故障码及填充字段
            int faultCodeInfo = data.length - 5;
            //剩余数据段
            int last = faultCodeInfo - num;
            //故障码个数
            int codeNum = last / 8;
            faultCodes = new LinkedList<>();
            for (int i = 0; i < codeNum; i++) {
                faultCode = new LogFaultCode();
                /*枪号*/
                faultCode.setGunNum(gunNum);
                /*故障码*/
                byte[] code = {data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]};
                /*保存原始*/
                faultCode.setFaultCode(Arrays.toString(code));
                /*码表标识符*/
                byte mark = code[0];
                if (mark != 0) {
                    faultCode.setErrorTable(mark);
                    /*故障码解析*/
                    StringBuffer builder = DataUtil.parseCode(code);
                    /*添加标识1的故障码*/
                    faultCode.setErrorMsg(builder.toString());
                    faultCodes.add(faultCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("故障解析异常:",e);
        }

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), faultCodes);
        return faultCodes;
    }


}
