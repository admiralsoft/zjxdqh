package com.tcp.tcp.active.impl;

import com.tcp.bean.JsonObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.core.code.TCPCode;
import com.tcp.util.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author TcT
 * Date: 2018/8/6.
 * Time: 上午8:46.
 * @Title:
 * @Description: 查询预约结果
 */
@Service("activeQueryReservationServicelmpl")
public class ActiveQueryReservationServicelmpl extends AbStracActiveService<String> {

    @Resource
    private RabbitMqSender rabbitMqSender;


    @Override
    public DictCodeEnum.SendMessageResult activeSend(String strings, String pileNum) {

        HashMap map = JsonUtils.toObject(strings, HashMap.class);

        //获取枪口号,发送到桩体
        if (map != null) {
            int gunNum = (Integer) map.get("gunNum");
            //下发
            return sendMessage(upgradeRequestCommandImpl.getByteInfo(gunNum, pileNum, getCmd()), pileNum, getCmd());
        } else {
            //数据异常,返回前台
            JsonObject jsonObject = getResultObj(pileNum, "数据异常", MQCode.SEARCH_O, false);
            rabbitMqSender.sendRabbitmqCollectDirect(jsonObject);
        }
        return DictCodeEnum.SendMessageResult.NO_HOST;
    }

    @Override
    public short getCmd() {
        return TCPCode.QUERY_RESERVATION;
    }
}
