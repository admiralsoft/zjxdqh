
package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.vo.send.vo.SignCardResultVo;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.util.DataUtil;
import com.tcp.util.OutUtil;

import java.math.BigDecimal;

/**
 * 帐号卡验证结果0009
 */
@Service("signCardResultCommandImpl")
public class SignCardResultCommandImpl extends BaseCommand implements CommandService<SignCardResultVo> {

	@Override
	public byte[] getByteInfo(SignCardResultVo vo, String pileNum, short cmd) {
		OutUtil.println("平台回复：0009" + "--------" + pileNum);
		/* 数据段头部 */
		// 帐号卡验证结果119,5110000000000004(桩编号),2(枪编号),000000000(卡号),0(验证结果),100(预付费余额),订单号
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		data[num++] = (byte) vo.getGunNum();
		byte[] card_num = DataUtil.stingTOBCD(vo.getCardNum());
		if (card_num.length != 8) {
			for (int i = 0; i < 8 - card_num.length; i++) {
				data[num++] = 0;
			}
			for (int i = 0; i < card_num.length; i++) {
				data[num++] = card_num[i];
			}
		} else {
			for (int i = 0; i < card_num.length; i++) {
				data[num++] = card_num[i];
			}
		}
		byte[] out_card_num = DataUtil.StringToAsciiByte("0", 16);
		for (int i = 0; i < out_card_num.length; i++) {
			data[num++] = out_card_num[i];
		}
		data[num++] = (byte) vo.getResultCode();
		for (int i = 0; i < DataUtil.intToFourBytes(0).length; i++) {
			data[num++] = (DataUtil.intToFourBytes(0))[i];
		}
		byte[] money = DataUtil.intToFourBytes(new BigDecimal(vo.getMoney()*100).intValue());
		for (int i = 0; i < money.length; i++) {
			data[num++] = money[i];
		}
		for (int i = 0; i < DataUtil.intToFourBytes(0).length; i++) {
			data[num++] = (DataUtil.intToFourBytes(0))[i];
		}
		byte[] sn = DataUtil.StringToAsciiByte(vo.getSn(), 32);
		for (int i = 0; i < sn.length; i++) {
			data[num++] = sn[i];
		}

		return getData(data, num);
	}
}
