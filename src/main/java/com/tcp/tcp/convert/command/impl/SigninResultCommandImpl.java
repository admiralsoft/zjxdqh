
package com.tcp.tcp.convert.command.impl;

import org.springframework.stereotype.Service;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.vo.SendSigninVo;

/**
 * 注册回复
 */
@Service("signinResultCommandImpl")
public class SigninResultCommandImpl extends BaseCommand implements CommandService<SendSigninVo> {

	@Override
	public byte[] getByteInfo(SendSigninVo vo, String pileNum, short cmd) {

		/* 数据段头部 */
		byte[] data = getSendHead(pileNum, cmd, 1);
		int num = headLength;
		data[num++] = (byte) vo.getResult();// 注册结果成功
		data[num++] = (byte) vo.getCode();// 注册失败码 表示成功
		return getData(data, num);
	}

}
