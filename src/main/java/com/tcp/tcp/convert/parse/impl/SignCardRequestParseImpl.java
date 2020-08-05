
package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.receive.vo.SignCardRequestVo;
import com.tcp.util.DataUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 1009数据解析
 * 
 * @author 蒋志林
 *
 */
@Service("signCardRequestParseImpl")
public class SignCardRequestParseImpl extends BaseParse implements TCPParseService<SignCardRequestVo> {

	@Override
	public SignCardRequestVo getInfo(byte[] data) {

		int num = SUBSCRIPT;
		SignCardRequestVo vo = new SignCardRequestVo();
		vo.setGunNum(DataUtil.oneBytesToInt(data[num++]));
		vo.setCardNum(DataUtil.bcdToString(new byte[] { data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++] }));
		vo.setOutCardNum(DataUtil.byteAsciiToString(new byte[] { data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));
		vo.setPwd(DataUtil.byteAsciiToString(
				new byte[] { data[num++], data[num++], data[num++], data[num++], data[num++], data[num++] }));
		vo.setVin(DataUtil.bcdToString(new byte[] { data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++], data[num++],
				data[num++], data[num++], data[num++], data[num++] }));
		int type = DataUtil.oneBytesToInt(data[num++]);
		vo.setCharging_type(type);
		/*按时间充,则不除比例*/
		if (type == 3){
			vo.setCharging_data(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num] })));
		}else {
			vo.setCharging_data(new BigDecimal(DataUtil.fourBytesToInt(new byte[] { data[num++], data[num++], data[num++], data[num] })).divide(new BigDecimal(100),2, RoundingMode.HALF_UP));
		}

		//输出 指令 日志
		CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), vo);
		return vo;

	}
}
