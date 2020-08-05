
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.vo.SendStartChargingVo;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;

/**
 * 采集回复启动报文 0002
 */
@Service("startChargingResultCommandImpl")
public class StartChargingResultCommandImpl extends BaseCommand implements CommandService<SendStartChargingVo> {

	@Override
	public byte[] getByteInfo(SendStartChargingVo vo, String pileNum, short cmd) {

		/* 数据段头部 */
		int num = headLength;
		byte[] data = getSendHead(pileNum, cmd, 1);
		//枪号
		data[num++] = (byte) vo.getGunNum();
		//启动方式
		data[num++] = (byte) vo.getCmd();
		//启动帐号
		byte[] account = DataUtil.StringToAsciiByte(vo.getS_account(), 32);
		for (int i = 0; i < account.length; i++) {
			data[num++] = account[i];
		}
		//账号余额
		byte[] balance = DataUtil.intToFourBytes((int) (new BigDecimal(vo.getS_balance()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() * 100));
		for (int i = 0; i < balance.length; i++) {
			data[num++] = balance[i];
		}
		//预付费余额
		byte[] balance_pay = DataUtil.intToFourBytes((int) (new BigDecimal(vo.getS_prepayment()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() * 100));
		for (int i = 0; i < balance_pay.length; i++) {
			data[num++] = balance_pay[i];
		}
		//免费余额
		byte[] free = DataUtil.intToFourBytes((int) (new BigDecimal(vo.getF_monney()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() * 100));
		for (int i = 0; i < free.length; i++) {
			data[num++] = free[i];
		}
		//订单号
		byte[] sn = DataUtil.StringToAsciiByte(vo.getSn(), 32);
		for (int i = 0; i < sn.length; i++) {
			data[num++] = sn[i];
		}

		return getData(data, num);
	}

}
