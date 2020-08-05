package com.tcp.tcp.service.impl;


import com.tcp.core.code.MQCode;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.vo.receive.vo.ReservationResultVo;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 下午3:46.
 * @Title:
 * @Description: 0x0212充电桩回复预约查询命令
 */
@Service("reservationResultServiceImpl")
public class ReservationResultServiceImpl extends BaseService<Object> implements TCPService {

    @Resource
    private RabbitMqSender rabbitMqSender;


    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        ReservationResultVo info = preReservationResultParseImpl.getInfo(bytes);
        //查询信息不做入库处理,直接回复MQ
        rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"预约查询回复", MQCode.SEARCH_O,true,info));

    }
}
