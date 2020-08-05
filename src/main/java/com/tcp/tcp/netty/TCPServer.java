
package com.tcp.tcp.netty;

import com.tcp.tcp.storage.TCPMap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.tcp.util.OutUtil;

//@Component
//@Slf4j
public class TCPServer {

	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap		b;

	@Autowired
	@Qualifier("tcpSocketAddress")
	private InetSocketAddress	tcpPort;

	private ChannelFuture		serverChannelFuture;

	@PostConstruct
	public void start() throws Exception {

//		log.info("TCP Starting ....." + tcpPort);
		serverChannelFuture = b.bind(tcpPort).sync();

		if (serverChannelFuture.isSuccess()) {
//			log.info("TCP Start Success.............");
		}

//		for (; ; ) {
//			if (TCPMap.connMap.size() > 0) {
//
//				for (ChannelHandlerContext ctx :
//						TCPMap.connMap.values()) {
//					System.out.println(ctx.channel().id().toString() + "   ：   " + ctx.channel().isActive());
//					Thread.sleep(500l);
//				}
//			}
//		}
	}

	@PreDestroy
	public void stop() throws Exception {
		serverChannelFuture.channel().closeFuture().sync();
	}

	public ServerBootstrap getB() {

		return b;
	}

	public void setB(ServerBootstrap b) {

		this.b = b;
	}

	public InetSocketAddress getTcpPort() {

		return tcpPort;
	}

	public void setTcpPort(InetSocketAddress tcpPort) {

		this.tcpPort = tcpPort;
	}
}
