package com.tcp.tcp.service.impl;

import com.tcp.bean.MPreCommand;
import com.tcp.core.service.BaseService;
import com.tcp.mapper.MPreCommandMapper;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author TcT
 *         Date: 2018/8/4.
 *         Time: 下午10:38.
 * @Title:
 * @Description: 预约指令回复
 */
@Service("reservationServiceImpl")
public class ReservationServiceImpl extends BaseService<Object> implements TCPService {

    @Resource
    private MPreCommandMapper mPreCommandMapper;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        /*解析数据*/
        Map<String, Integer> map = reservationResultParseImpl.getInfo(bytes);
        int result = map.get("result");
        int gunNum = map.get("gunNum");

        MPreCommand mPreCommand = new MPreCommand();
        mPreCommand.setPillNum(pileNum);
        mPreCommand.setGunNum(gunNum);

        /*1，预约成功 2，预约失败 3，取消预约成功 4，取消预约失败*/
        if (result != 1) {
            /*根据桩号及枪号查找该MQ,最新的一次调用消息*/
            //todo: 1. redis取值,存桩号及枪号,再更新状态. 2. sql获取时间区间差加桩号枪号,更新状态, TIMESTAMPDIFF(second,create_time,NOW()) < 120
            mPreCommandMapper.getLastData();
            /*更改状态字段为失败*/
            MPreCommand command = new MPreCommand();

        }else {
            /*回复成功?*/

        }
    }
}
