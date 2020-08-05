
package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.CardStartResultVo;
import com.tcp.util.DataUtil;

/**
 * 充电中1005数据解析
 * 
 * @author 蒋志林
 *
 */
@Service("cardStartResultParseImpl")
public class CardStartResultParseImpl extends BaseParse implements TCPParseService<CardStartResultVo> {

	@Override
	public CardStartResultVo getInfo(byte[] data) {

		int num = SUBSCRIPT;
		CardStartResultVo vo = new CardStartResultVo();
		vo.setGunNum(DataUtil.oneBytesToInt(data[num++]));
		vo.setResult_code(DataUtil.oneBytesToInt(data[num++]));
		vo.setCardNum(DataUtil.bcdToString(new byte[] { data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++] }));
		vo.setSn(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;

	}
}
