package com.tcp.tcp.service.impl;

import com.tcp.bean.LogVinCharge;
import com.tcp.core.code.MQCode;
import com.tcp.mapper.LogVinChargeMapper;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.vo.receive.vo.LogVinChargeVo;
import com.tcp.util.OutUtil;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 0x1014 车辆VIN验证充电
 * 充电桩发送来的vin码 我们验证
 *
 * @Author yaweiXu
 */
@Service("carVINServiceImpl")
@Slf4j
public class CarVINServiceImpl extends BaseService<Object> implements TCPService {
    @Resource
    LogVinChargeMapper logVinChargeMapper;

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        /*解析*/
//        LogVinCharge resultCode = carVINParseImpl.getInfo(bytes);
//        log.info("车辆VIN验证充电"+pileNum);
//        resultCode.setId(StringUtil.get32UUID());
//        logVinChargeMapper.insert(resultCode);
//        log.info("车辆VIN验证充电"+resultCode.getGunNum());

        // 将收到的bytes封装为一个对象
        LogVinChargeVo logVinChargeVo = ByteToMessageConvert.unWrapConvert(bytes, LogVinChargeVo.class);

        // 当充电参数不为空的时候执行
        if (logVinChargeVo.getCharging_type() != null) {
            // 目前只支持按金额充电,其余方式不支持
            if (logVinChargeVo.getCharging_type() == 2 && logVinChargeVo.getCharging_type().doubleValue() > 0) {
                OutUtil.println("桩体上报：1014" + pileNum + "--------" + logVinChargeVo.getGunNum());
                // 验证vin码是否存在等
                sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "VIN码验证", MQCode.VIN_SIGN,true, logVinChargeVo));
            }
        }


//        if (logVinChargeVo.getCharging_type() != null) {
//            // 目前只支持 按金额充电， 其余方式不支持。
//            if (logVinChargeVo.getCharging_type() == 2 && logVinChargeVo.getCharging_data().doubleValue()>0) {
//
//                logVinChargeVo.setChargeAmount(logVinChargeVo.getCharging_data().doubleValue());
//
//                // 充电方式参数 获取 正常。  此参数为我方在原协议基础上添加的参数。
//                sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "VIN码验证", MQCode.VIN_SIGN,true, logVinChargeVo));
//            } else {
//                // TODO 回复 认证失败
//                sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "VIN码验证失败", MQCode.VIN_SIGN_RESULT,false, logVinChargeVo));
//            }
//        }

    }
}
