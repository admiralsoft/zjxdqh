
package com.tcp.tcp.netty;

		import io.netty.channel.ChannelInitializer;
		import io.netty.channel.ChannelPipeline;
		import io.netty.channel.nio.NioEventLoopGroup;
		import io.netty.channel.socket.SocketChannel;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.beans.factory.annotation.Qualifier;
		import org.springframework.stereotype.Component;

/**
 * TCP数据组装
 */
@Component
@Qualifier("childHandler")
public class ChildHandler extends ChannelInitializer<SocketChannel> {

	/**
	 * 数据处理
	 */
	@Autowired
	ServerListene	serverHandler;

	private NioEventLoopGroup handlerNioLoop = new NioEventLoopGroup(500);
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		ChannelPipeline p = ch.pipeline();
		// p.addLast(new XEncoder());//发送时解决粘包问题
		p.addLast(new XDecoder());// 接收时解决粘包问题
		p.addLast(handlerNioLoop,serverHandler);// 在管道中添加我们自己的接收数据实现方法
	}

}