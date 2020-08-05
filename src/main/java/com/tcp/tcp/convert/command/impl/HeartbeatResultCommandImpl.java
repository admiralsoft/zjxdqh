
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.util.DataUtil;

/*
 * 心跳包回复
 */
@Service("heartbeatResultCommandImpl")
public class HeartbeatResultCommandImpl extends BaseCommand implements CommandService<Long> {

	@Override
	public byte[] getByteInfo(Long t, String pileNum, short cmd) {

		/* 数据段头部 */
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		byte[] time = DataUtil.getTime();
		System.arraycopy(time, 0, data, num, 4);
		return getData(data, num);
	}

}
