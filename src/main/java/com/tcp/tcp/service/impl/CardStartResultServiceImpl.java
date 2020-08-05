
package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.vo.receive.vo.StartChargingBackVo;
import com.tcp.tcp.vo.send.vo.SendStartChargingVo;
import com.tcp.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.CardStartResultVo;
import com.tcp.tcp.vo.send.vo.CardStartResultResponseVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

import java.math.BigDecimal;

import static sun.security.krb5.Confounder.intValue;

/**
 * 桩体上报 1015并回复0015 jiangzhilin
 */
@Service("cardStartResultServiceImpl")
@Slf4j
public class CardStartResultServiceImpl extends BaseService<Object> implements TCPService {

	@Autowired
	private OrderJournalService orderJournalService;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

		CardStartResultVo vo = null;
		try {
			vo = cardStartResultParseImpl.getInfo(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解析异常:",e);
		}
//		Integer money = (Integer) redisUtil.get(RedisCode.SN, "orderNo:" + vo.getSn());
		Double orderAmount = (Double) redisUtil.get(RedisCode.SN, "orderNo:" + vo.getSn());
		if (orderAmount == null) {
			logger.error("桩体上传指令[{}]刷卡启动结果,订单[{}]获取订单缓存金额失败。", DataUtil.numToHex16(ByteToMessageConvert.unWrapConvertCmd(bytes)), vo.getSn());
		}
		Integer money = orderAmount == null ? 0 :new BigDecimal(orderAmount).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP).intValue();

		log.info("桩体上报：1015" + "--------" + pileNum+"预付余额:"+money.toString());


		JsonObject obj= null;
		CardStartResultResponseVo vo_resp = null;
		try {
			String sn= (String) redisUtil.get(RedisCode.SN,pileNum + ":" + vo.getGunNum());
			vo.setSn(sn);
			StartChargingBackVo result=new StartChargingBackVo();
			result.setGunNum(vo.getGunNum());
			result.setSn(sn);

			obj = getResultObj(pileNum,"桩体刷卡回复", MQCode.START_RESULT,false);
			if (0 == vo.getResult_code()) {// 刷卡启动成功
				obj.setSuccess(true);
				result.setResult_code(11);
			}else if (1 == vo.getResult_code()){// 刷卡启动失败
				orderJournalService.saveOrderJounal(vo.getSn(), DictCodeEnum.OrderStat.ORDER_YSX);

				obj.setSuccess(false);
				result.setResult_code(12);

				// 清除缓存订单消息
				redisUtil.remove(RedisCode.SN, pileNum+":"+vo.getGunNum());
				redisUtil.remove(RedisCode.SN, "orderNo:" + vo.getSn());
			}
			obj.setObj(result);

			vo_resp = new CardStartResultResponseVo();
			vo_resp.setGunNum(vo.getGunNum());
			vo_resp.setSn(vo.getSn());
			vo_resp.setMoney(money);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("1015异常:",e);
		}
		sendMessage(ctx, cardStartResultCommandImpl.getByteInfo(vo_resp, pileNum, TCPCode.card_start), pileNum,
				TCPCode.card_start);

		/**通知平台刷卡结果**/
		sender.sendRabbitmqCollectDirect(obj);

	}
}
