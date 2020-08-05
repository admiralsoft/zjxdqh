
package com.tcp.tcp.netty;


import com.tcp.mapper.TPileMapper;
import com.tcp.mapper.TPileStatusInfoMapper;
import com.tcp.util.RedisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * TCP基础数据接收
 */
@Component
@Sharable
@Slf4j
public class ServerListene extends ChannelInboundHandlerAdapter {

	@Autowired
	protected RedisUtil redisUtil;

	@Autowired
	protected TPileMapper pileMapper;

	@Autowired
	protected TPileStatusInfoMapper tPileStatusInfoMapper;


	/**
	 * 数据处理入口
	 */
	@Autowired
	private TCPMessageEntrance	tcpMessageEntrance;

	//过滤阿里云内网监控IP（100.117号段）
	private String backIps = "100\\.117\\..*";

	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

		super.channelRegistered(ctx);
	}

	/**
	 * 有桩连接上来了
	 */
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String rIP = ctx.channel().remoteAddress().toString().replaceFirst("/","");
		if (!rIP.matches(backIps)) {
			log.info("有设备连接 " + ctx.channel().remoteAddress().toString());
		}
	}

	/**
	 * 有桩断开连接了
	 */
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String rIP = ctx.channel().remoteAddress().toString().replaceFirst("/","");
		if (!rIP.matches(backIps)) {
			log.info("有设备断开" + ctx.channel().remoteAddress().toString());
		}
	}

	/**
	 * 数据读取
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		try {
			/* 进行处理报文 */
			tcpMessageEntrance.process(ctx, req);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("包解析校验异常");
		} finally {
			if (null != buf) {
				buf.release();
			}
		}
	}

	/**
	 * 读取完成后刷新
	 */
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();// 刷新后才将数据发出到SocketChannel
		super.channelReadComplete(ctx);
	}

	/**
	 * 异常捕获
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		ctx.close();
	}
}
