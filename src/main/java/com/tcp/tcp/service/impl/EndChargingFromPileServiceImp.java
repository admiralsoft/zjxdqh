package com.tcp.tcp.service.impl;

import com.tcp.bean.BOrderChargingInfo;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.biz.order.OrderService;
import com.tcp.core.code.RedisCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.EndChargingFromPileVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

import java.math.BigDecimal;

/**
 * 1003停止命令桩回复0003
 * 
 * @author Administrator
 *
 */
@Service("endChargingFromPileServiceImp")
public class EndChargingFromPileServiceImp extends BaseService<Object> implements TCPService {


	@Autowired
	private OrderJournalService orderJournalService;

	@Autowired
	private OrderService orderService;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
		EndChargingFromPileVo vo = endChargingFromPileParseImpl.getInfo(bytes);
		OutUtil.println("桩体回复：0003" + "--------" + pileNum);

		Double per_money= (Double) redisUtil.get(RedisCode.SN, "orderNo:" + vo.getSn());
		if (per_money !=null) {
			vo.setPer_money(new BigDecimal(per_money));
		}

		redisUtil.setValueExpire(RedisCode.SN,"end_type:"+vo.getSn(),vo.getE_type());

		if (!StringUtil.isEmpty(vo.getSn())) {
			//保存订单状态流水
			orderJournalService.saveOrderJounal(vo.getSn(), DictCodeEnum.OrderStat.ORDER_JSZ);

			//保存订单结束数据
			BOrderChargingInfo chargingInfo = new BOrderChargingInfo();
			chargingInfo.setAccountBalance(vo.getBalance());
			chargingInfo.setFreeBalance(vo.getFree_money());
			chargingInfo.setPrepayBalance(vo.getTotleMoney());
			chargingInfo.setKilowattNow(vo.getReadNum());
			chargingInfo.setStarAccount(vo.getE_account());
			chargingInfo.setEndType((byte)vo.getE_type());
			chargingInfo.setOrderNo(vo.getSn());
			chargingInfo.setEndType(Byte.valueOf(String.valueOf(vo.getE_type())));
			chargingInfo.setPileNum(pileNum);
			chargingInfo.setField1(""+vo.getTotlePower());
			chargingInfo.setGunNum(Byte.valueOf(String.valueOf(vo.getGunNum())));
			orderService.saveStopChargingInfo(chargingInfo);
		}

		sendMessage(ctx, endChargingToPileResultCommandImpl.getByteInfo(vo, pileNum, TCPCode.end_charging_back),
				pileNum, TCPCode.end_charging_back);
	}

}
