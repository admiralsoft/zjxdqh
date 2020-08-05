package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.StartChargingBackVo;
import com.tcp.util.DataUtil;

/**
 * 桩回复0201解析
 * 
 * @author 蒋志林
 *
 */
@Service("startChargingParseImpl")
public class StartChargingParseImpl extends BaseParse implements TCPParseService<StartChargingBackVo> {

	@Override
	public StartChargingBackVo getInfo(byte[] data) {
		// TODO Auto-generated method stub
		int num = SUBSCRIPT;
		StartChargingBackVo vo = new StartChargingBackVo();
		vo.setResult_code(DataUtil.oneBytesToInt((data[num++])));
		vo.setTotlePower(
				"" + DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d);
		vo.setGunNum(DataUtil.oneBytesToInt((data[num++])));

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;
	}

}
