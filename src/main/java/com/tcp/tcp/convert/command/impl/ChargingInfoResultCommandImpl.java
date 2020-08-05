
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.vo.SendChargingInfoVo;
import com.tcp.util.DataUtil;

/**
 * 充电中数据回复 0005
 */
@Service("chargingInfoResultCommandImpl")
public class ChargingInfoResultCommandImpl extends BaseCommand implements CommandService<SendChargingInfoVo> {

	@Override
	public byte[] getByteInfo(SendChargingInfoVo t, String pileNum, short cmd) {

		/* 数据段头部 */
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		data[num++] = (byte) t.getGunNum();
		byte[] balance = DataUtil.intToFourBytes((int)(t.getBalance()*100));
		for (int i = 0; i < balance.length; i++) {
			data[num++] = balance[i];
		}
		byte[] prepaidBalance = DataUtil.intToFourBytes((int)(t.getPrepaidBalance()*100));
		for (int i = 0; i < prepaidBalance.length; i++) {
			data[num++] = prepaidBalance[i];
		}
		byte[] freeBalance = DataUtil.intToFourBytes((int)(t.getFreeBalance()*100));
		for (int i = 0; i < freeBalance.length; i++) {
			data[num++] = freeBalance[i];
		}

		byte[] orderNum = DataUtil.StringToAsciiByte(t.getOrderNum(), 32);
		for (int i = 0; i < orderNum.length; i++) {
			data[num++] = orderNum[i];
		}
		return getData(data, num);
	}
}
