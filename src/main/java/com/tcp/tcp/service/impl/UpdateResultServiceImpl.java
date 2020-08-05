package com.tcp.tcp.service.impl;


import com.tcp.core.code.MQCode;
import com.tcp.core.service.BaseService;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 下午2:05.
 * @Title:
 * @Description: 0x0217 充电桩回复软件/固件更新查询命令
 */
@Service("updateResultServiceImpl")
@Slf4j
public class UpdateResultServiceImpl extends BaseService<Object> implements TCPService {

    @Resource
    private RabbitMqSender rabbitMqSender;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        Map<String, Integer> info = updateResultParseImpl.getInfo(bytes);
        Integer result = info.get("result");
        Integer failResult = info.get("failResult");
        String mResult = "";
        String mFailResult = "";
        switch (result){
            case 1:
                mResult = "查询返回更新成功";
                break;
            case 2:
                mResult = "查询返回更新失败";
                break;
            case 3:
                mResult = "主动发送更新成功";
                break;
            case 4:
                mResult = "主动发送更新失败";
                break;
                default:
                    mResult = String.valueOf(result);
                    break;
        }
        switch (failResult){
            case 0:
                mFailResult = "成功";
                break;
            case 1:
                mFailResult = "软件验证失败";
                break;
            case 2:
                mFailResult = "电桩更新失败";
                break;
            default:
                mFailResult = String.valueOf(failResult);
                break;
        }
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("result",mResult);
        hashMap.put("mFailResult",mFailResult);
        logger.info("查询动态:{},执行结果:{}",mResult,mFailResult);
        rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"成功或失败原因", MQCode.QUERY_UPDATE_RESULT,true,hashMap));

    }
}
