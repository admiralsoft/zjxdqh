package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.EndChargingFromPileVo;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;

/**
 * 1003数据解析
 * 
 * @author 蒋志林
 *
 */
@Service("endChargingFromPileParseImpl")
public class EndChargingFromPileParseImpl extends BaseParse implements TCPParseService<EndChargingFromPileVo> {

	@Override
	public EndChargingFromPileVo getInfo(byte[] data) {
		// TODO Auto-generated method stub
		int num = SUBSCRIPT;
		EndChargingFromPileVo vo = new EndChargingFromPileVo();
		vo.setGunNum(DataUtil.oneBytesToInt((data[num++])));
		vo.setE_type(DataUtil.oneBytesToInt((data[num++])));
		vo.setE_account(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));
		vo.setBalance(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setTotleMoney(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setFree_money(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setReadNum(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setTotlePower(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
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
