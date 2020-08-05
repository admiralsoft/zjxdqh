package com.tcp.tcp.active;

import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.core.service.BaseService;
import com.tcp.log.CustomerLogger;
import com.tcp.mapper.TPileMapper;
import com.tcp.mq.base.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public abstract class AbStracActiveService<T> extends BaseService<T> implements ActiveService<T> {

    @Resource
    RabbitMqSender rabbitMqSender;

    @Override
    public final void send(T t, String pileNum) {
        DictCodeEnum.SendMessageResult result = null;
        try {
            result = activeSend(t, pileNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 指令下发成功
        if (result == DictCodeEnum.SendMessageResult.SEND_OK) {
            CustomerLogger.printCommandLogger(getCmd(), pileNum, t);
        } else if (getMqCode() > 0 && result == DictCodeEnum.SendMessageResult.SEND_FAIL){
            //下发失败时， 回复MQ消息
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, MQResultEnum.MQ_RESULT_FAIL.getDesc(), getMqCode(),false));
        }
    }

    public abstract short getCmd();

    /**
     * 下发失败时，回复的MQCode
     * @return
     */
    protected int getMqCode(){
        return 0;
    }


}
