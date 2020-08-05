
package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.GunStateVo;

/**
 * 充电枪状态解析
 */
@Service("gunStateParseImpl")
public class GunStateParseImpl extends BaseParse implements TCPParseService<GunStateVo> {

	@Override
	public GunStateVo getInfo(byte[] data) {

		int num = SUBSCRIPT;
		GunStateVo vo = new GunStateVo();
		vo.setGunNum(data[num++]);
		vo.setGunAdminState(data[num++]);
		vo.setGunChargingState(data[num++]);

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;
	}

}
