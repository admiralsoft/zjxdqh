
package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.SimpleByteMessageResult;
import com.tcp.tcp.convert.util.ConvertUtil;
import com.tcp.tcp.vo.receive.vo.CardStartResultVo;
import com.tcp.tcp.vo.send.vo.CardStartResultResponseVo;
import com.tcp.util.OutUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * 桩体上报 1015并回复0015 jiangzhilin
 */
@Service("pileLocalConfigResultServiceImpl")
public class PileLocalConfigResultServiceImpl extends BaseService<Object> implements TCPService {

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

		SimpleByteMessageResult simple = ByteToMessageConvert.unWrapConvert(bytes, SimpleByteMessageResult.class);
		if (simple.getResult() == SimpleByteMessageResult.RESULT_SUCCESS) {
			System.out.println("本地参数配置成功");
		}
	}
}
