package com.tcp.tcp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.TPile;
import com.tcp.core.code.HeartbeatTimeCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.SigninCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.retry.RetryRecord;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.convert.MessageToByteConvert;
import com.tcp.tcp.fo.TCPConnectionFo;
import com.tcp.tcp.storage.TCPMap;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.PrescribedRateVo;
import com.tcp.tcp.vo.receive.vo.SigninVo;
import com.tcp.tcp.vo.send.vo.ActiveSendSetUpHeartbeatVo;
import com.tcp.tcp.vo.send.vo.SendSigninVo;
import com.tcp.util.CoreUtil;
import com.tcp.util.JsonUtils;
import com.tcp.util.OutUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 注册逻辑
 */
@Service("pileSigninServiceImpl")
public class PileSigninServiceImpl extends BaseService<Object> implements TCPService {

    public static final Logger regLog= LoggerFactory.getLogger("com.tcp.reg.count");

    @Autowired
    private RedisTemplate byteRedisTemplate;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        /* 数据解析 */
        SigninVo vo = signinParseImpl.getInfo(bytes);
        OutUtil.println("桩体注册：1001" + "--------" + pileNum);
        /* 获取桩信息 */
        TPile pile = pileService.getPileById(pileNum);
        /* 注册信息返回实体 */
        SendSigninVo sendVo = new SendSigninVo();
        sendVo.setResult(SigninCode.result_success);
        if (null == pile) {
            logger.debug("桩体【{}】在服务器中未查询到", pileNum);
            sendVo.setResult(SigninCode.result_fail);
            sendVo.setCode(SigninCode.server_prohibit_code);
            sendMessage(ctx, signinResultCommandImpl.getByteInfo(sendVo, pileNum, TCPCode.signin), pileNum,
                    TCPCode.signin);
            return;
        }

        /* 数据入库 */
        pile.setPileNum(pileNum);
        pile.setPileLatitude(new BigDecimal(vo.getPileLatitude()));
        pile.setPileLongitude(new BigDecimal(vo.getPileLongitude()));
        pile.setPileOperateType(vo.getPileOperateType());
        pile.setPileUsableGunNum(vo.getGunNum());
        pile.setPileSigninState(TPile.SIGN_ONLINE);

        // 最近 5分钟注册次数
        Integer regCount = redisUtil.getAndIncrement(RedisCode.REDIS_TCP_REG, pileNum, 5);
        logger.debug("桩号[{}],注册次数[{}]",pileNum,regCount);
        if (regCount == 5) {
            String stationName = pileService.getStationByPileId(pileNum);
            regLog.error("场站[{}],桩号[{}],最近5分钟注册次数超过5次,请运维同事重点关注！",stationName , pileNum);
//            pile.setPileSigninState(TPile.SIGN_FAULT);
//            pileService.updatePile4OnOff(pile);
            return;
        } else if (regCount>5){
            if (regCount>20){
                redisUtil.remove(RedisCode.REDIS_TCP_REG,pileNum);
            }
            return;
        }

        pileService.updatePile4OnOff(pile);
        /* 数据缓存 */
        TCPConnectionFo fo = new TCPConnectionFo();
        fo.setChannelId(ctx.channel().id().toString());
        fo.setConntState(1);
        fo.setPileNum(pileNum);
        fo.setServiceIp(CoreUtil.getHost());
        if (ctx.channel() != null && ctx.channel().remoteAddress() != null) {
            fo.setRemoteAddress(ctx.channel().remoteAddress().toString());
        }
        fo.setMuzzleCount(vo.getGunNum());
        redisUtil.set(RedisCode.REDIS_TCP, pileNum, fo);
        logger.debug("桩[{}]保存 [{}]至 redis ", pileNum, JsonUtils.toJson(fo));

        /* 将通道放入存储 */
        TCPMap.connMap.put(pileNum, ctx);

        /* 注册命令回复 */
        sendMessage(ctx, signinResultCommandImpl.getByteInfo(sendVo, pileNum, TCPCode.signin), pileNum, TCPCode.signin);
        /* 心调设置 */
        ActiveSendSetUpHeartbeatVo activeVo = new ActiveSendSetUpHeartbeatVo();
        activeVo.setReportTime(HeartbeatTimeCode.HEART_BEAT);
        activeVo.setOverTime(HeartbeatTimeCode.HEART_BEAT_TIME_OUT);
        sendMessage(ctx, activeSendSetUpHeartbeatCommandImpl.getByteInfo(activeVo, pileNum, TCPCode.set_up_heartbeat),
                pileNum, TCPCode.set_up_heartbeat);
        /* 设置分时电价*/
        List<PrescribedRateInfo> priceRates = pileService.getPriceRates(pileNum);

        logger.debug("桩[{}] 获取分时电价", pileNum);
        if (priceRates != null && priceRates.size() > 0) {
            PrescribedRateVo rateVo = new PrescribedRateVo();
            rateVo.setGunNum(1);
            rateVo.setTimeNum(priceRates.size());
            rateVo.setPrescribedRateInfos(priceRates);
            short cmd = TCPCode.PILE_PRESCRIBED_RATE;
            byte[] context = MessageToByteConvert.convertWrapReport(rateVo, pileNum, cmd);
            sendMessage(ctx, context, pileNum, cmd);


            // 如果 指令映射关系表 存在
            if (DictCodeEnum.SendRecievCodeMap.getRecieveCode( cmd) > 0) {
                // 缓存 指令 3分钟，如果 未收到回复， 则使用此指令重发
                String pileCodeKey = "PILECODE:" + pileNum + ":" + cmd;
                if (!byteRedisTemplate.hasKey(pileCodeKey)) {
                    byteRedisTemplate.opsForValue().set(pileCodeKey, context, 5, TimeUnit.MINUTES);

                    //延迟5秒, 再确认是否回复
                    sender.sendDelay(new RetryRecord(4, pileCodeKey, 150000));
                }
            }

        } else {
            logger.error("桩[{}]未获取到对应分时费率", pileNum);
        }

    }

}
