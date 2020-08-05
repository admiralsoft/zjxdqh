package com.tcp.tcp.service.impl;

import com.tcp.bean.LogWarningProtectCode;
import com.tcp.core.code.TCPCode;
import com.tcp.mapper.LogWarningProtectCodeMapper;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.convert.SimpleByteMessageResult;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author TcT
 *         Date: 2018/8/3.
 *         Time: 下午2:51.
 * @Title:
 * @Description:  0x1012 报警码及保护码上报
 */
@Service("warningAndGuardServiceImpl")
public class WarningAndGuardServiceImpl extends BaseService<Object> implements TCPService{

    @Resource
    private LogWarningProtectCodeMapper logWarningProtectCodeMapper;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        LogWarningProtectCode info = warningAndGuardParseImpl.getInfo(bytes);
        info.setId(StringUtil.get32UUID());
        info.setPileNum(pileNum);
        info.setCreateTime(new Date());
        //入库
        logWarningProtectCodeMapper.insert(info);
        /*回复桩体*/
        sendMessage(ctx, MessageToByteConvert.convertWrapReport(SimpleByteMessageResult.getInstance(info.getGunNum()), pileNum, TCPCode.warring_result_charging_back),
                pileNum, TCPCode.warring_result_charging_back);

    }
}
