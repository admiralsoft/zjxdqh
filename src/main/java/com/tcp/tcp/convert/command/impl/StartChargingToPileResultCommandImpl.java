
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.vo.StartChargingVo;
import com.tcp.util.DataUtil;

/**
 * 采集回复启动报文 0002
 */
@Service("startChargingToPileResultCommandImpl")
public class StartChargingToPileResultCommandImpl extends BaseCommand implements CommandService<StartChargingVo> {

	@Override
	public byte[] getByteInfo(StartChargingVo vo, String pileNum, short cmd) {

		/* 数据段头部 */
		int num = headLength;
		byte[] data = getSendHead(pileNum, cmd, 1);
		data[num++] = (byte) vo.getGun_num();

		byte[] sn = DataUtil.StringToAsciiByte(vo.getSn(), 32);
		for (int i = 0; i < sn.length; i++) {
			data[num++] = sn[i];
		}

		return getData(data, num);
	}

}
