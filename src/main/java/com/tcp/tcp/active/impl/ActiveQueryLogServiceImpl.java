package com.tcp.tcp.active.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.enums.MQResultEnum;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.active.AbStracActiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * 0x0114 运营平台查询记录命令
 * @Author Xu
 */
@Service("activeQueryLogServiceImpl")
@Slf4j
public class ActiveQueryLogServiceImpl extends AbStracActiveService {

    @Resource
    private RabbitMqSender rabbitMqSender;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public short getCmd() {
        return TCPCode.QUERY_LOG;
    }

    @Override
    public DictCodeEnum.SendMessageResult activeSend(Object o, String pileNum) {
        Byte by = (Byte) o;
        boolean exists = redisUtil.exists(RedisCode.QUERY, pileNum);
        long lon = System.currentTimeMillis()/1000;
        //查看是否查询过该key
        if(exists){
            //查看最后查询记录的时间
            Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().reverseRangeWithScores(RedisCode.QUERY + pileNum, 0l, 2l);
            Iterator<ZSetOperations.TypedTuple<Object>> iterator = set.iterator();
                ZSetOperations.TypedTuple<Object> obj = iterator.next();
                Long lon1 = Long.valueOf(obj.getValue().toString());
            //当天记录不做重复查询,重复数据不做入库操作
            if(lon-lon1 < 24*60*60){
                rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "操作不要太频繁",MQCode.QUERY_LOG,false));
                return DictCodeEnum.SendMessageResult.NO_HOST;
            }
        }
        DictCodeEnum.SendMessageResult res = sendMessage(activeQueryLogCommandImpl.getByteInfo(by,pileNum,getCmd()),pileNum,getCmd());
        if (res == DictCodeEnum.SendMessageResult.SEND_FAIL) {
            log.info("{} : {} 查询记录命令 出现错误：%s", getCmd(), pileNum, o);
            //通知mq下发失败了
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, MQResultEnum.MQ_RESULT_FAIL.getDesc(),MQCode.QUERY_LOG,false));
        }
        return res;
    }
}
