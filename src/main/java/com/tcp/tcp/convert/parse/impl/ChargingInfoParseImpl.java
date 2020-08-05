
package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.ChargingInfoVo;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;

/**
 * 充电中1005数据解析
 * 
 * @author jiangzhilin
 *
 */
@Service("chargingInfoParseImpl")
public class ChargingInfoParseImpl extends BaseParse implements TCPParseService<ChargingInfoVo> {

	@Override
	public ChargingInfoVo getInfo(byte[] data) {

		int num = SUBSCRIPT;

		ChargingInfoVo vo = new ChargingInfoVo();
		//枪号
		vo.setGunNum(DataUtil.oneBytesToInt(data[num++]));
		//充电类型
		vo.setChargingType(DataUtil.oneBytesToInt(data[num++]));
		//输出A相电压
		vo.setOutputA(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setOutputB(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setOutputC(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//交流A相电流
		vo.setFlowA(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setFlowB(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setFlowC(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//直流电压
		vo.setdCVoltage(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//直流电流
		vo.setdCurrent(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//当前充电桩桩功率
		vo.setChargingRate(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] }));
		//使用电量
		vo.setUseElectricity(new BigDecimal(
				DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//soc
		vo.setSoc(DataUtil.oneBytesToInt(data[num++]));
		//vin
		vo.setVin(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++]}));
		//当前电表读数
		vo.setAmmeterReading(new BigDecimal(
				DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//已充时长
		vo.setWhenLong(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] }));
		//剩余充电时间
		vo.setSurplusWhenLong(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] }));
		//桩内温度
		vo.setPileTemperature(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] }));
		//枪内温度
		vo.setMuzzTemperature(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] }));
		//电池最高温度号
		vo.setDcTemperatureNum(DataUtil.oneBytesToInt(data[num++]));
		//电池最高温度
		vo.setDcTemperature(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//电池最高电压号号
		vo.setDcdyNum(DataUtil.oneBytesToInt(data[num++]));
		//电池最高电压
		vo.setDcdy(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//电池最低电压号
		vo.setDcdyMinNum(DataUtil.oneBytesToInt(data[num++]));
		//电池最低电压
		vo.setDcdyMin(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//允许充电最高电压
		vo.setYxcdzgdy(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/10d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//允许充电单节最高电压
		vo.setYxcddjzgdy(new BigDecimal(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//允许电池单节最高温度
		vo.setYxcddjzgwd(DataUtil.twoBytesToInt(new byte[] { data[num++], data[num++] }));
		//帐号类型
		vo.setAccountsType(DataUtil.oneBytesToInt(data[num++]));
		//卡号
		vo.setCardNum(DataUtil.bcdToString(new byte[] { data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++] }));
		//充电帐号
		vo.setChargingAccounts(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++] }));
		//订单号
		vo.setOrderNum(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));
		//帐号余额
		vo.setBalance(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//预付金额
		vo.setPrepaidBalance(new BigDecimal(
				DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		//免费余额
		vo.setFreeBalance(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num++] })/100d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;

	}
}
