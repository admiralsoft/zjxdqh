
package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.SigninVo;
import com.tcp.util.DataUtil;

/**
 * 注册字节解析类
 */
@Service("signinParseImpl")
public class SigninParseImpl extends BaseParse implements TCPParseService<SigninVo> {

	@Override
	public SigninVo getInfo(byte[] data) {

		int num = SUBSCRIPT;
		SigninVo vo = new SigninVo();
		/* 设备类型1 */
		vo.setPileType(DataUtil.oneBytesToInt(data[num++]));
		/* 额定功率4 */
		vo.setPilePower(DataUtil.fourBytesToInt(new byte[]{data[num++], data[num++], data[num++], data[num++]}));
		/* 额定电压 2 */
		vo.setPileVoltage(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]}));
		/* 经度 12 */
		vo.setPileLongitude(DataUtil.byteAsciiToString(new byte[]{data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]}));
		/* 纬度 12 */
		vo.setPileLatitude(DataUtil.byteAsciiToString(new byte[]{data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]}));
		/* 所属电站编号 13 */
		vo.setStationNum(DataUtil.byteAsciiToString(new byte[]{data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]}));
		/* 所属地区编号 13 */
		vo.setRegionNum(DataUtil.byteAsciiToString(new byte[]{data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++]}));
		/* 运营类型 2 */
		vo.setPileOperateType(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]}));
		/* 桩内编号 2 */
		vo.setNumber(DataUtil.twoBytesToInt(new byte[]{data[num++], data[num++]}));
		/* 枪口个数 1 */
		vo.setGunNum(DataUtil.oneBytesToInt(data[num++]));
		for (int i = 0; i < vo.getGunNum(); i++) {
			/* 停车位 2 */
			/* 充电枪接口类型 1 */
		}

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;
	}
}
