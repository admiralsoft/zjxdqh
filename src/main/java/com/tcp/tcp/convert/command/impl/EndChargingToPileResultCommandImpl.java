
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.vo.EndChargingFromPileVo;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;

/**
 * 采集回复结束充电 0003
 * 
 * @author 蒋志林
 *
 */
@Service("endChargingToPileResultCommandImpl")
public class EndChargingToPileResultCommandImpl extends BaseCommand implements CommandService<EndChargingFromPileVo> {

	@Override
	public byte[] getByteInfo(EndChargingFromPileVo vo, String pileNum, short cmd) {

		/* 数据段头部 */
		int num = headLength;
		byte[] data = getSendHead(pileNum, cmd, 1);
		data[num++] = (byte) vo.getGunNum();

		byte[] balance = DataUtil.intToFourBytes((vo.getBalance().multiply(new BigDecimal(100)).intValue()));
		for (int i = 0; i < balance.length; i++) {
			data[num++] = balance[i];
		}

		byte[] perpayment = DataUtil.intToFourBytes((vo.getTotleMoney().multiply(new BigDecimal(100)).intValue()));
		for (int i = 0; i < perpayment.length; i++) {
			data[num++] = perpayment[i];
		}

		byte[] f_money = DataUtil.intToFourBytes((vo.getFree_money().multiply(new BigDecimal(100)).intValue()));
		for (int i = 0; i < f_money.length; i++) {
			data[num++] = f_money[i];
		}

        byte[] use_money = DataUtil.intToFourBytes((vo.getPer_money().subtract(vo.getTotleMoney()).multiply(new BigDecimal(100)).intValue()));
        for (int i = 0; i < use_money.length; i++) {
            data[num++] = use_money[i];
        }

		return getData(data, num);
	}

}
