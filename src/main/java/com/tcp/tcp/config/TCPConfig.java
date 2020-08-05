
package com.tcp.tcp.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.tcp.tcp.netty.ChildHandler;
import com.tcp.tcp.netty.TCPMessageEntrance;
import com.tcp.util.OutUtil;

@Configuration
public class TCPConfig {

	/**
	 * BOSS处理客户端连接线程数
	 */
	@Value("${tcp.boss.thread.count}")
	private int				bossCount;

	/**
	 * Worker处理读写线程数
	 */
	@Value("${tcp.worker.thread.count}")
	private int				workerCount;

	/**
	 * tcp端口
	 */
	@Value("${tcp.port}")
	private int				tcpPort;

	/**
	 * 是否保持长连接
	 */
	@Value("${tcp.so.keepalive}")
	private boolean			keepAlive;

	/**
	 * 缓存字节数
	 */
	@Value("${tcp.so.backlog}")
	private int				backlog;

	/**
	 * 是否延迟（这里使用不延迟立即发送）
	 */
	@Value("${tcp.nodelay}")
	private boolean			nodelay;

	@Autowired
	@Qualifier("childHandler")
	private ChildHandler	childHandler;

	/**
	 * 生产辅助工具类交给Srping
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Bean(name = "serverBootstrap")
	public ServerBootstrap bootstrap() {

		/* 创建bootstrap辅助工具类，用于服务器通道的一系列配置 */
		ServerBootstrap b = new ServerBootstrap();
		/* 绑定两条线程 */
		b.group(bossGroup(), workerGroup());
		/* 指定NIO模式 */
		b.channel(NioServerSocketChannel.class);
		/* 不是默认的，可以重复利用之前分配的内存空间。 */
		b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		/* 延迟关闭时间设置 */
		b.option(ChannelOption.SO_LINGER, 0);
		b.childHandler(childHandler);
		Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
		Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
		for (@SuppressWarnings("rawtypes")
		ChannelOption option : keySet) {
			b.option(option, tcpChannelOptions.get(option));
		}
		OutUtil.println("TCP BIND OK.....");
		return b;
	}

	/**
	 * BOSS处理客户端连接
	 */
	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {

		return new NioEventLoopGroup(bossCount);
	}

	/**
	 * Worker处理读写
	 */
	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {

		return new NioEventLoopGroup(workerCount);
	}

	/**
	 * 生产TCP服务器信息交给Spring容器
	 * 
	 * @return
	 */
	@Bean(name = "tcpSocketAddress")
	public InetSocketAddress tcpPort() {

		return new InetSocketAddress(tcpPort);
	}

	@Bean(name = "tcpChannelOptions")
	public Map<ChannelOption<?>, Object> tcpChannelOptions() {

		Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
		/* 是否保持连接 */
		options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
		/* 字节缓冲数量 */
		options.put(ChannelOption.SO_BACKLOG, backlog);
		/* 是否立即发送消息 */
		options.put(ChannelOption.TCP_NODELAY, nodelay);
		return options;
	}

	/**
	 * 生产一个解码工具类
	 * 
	 * @return
	 */
	// @Bean(name = "xDecoder")
	// public XDecoder xDecoder() {
	//
	// return new XDecoder();
	// }

	/**
	 * 数据处理入口
	 * 
	 * @return
	 */
	@Bean(name = "tcpMessageEntrance")
	public TCPMessageEntrance tcpMessageEntrance() {

		return new TCPMessageEntrance();
	}

	/**
	 * Necessary to make the Value annotations work.
	 *
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {

		return new PropertySourcesPlaceholderConfigurer();
	}

}
