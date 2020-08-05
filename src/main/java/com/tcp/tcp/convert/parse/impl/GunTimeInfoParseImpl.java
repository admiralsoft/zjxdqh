
package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.BPileCircleData;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;

/**
 * 定时数据
 */
@Service("gunTimeInfoParseImpl")
public class GunTimeInfoParseImpl extends BaseParse implements TCPParseService<BPileCircleData> {

	@Override
	public BPileCircleData getInfo(byte[] data) {

		int num = SUBSCRIPT;
		BPileCircleData vo = new BPileCircleData();
		vo.setGunNum(data[num++]);
		vo.setManagerState(data[num++]);
		vo.setChargingState(data[num++]);
		vo.setNetType(data[num++]);
		vo.setNetwork(data[num++]);
		vo.setPileVoltage(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setPileCurrent(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setAmmeterReading(""+new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[num++], data[num++], data[num++], data[num++]})/100d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setPileTemperature(""+DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]}));
		vo.setMuzzTemperature(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setInputa(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setInputb(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setInputc(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setFlowa(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setFlowb(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setFlowc(""+new BigDecimal(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})/10d).setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setChargingRate(DataUtil.fourBytesToInt(new byte[]{data[num++], data[num++], data[num++], data[num++]}));
		vo.setGunUseNum(Long.valueOf(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]})));

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;
	}

}
