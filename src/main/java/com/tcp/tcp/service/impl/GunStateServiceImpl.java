
package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.biz.pile.PileService;
import com.tcp.core.code.RedisCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.util.StringUtil;

import com.tcp.bean.TGun;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.GunStateVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;

import io.netty.channel.ChannelHandlerContext;

/**
 * 状态改变
 */
@Service("gunStateServiceImpl")
@Slf4j
public class GunStateServiceImpl extends BaseService<Object> implements TCPService {

	@Autowired
	private OrderJournalService orderJournalService;

	@Autowired
	private PileService pileService;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

		GunStateVo vo = gunStateParseImpl.getInfo(bytes);
		log.info("状态改变:" + pileNum + "----" + vo.getGunNum());
		String orderNo = (String) redisUtil.get(RedisCode.SN, pileNum + ":" + vo.getGunNum());
		if (!StringUtil.isEmpty(orderNo) && vo.getGunChargingState() == DictCodeEnum.ChargeStat.LOADING.getCode()) {//启动中
			orderJournalService.saveOrderJounal(orderNo, DictCodeEnum.OrderStat.ORDER_QDZ);
		}
		//保存枪状态
		if (vo.getGunNum() > 0 && vo.getGunAdminState() > 0) {
			TGun gun = new TGun();
			gun.setPileNum(pileNum);
			gun.setGunNum(String.valueOf(vo.getGunNum()));
			gun.setGunAdminState(vo.getGunAdminState());
			gun.setGunChargingState(vo.getGunChargingState());

			pileService.updateGunStat(gun);
		}
		sendMessage(ctx, integerResultCommandImpl.getByteInfo(vo.getGunNum(), pileNum, TCPCode.state), pileNum,
				TCPCode.state);

		// 发送到MQ通知发送改变桩枪状态
		sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "", MQCode.GUN_STATES, true, vo));
	}
}
