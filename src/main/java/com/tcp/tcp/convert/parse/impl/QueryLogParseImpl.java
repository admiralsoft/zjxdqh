package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.LogQuery;
import com.tcp.core.code.RedisCode;
import com.tcp.mapper.LogQueryMapper;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;
import com.tcp.util.RedisUtil;
import com.tcp.util.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author Xu
 */
@Service("queryLogParseImpl")
public class QueryLogParseImpl extends BaseParse implements TCPParseService<LogQuery> {
    @Resource
    LogQueryMapper logQueryMapper;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    RedisUtil redisUtil;
    @Override
    public LogQuery getInfo(byte[] data) {
        int index = SUBSCRIPT;
        boolean boo = true;
        int a = 0;
        //有多少个数
        int b = 0;
        //类型
        byte datum = data[index++];
        //记录个数
        b = DataUtil.twoBytesToInt(new byte[]{data[index++],data[index++]});
        index--;index--;index--;
        //拿到存到缓存中的时间值
        List<Long> list = new ArrayList<>();
        byte[] bytes = new byte[16];
        System.arraycopy(data,data.length-16,bytes,0,16);
        String str = new String(bytes);
        boolean exists = redisUtil.exists(RedisCode.QUERY, str);
        if(exists){
            Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().reverseRangeWithScores(RedisCode.QUERY + str, 0l, -1l);
            Iterator<ZSetOperations.TypedTuple<Object>> iterator = set.iterator();
            while(iterator.hasNext()){
                ZSetOperations.TypedTuple<Object> obj = iterator.next();
                Long lon = Long.valueOf(obj.getValue().toString());
                list.add(lon);
            }
        }
        if(datum==1||datum==2) {
            while (boo && b > 0) {
                LogQuery logQuery = new LogQuery();
                //记录类型
                //1. 历史充/放电记录 2. 历史充电过程记录
                logQuery.setRecordType(data[index++]);
                //跳过2个字节
                index++;
                index++;
                //账号类型
                logQuery.setAccountType(data[index++]);
                //卡号
                logQuery.setCarId(DataUtil.byteAsciiToString(new byte[]{data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++]}));
                //账号
                logQuery.setAmount(DataUtil.byteAsciiToString(new byte[]{data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++],
                        data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++],
                        data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++],}));
                //充电电量
                logQuery.setChargeNum(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) / 100));
                //开始时间
                Long time = (long)DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) * 1000;
                index--;index--;index--;index--;
                logQuery.setStartTime(new Date(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) * 1000));
                //结束时间
                logQuery.setEndTime(new Date(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) * 1000));
                //起始soc
                logQuery.setStartSoc(DataUtil.oneBytesToInt(data[index++]));
                //终止soc
                logQuery.setEndSoc(DataUtil.oneBytesToInt(data[index++]));
                //充电时电表读数
                logQuery.setStartAmmeterNum(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) / 100));
                //终止时电表读数
                logQuery.setEndAmmeterNum(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) / 100));
                logQuery.setId(StringUtil.get32UUID());
                if(list.size()>0&&list.contains(time)){
                    continue;
                }
                logQueryMapper.insert(logQuery);
                if(logQuery.getStartTime().getTime() > 0){
                    redisTemplate.opsForZSet().incrementScore(RedisCode.QUERY+str,logQuery.getStartTime().getTime(),1);
                }
                a++;
                if(a==b){
                    boo = false;
                }
            }
        }
        if(datum==3||datum==4){
            while (boo && b > 0){
                LogQuery logQuery = new LogQuery();
                //记录类型
                //3. 历史事件记录 4. 历史告警记录
                logQuery.setRecordType(data[index++]);
                //跳过2个字节
                index++;index++;
                //记录信息
                logQuery.setMessage(DataUtil.byteAsciiToString(new byte[]{data[index++],data[index++]}));
                //发生时间
                Long time = (long)DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) * 1000;
                index--;index--;index--;index--;
                logQuery.setStartTime(new Date(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) * 1000));
                //结束时间
                logQuery.setEndTime(new Date(DataUtil.fourBytesToInt(new byte[]{data[index++], data[index++], data[index++], data[index++]}) * 1000));
                logQuery.setId(StringUtil.get32UUID());
                if(list.size()>0&&list.contains(time)){
                    continue;
                }
                logQueryMapper.insert(logQuery);
                if(logQuery.getStartTime().getTime() > 0){
                    redisTemplate.opsForZSet().incrementScore(RedisCode.QUERY+str,logQuery.getStartTime().getTime(),1);
                }
                a++;
                if(a==b){
                    boo = false;
                }
            }
        }
        if(a==0){
            return null;
        }
        LogQuery logQuery = new LogQuery();
        logQuery.setId(StringUtil.get32UUID());
        return logQuery;
    }
}
