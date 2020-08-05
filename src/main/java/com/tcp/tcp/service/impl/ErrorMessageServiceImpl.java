package com.tcp.tcp.service.impl;


import com.tcp.bean.LogFaultCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.service.BaseService;
import com.tcp.mapper.LogFaultCodeMapper;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.convert.SimpleByteMessageResult;
import com.tcp.util.StringUtil;
import com.tcp.util.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author TcT
 *         Date: 2018/8/3.
 *         Time: 上午10:16.
 * @Title:
 * @Description: 桩体上报故障信息
 */
@Service("errorMessageServiceImpl")
@Slf4j
public class ErrorMessageServiceImpl extends BaseService<Object> implements TCPService{

    @Resource
    private LogFaultCodeMapper logFaultCodeMapper;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        log.info("桩号: "+pileNum+"故障上报.");
        /*解析*/
        byte gunNum = 0;
        try {
            List<LogFaultCode> info = errorMessageParseImpl.getInfo(bytes);
            /*数据拆分*/
            LogFaultCode code;
            List<LogFaultCode> logFaultCodes = new ArrayList<>();
            /*枪号*/
            if (!info.isEmpty()) {
                gunNum = info.get(0).getGunNum();
                for (LogFaultCode logFaultCode : info) {
                    String msg = logFaultCode.getErrorMsg();
                    String[] codes = msg.split(",");
                    for (int i = 0; i < codes.length; i++) {
                        code = new LogFaultCode();
                        BeanUtils.copyProperties(logFaultCode, code);
                        code.setPileNum(pileNum);
                        code.setId(StringUtil.get32UUID());
                        code.setCreateTime(new Date());
                        code.setErrorMsg(codes[i]);
                        logFaultCodes.add(code);
                    }
                }
                /*入库*/
                if (!logFaultCodes.isEmpty()) {
                    for (LogFaultCode logFaultCode : logFaultCodes) {
                        if(StringUtils.isNotBlank(logFaultCode.getErrorMsg())) {
                            logFaultCodeMapper.insert(logFaultCode);
                        }
                    }
                } else {
                    log.info("故障码解析内容为空:" + info.size());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("故障解析异常:",e);
        }

        // 回复 桩故障 信息已收到
        sendMessage(ctx, MessageToByteConvert.convertWrapReport(SimpleByteMessageResult.getInstance(gunNum), pileNum, TCPCode.warring_charging_back),
                pileNum, TCPCode.warring_charging_back);
    }
}
