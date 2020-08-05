package com.tcp.tcp.active.impl;

import com.tcp.bean.JsonObject;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.tcp.vo.send.vo.SignCardResultVo;
import com.tcp.util.JsonUtils;
import com.tcp.util.OutUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 平台回复1014
 * 
 * @author jiangzhilin
 *
 */
@Service("activeSignVinResponseServiceImp")
public class ActiveSignVinResponseServiceImp extends AbStracActiveService<JsonObject> {

	@Autowired
	private OrderJournalService orderJournalService;

	@Override
	public DictCodeEnum.SendMessageResult activeSend(JsonObject t, String pileNum) {
		OutUtil.println("平台回复: 1014" + pileNum);
		SignCardResultVo signCardResponse = JsonUtils.toObject(JsonUtils.toJson(t.getObj()), SignCardResultVo.class);

		if (signCardResponse != null && signCardResponse.getResultCode() == 1) {
			// VIN码 验证成功
			logger.debug("订单号[{}], VIN验证结果[{}]", signCardResponse.getSn(), signCardResponse.getResultCode() == 1);
			orderJournalService.saveOrderJounal(signCardResponse.getSn(), DictCodeEnum.OrderStat.ORDER_ZBZ);
		} else {
			logger.debug("订单号[{}], VIN验证结果[{}], 验证结果: {}", signCardResponse.getSn(), signCardResponse != null && signCardResponse.getResultCode() == 1, JsonUtils.toJson(t.getObj()));
		}

		return sendMessage(signCardResultCommandImpl.getByteInfo(signCardResponse, pileNum, getCmd()), pileNum,
				getCmd());
	}

	@Override
	public short getCmd() {
		return TCPCode.sign_vin;
	}
}
