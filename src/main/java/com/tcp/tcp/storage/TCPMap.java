
package com.tcp.tcp.storage;

import io.netty.channel.ChannelHandlerContext;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储器
 */
public final class TCPMap {

	/**
	 * TPC通道存储器
	 */
	public final static ConcurrentMap<String, ChannelHandlerContext>
            connMap			= new ConcurrentHashMap<>(0);

	/**
	 * 心跳存储器
	 */
	public final static ConcurrentMap<String, Timer> heartbeatMap	= new ConcurrentHashMap<>(0);

}
