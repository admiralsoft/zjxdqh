package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.StartChargingVo;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;

/**
 * 桩回复 0201解析
 * 
 * @author 蒋志林
 *
 */
@Service("startChargingFromPileParseImpl")
public class StartChargingFromPileParseImpl extends BaseParse implements TCPParseService<StartChargingVo> {

	@Override
	public StartChargingVo getInfo(byte[] data) {
		// TODO Auto-generated method stub
		int num = SUBSCRIPT;
		StartChargingVo vo = new StartChargingVo();
		vo.setGun_num(DataUtil.oneBytesToInt((data[num++])));
		vo.setS_type(DataUtil.oneBytesToInt((data[num++])));
		vo.setS_account(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));
		vo.setS_balance(
				new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++]})).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setS_prepayment(
				new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setF_money(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP ));
		vo.setReadnum(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++]}) / 100.00);
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
