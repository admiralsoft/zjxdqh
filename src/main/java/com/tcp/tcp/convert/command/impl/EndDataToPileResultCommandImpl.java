
package com.tcp.tcp.convert.command.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.vo.EndChargingVo;

/**
 * 采集回复已收到结算信息 0011
 */
@Service("endDataToPileResultCommandImpl")
public class EndDataToPileResultCommandImpl extends BaseCommand implements CommandService<EndChargingVo> {

	@Override
	public byte[] getByteInfo(EndChargingVo vo, String pileNum, short cmd) {


		System.out.println("1011的解析数据"+ JSONObject.toJSONString(vo));

		/* 数据段头部 */
		int num = headLength;
		byte[] data = getSendHead(pileNum, cmd, 1);
		data[num++] = (byte) 1;// 默认单条数据
		data[num++] = (byte) vo.getNum();// 默认单条数据
		data[num++] = (byte) 1;// 收到消息就默认处理成功
		data[num++] = (byte)vo.getGunNum();
		return getData(data, num);
	}

}
