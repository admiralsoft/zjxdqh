
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;

/**
 * 用于只返回一个int参数的
 */
@Service("integerResultCommandImpl")
public class IntegerResultCommantImpl extends BaseCommand implements CommandService<Integer> {

	@Override
	public byte[] getByteInfo(Integer t, String pileNum, short cmd) {

		/* 数据段头部 */
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		data[num++] = (byte) ((int) t);
		return getData(data, num);
	}

}
