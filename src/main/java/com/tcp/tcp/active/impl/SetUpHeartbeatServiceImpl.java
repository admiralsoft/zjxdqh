
package com.tcp.tcp.active.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import org.springframework.stereotype.Service;

import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.send.vo.ActiveSendSetUpHeartbeatVo;
import com.tcp.util.OutUtil;

/**
 * 主动设置心跳逻辑类
 */
@Service("setUpHeartbeatServiceImpl")
public class SetUpHeartbeatServiceImpl extends AbStracActiveService<Object> {

	@Override
	public DictCodeEnum.SendMessageResult activeSend(Object t, String pileNum) {

		ActiveSendSetUpHeartbeatVo vo =JSONObject.parseObject(t.toString(),ActiveSendSetUpHeartbeatVo.class);

		return sendMessage(activeSendSetUpHeartbeatCommandImpl.getByteInfo(vo, pileNum, getCmd()),
				pileNum, getCmd());
	}

	@Override
	public short getCmd() {
		return TCPCode.set_up_heartbeat;
	}

	@Override
	protected int getMqCode() {
		return MQCode.PILE_HEART;
	}
}
