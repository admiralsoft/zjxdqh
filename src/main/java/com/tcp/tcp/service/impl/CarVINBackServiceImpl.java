package com.tcp.tcp.service.impl;

import com.tcp.bean.LogVinResult;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.LogVinResultMapper;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * x0014 车辆VIN验证回复信息
 * @Author yaweiXu
 */
@Service("carVINBackServiceImpl")
@Slf4j
public class CarVINBackServiceImpl extends BaseService<Object> implements TCPService {
    @Resource
    LogVinResultMapper logVinResultMapper;
    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        /*解析*/
        LogVinResult resultCode = carVINBackParseImpl.getInfo(bytes);
        log.info("平台回复VIN验证结果 0014---------"+pileNum);
        //下一步存库？MQ暂时没有交互
        resultCode.setId(StringUtil.get32UUID());

        //1，验证成功2，失败，⽆此VIN记录 3，失败，桩车绑定验证失败
        if (resultCode != null && resultCode.getVerificationResult() == 1) {
            logger.debug("订单号[{}]， 卡号验证结果[{}]", resultCode.getOrderNum(), resultCode.getVerificationResult() == 1);
            // 添加到数据库
            logVinResultMapper.insert(resultCode);
            // 写入缓存
            redisUtil.setValueExpire(RedisCode.SN, pileNum + ":" + resultCode.getGunNum(), resultCode.getOrderNum());
        } else {
            logger.debug("订单号[{}]， 卡号验证结果[{}], 验证结果：{}", resultCode.getOrderNum(), resultCode != null && resultCode.getVerificationResult() == 1, "验证失败");
        }

        redisUtil.setValueExpire(RedisCode.SN, "orderNo:"+resultCode.getOrderNum(), resultCode.getRechargeAmount());
        DictCodeEnum.SendMessageResult sendMessageResult = sendMessage(bytes, pileNum, TCPCode.CAR_VIN_BACK);
        sendMessage(bytes,pileNum,TCPCode.CAR_VIN_BACK);
    }
}
