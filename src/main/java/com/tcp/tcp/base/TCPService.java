
package com.tcp.tcp.base;

import io.netty.channel.ChannelHandlerContext;

/**
 * 逻辑处理接口
 */
public interface TCPService {

	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum);
}
