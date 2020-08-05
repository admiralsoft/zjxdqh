package com.tcp.tcp.active.impl;


import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.active.AbStracActiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author TcT
 * Date: 2018/8/10.
 * Time: 下午5:02.
 * @Title:
 * @Description: 0x0107 运营平台发送查询充电桩配置信息命令
 */
@Service("activeQueryConfigServiceImpl")
@Slf4j
public class ActiveQueryConfigServiceImpl extends AbStracActiveService<String> {

    @Resource
    RabbitMqSender rabbitMqSender;
    @Override
    public DictCodeEnum.SendMessageResult activeSend(String s, String pileNum) {

        /*无数据体,直接下发装*/
        DictCodeEnum.SendMessageResult i = sendMessage(queryConfigCommandImpl.getByteInfo(new byte[]{}, pileNum, getCmd()), pileNum, getCmd());

        if (i == DictCodeEnum.SendMessageResult.SEND_FAIL) {
            log.info("{} : {} 查询充电桩配置信息 出现错误：%s", getCmd(), pileNum);
            //通知mq下发失败了
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "查询充电桩配置信息出错", MQCode.QUERY_CONFIG,false));
        }
        return i;
    }

    @Override
    public short getCmd() {
        return TCPCode.QUERY_CONFIG;
    }
}
