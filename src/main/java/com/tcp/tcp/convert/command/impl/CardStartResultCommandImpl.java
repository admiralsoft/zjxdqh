
package com.tcp.tcp.convert.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.vo.CardStartResultResponseVo;
import com.tcp.util.DataUtil;
import com.tcp.util.OutUtil;

/**
 * 刷卡回复 0015
 */
@Service("cardStartResultCommandImpl")
@Slf4j
public class CardStartResultCommandImpl extends BaseCommand implements CommandService<CardStartResultResponseVo> {

	@Override
	public byte[] getByteInfo(CardStartResultResponseVo t, String pileNum, short cmd) {
		log.info("平台回复：0015" + "--------" + pileNum);
		/* 数据段头部 */
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		data[num++] = (byte) ((int) t.getGunNum());
		byte[] sn = DataUtil.StringToAsciiByte(t.getSn(), 32);
		for (int i = 0; i < sn.length; i++) {
			data[num++] = sn[i];
		}
		byte[] money = DataUtil.intToFourBytes(t.getMoney());
		for (int i = 0; i < money.length; i++) {
			data[num++] = money[i];
		}
		return getData(data, num);
	}
}
