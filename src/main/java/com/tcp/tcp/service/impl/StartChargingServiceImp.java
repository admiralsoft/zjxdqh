package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.core.code.RedisCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.convert.RedisData;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.tcp.vo.receive.vo.StartChargingBackVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * 主动下发启动命令桩回复 0201
 * 
 * @author Administrator
 *
 */
@Service("startChargingServiceImpl")
@Slf4j
public class StartChargingServiceImp extends BaseService<Object> implements TCPService {

	@Autowired
	private OrderJournalService orderJournalService;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
		// TODO Auto-generated method stub
		StartChargingBackVo vo = startChargingParseImpl.getInfo(bytes);
		// OutUtil.println("启动充电：" + pileNum);

		String sn=(String)redisUtil.get(RedisCode.SN,pileNum + ":" + vo.getGunNum());
		vo.setSn(sn);

		log.info("桩体回复：0201" + "--------" + pileNum);

		JsonObject obj = getResultObj(pileNum, "启动结果", MQCode.START_RESULT, true,vo);

		String resultMsg = "";
		switch (vo.getResult_code()){
			case 11:
				resultMsg = "启动成功";
				break;
			case 12:
				resultMsg = "启动失败";
				break;
			case 21:
				resultMsg = "停止充电成功";
				break;
			case 22:
				resultMsg = "停止充电失败";
				break;
			default:
				break;
		}

		obj.setMsg(resultMsg);

		sender.sendRabbitmqCollectDirect(obj);
	}

}
