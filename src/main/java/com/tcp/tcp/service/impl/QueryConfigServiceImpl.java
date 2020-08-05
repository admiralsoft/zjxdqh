package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.bean.TLocalParameterResult;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.service.BaseService;
import com.tcp.mapper.TLocalParameterResultMapper;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author TcT
 * Date: 2018/8/10.
 * Time: 下午5:46.
 * @Title:
 * @Description: 0x0207 充电桩回复配置信息查询功能码
 */
@Service("queryConfigServiceImpl")
@Slf4j
public class QueryConfigServiceImpl extends BaseService<Object> implements TCPService{

    @Resource
    private TLocalParameterResultMapper localParameterResultMapper;

    @Resource
    private RabbitMqSender rabbitMqSender;


    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        /*解析*/
        TLocalParameterResult info = queryConfigParseImpl.getInfo(bytes);
        info.setId(StringUtil.get32UUID());
        info.setCreateTime(new Date());
        info.setPileNum(pileNum);
        /*入库*/
        localParameterResultMapper.insert(info);

        Integer code = (Integer) redisUtil.get(RedisCode.PILE_UPGRADE, pileNum);
        if (code != null && TLocalParameterResult.Upgrade_Result_Succ == info.getUpgradeResult()) {
            //升级成功
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "升级成功", code, true, info));
        } else if (code != null && TLocalParameterResult.Upgrade_Result_Fail == info.getUpgradeResult()) {
            //升级/失败
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "升级失败", code, false));
        } else {
            /*回复MQ*/
            rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum, "回复配置信息查询", MQCode.QUERY_CONFIG, true, info));
        }
        log.info(pileNum+":桩号,回复配置信息查询:"+MQCode.QUERY_CONFIG);
    }
}
