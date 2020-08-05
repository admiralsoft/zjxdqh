package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.core.service.BaseService;
import com.tcp.mapper.LogMsDataMapper;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.active.ActiveService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.SimpleByteMessageResult;
import com.tcp.tcp.vo.receive.vo.LogMsDataVo;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 0x1016 BMS返回数据
 * @Author Xu
 */
@Service("pileBMSMessageServiceImpl")
@Slf4j
public class PileBMSMessageServiceImpl extends BaseService<Object> implements TCPService{

    @Autowired
    private LogMsDataMapper msDataMapper;

    @Autowired
    @Qualifier("activeBMSResultServiceImpl")
    private ActiveService activeBMSResultServiceImpl;


    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        String cmd_device =ByteToMessageConvert.unWrapConvertCmd(bytes) +":" + ByteToMessageConvert.unWrapConvertDeviceNo(bytes);
        System.out.println(cmd_device +"上传 BMS 数据 ");
        log.info("{} 上传 BMS 数据", cmd_device);
        try {
            LogMsDataVo bmsDataVo = ByteToMessageConvert.unWrapConvert(bytes, LogMsDataVo.class);
            if (bmsDataVo != null && bmsDataVo.getGunNum() > 0) {

                // 发送回复消息
                activeBMSResultServiceImpl.activeSend(SimpleByteMessageResult.getInstance(bmsDataVo.getGunNum()), pileNum);

                bmsDataVo.setId(StringUtil.get32UUID());
                bmsDataVo.setPileNum(pileNum);
                bmsDataVo.setCreateTime(new Date());
                msDataMapper.insert(bmsDataVo);
            }
        } catch (Exception e) {
            log.error("{} 上传 BMS 数据 发生异常", cmd_device);
        }

    }
}
