
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.vo.ActiveSendSetUpHeartbeatVo;
import com.tcp.util.DataUtil;

/**
 * 心跳包设置
 */
@Service("activeSendSetUpHeartbeatCommandImpl")
public class ActiveSendSetUpHeartbeatCommandImpl extends BaseCommand implements CommandService<ActiveSendSetUpHeartbeatVo> {

	@Override
	public byte[] getByteInfo(ActiveSendSetUpHeartbeatVo t, String pileNum, short cmd) {

		/* 数据段头部 */
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		byte[] reportTime = DataUtil.intToBytes(t.getReportTime());
		for (int i = 0; i < reportTime.length; i++) {
			data[num++] = reportTime[i];
		}
		byte[] overTime = DataUtil.intToBytes(t.getOverTime());
		for (int i = 0; i < overTime.length; i++) {
			data[num++] = overTime[i];
		}
		return getData(data, num);
	}
}
