
package com.tcp.tcp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.BChargingInfo;
import com.tcp.bean.JsonObject;
import com.tcp.core.code.RedisCode;
import com.tcp.util.StringUtil;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.ChargingInfoVo;
import com.tcp.tcp.vo.send.vo.SendChargingInfoVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.UUID;

/**
 * 1005充电中逻辑处理并回复0005
 */
@Service("chargingInfoServiceImpl")
public class ChargingInfoServiceImpl extends BaseService<Object> implements TCPService {

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

		ChargingInfoVo vo = chargingInfoParseImpl.getInfo(bytes);
		// OutUtil.println("充电数据：" + pileNum + "--------" + vo.getGunNum());
		OutUtil.println("桩体上报：1005" + "--------" + pileNum);

		BChargingInfo info=new BChargingInfo();
		info.setId(UUID.randomUUID().toString().replace("-",""));
		info.setAccountsType((byte)((int)vo.getAccountsType()));
		info.setCardNum(vo.getCardNum());
		info.setBalance(""+vo.getBalance());
		info.setFreeBalance(""+vo.getFreeBalance());
		info.setPrepaidBalance(""+vo.getPrepaidBalance());
		info.setGunNum((byte)((int)vo.getGunNum()));
		info.setPileNum(pileNum);
		info.setOrderNum(vo.getOrderNum());
		info.setChargingType((byte)((int)vo.getChargingType()));
		info.setDCurrent(""+vo.getdCurrent());
		info.setDcVoltage(""+vo.getdCVoltage());
		info.setChargingRate(vo.getChargingRate());
		info.setDcdy(""+vo.getDcdy());
		info.setAmmeterReading(""+vo.getAmmeterReading());
		info.setDcdyMin(vo.getDcdyMin()+"");
		info.setDcdyMinNum((byte)((int)vo.getDcdyMinNum()));
		info.setDctemperature(vo.getDcTemperature()+"");
		info.setMuzzTemperature(vo.getMuzzTemperature()+"");
		info.setSoc(vo.getSoc()+"");
		info.setFlowa(vo.getFlowA()+"");
		info.setFlowb(vo.getFlowB()+"");
		info.setFlowc(vo.getFlowC()+"");
		info.setOutputa(vo.getOutputA()+"");
		info.setOutputb(vo.getOutputB()+"");
		info.setOutputc(vo.getOutputC()+"");
		info.setPileTemperature(vo.getPileTemperature()+"");
		info.setSurplusWhenLong(vo.getSurplusWhenLong()+"");
		info.setUsePower(vo.getUseElectricity()+"");
		info.setVin(vo.getVin());
		info.setWhenLong(vo.getWhenLong());
		info.setYxcddjzgdy(vo.getYxcddjzgdy()+"");
		info.setYxcddjzgwd(vo.getYxcddjzgwd()+"");
		info.setYxcdzgdy(vo.getYxcdzgdy()+"");
		info.setCreateTime(new Date());

		System.out.println("充电中数据："+ JSONObject.toJSONString(info));

		chargingInfoMapper.insert(info);

		String sn=(String)redisUtil.get(RedisCode.SN,pileNum + ":" + vo.getGunNum());
		if (StringUtil.isEmpty(sn)) {
			logger.warn("Redis 中桩[{}]枪[{}]上缓存订单为空", pileNum, vo.getGunNum());
		}
		vo.setSn(sn);

		redisUtil.setValueExpire(RedisCode.ORDER,sn,vo.getPrepaidBalance());
		redisUtil.setValueExpire(RedisCode.ORDER_POWER,sn,vo.getUseElectricity());

		sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "充电中数据 ",MQCode.CHARGING_INFO,true, vo));

		SendChargingInfoVo sendVo = new SendChargingInfoVo();
		sendVo.setGunNum(vo.getGunNum());
		sendVo.setBalance(vo.getBalance());
		sendVo.setPrepaidBalance(vo.getPrepaidBalance());
		sendVo.setFreeBalance(0d);
		sendVo.setOrderNum(vo.getOrderNum());
		sendMessage(ctx, chargingInfoResultCommandImpl.getByteInfo(sendVo, pileNum, TCPCode.charging_info), pileNum,
				TCPCode.charging_info);
	}
}
