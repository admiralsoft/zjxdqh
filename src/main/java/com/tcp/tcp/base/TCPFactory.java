
package com.tcp.tcp.base;

import io.netty.channel.ChannelHandlerContext;

public interface TCPFactory {

	/**
	 * 工厂接口方法
	 * 
	 * @return 业务处理类
	 */
	void produce(int cmd, String pileNum, byte[] req, ChannelHandlerContext ctx);
}
