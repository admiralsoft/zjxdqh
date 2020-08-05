package com.tcp.tcp.convert.parse.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tcp.bean.BChargeEndInfo;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.util.OutUtil;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.EndChargingVo;
import com.tcp.tcp.vo.receive.vo.EndChargingVo.Times;
import com.tcp.util.DataUtil;

/**
 * 1011数据解析
 * 
 * @author 蒋志林
 *
 */
@Service("endDataFromPileParseImpl")
public class EndDataFromPileParseImpl extends BaseParse implements TCPParseService<EndChargingVo> {

	@Override
	public EndChargingVo getInfo(byte[] data) {
		// TODO Auto-generated method stub
		int num = SUBSCRIPT;
		EndChargingVo vo = new EndChargingVo();
		vo.setNum(DataUtil.oneBytesToInt(data[num++]));
		vo.setLength(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] }));
		vo.setGunNum(DataUtil.oneBytesToInt((data[num++])));
		vo.setA_type(DataUtil.oneBytesToInt(data[num++]));
		vo.setCard_num(DataUtil.eightByteToLong(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++]}));
		vo.setAccont(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));
		vo.setVin(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++] }));
		vo.setS_type(DataUtil.oneBytesToInt(data[num++]));
		vo.setStime(DataUtil.fourByteToLong(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
		vo.setEtime(DataUtil.fourByteToLong(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
		vo.setPower(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setServer_price(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setTotleMoney(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setTime_num(DataUtil.oneBytesToInt(data[num++]));
		OutUtil.println("时间段个数："+vo.getTime_num());
		List<Times> times = new ArrayList<>();
		for (int x = 0; x < vo.getTime_num(); x++) {

			Times time = new Times();
			time.setStimes(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
			time.setEtimes(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
			time.setPowers(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			time.setServer_p_d(
					new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/10000d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			time.setEle_p_d(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/10000d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			time.setEle_p(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			time.setServer_p(
					new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			times.add(time);
		}
		vo.setTimes(times);
		vo.setSsoc(DataUtil.oneBytesToInt(data[num++]));
		vo.setEsoc(DataUtil.oneBytesToInt(data[num++]));
		vo.setSread(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
		vo.setEread(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
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
