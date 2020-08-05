package com.tcp.tcp.active.impl;

import com.tcp.bean.JsonObject;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.tcp.vo.send.vo.SignCardResultVo;
import com.tcp.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.util.OutUtil;

/**
 * 平台回复0009
 * 
 * @author jiangzhilin
 *
 */
@Service("activeSignCardResponseServiceImp")
public class ActiveSignCardResponseServiceImp extends AbStracActiveService<JsonObject> {

	@Autowired
	private OrderJournalService orderJournalService;

	@Override
	public DictCodeEnum.SendMessageResult activeSend(JsonObject t, String pileNum) {
		// TODO Auto-generated method stub
		OutUtil.println("平台回复：0009" + pileNum);
//		redisUtil.set(RedisCode.SN, t[6], t[5]);
		SignCardResultVo signCardResponse = JsonUtils.toObject(JsonUtils.toJson(t.getObj()), SignCardResultVo.class);

		if (signCardResponse != null && signCardResponse.getResultCode() == 1) {// 卡号验证成功
			logger.debug("订单号[{}]， 卡号验证结果[{}]", signCardResponse.getSn(), signCardResponse.getResultCode() == 1);
			orderJournalService.saveOrderJounal(signCardResponse.getSn(), DictCodeEnum.OrderStat.ORDER_ZBZ);
			redisUtil.setValueExpire(RedisCode.SN, pileNum + ":" + signCardResponse.getGunNum(), signCardResponse.getSn());
//			redisUtil.setValueExpire(RedisCode.SN, "orderNo:"+signCardResponse.getSn(), signCardResponse.getMoney());
		} else {
			logger.debug("订单号[{}]， 卡号验证结果[{}], 验证结果：{}", signCardResponse.getSn(), signCardResponse != null && signCardResponse.getResultCode() == 1, JsonUtils.toJson(t.getObj()));
		}

		redisUtil.setValueExpire(RedisCode.SN, "orderNo:"+signCardResponse.getSn(), signCardResponse.getMoney());

//		SignCardResultVo vo_result=new SignCardResultVo();
//		vo_result.setCardNum(signCardResponse.getCardNum());
//		vo_result.setGunNum(signCardResponse.getGunNum());
//		vo_result.setMoney(signCardResponse.getPrepayment());
//		vo_result.setResultCode(signCardResponse.getValidResult());
//		vo_result.setSn(signCardResponse.getOrderNo());

		return sendMessage(signCardResultCommandImpl.getByteInfo(signCardResponse, pileNum, getCmd()), pileNum,
				getCmd());
//		return sendMessage(MessageToByteConvert.convertWrapReport(signCardResponse, pileNum, getCmd()), pileNum, getCmd());
	}

	@Override
	public short getCmd() {
		return TCPCode.sign_card;
	}
}
