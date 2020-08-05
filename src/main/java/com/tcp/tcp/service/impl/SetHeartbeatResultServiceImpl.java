
package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.DataUtil;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * 桩体回复心跳设置0211
 */
@Service("setHeartbeatResultServiceImpl")
public class SetHeartbeatResultServiceImpl extends BaseService<Object> implements TCPService {

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
		OutUtil.println("桩体回复：0211" + "--------" + pileNum);
		int result = DataUtil.oneBytesToInt(bytes[32]);
		JsonObject obj=new JsonObject();
		obj.setCode(MQCode.PILE_HEART);
		obj.setPileNum(pileNum);

		obj.setMsg(1 == result ? "成功" : "失败");
		obj.setSuccess(1 == result );
		sender.sendRabbitmqCollectDirect(obj);
		// sendMessage(ctx,
		// heartbeatResultCommandImpl.getByteInfo(System.currentTimeMillis(), pileNum,
		// TCPCode.heartbeat),
		// pileNum, TCPCode.heartbeat);
	}

}
