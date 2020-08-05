
package com.tcp.tcp.netty;

import com.alibaba.fastjson.JSON;
import com.tcp.biz.pile.PileService;
import com.tcp.core.code.RedisCode;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.fo.PileAccessTime;
import com.tcp.tcp.fo.TCPConnectionFo;
import com.tcp.tcp.storage.TCPMap;
import com.tcp.util.CoreUtil;
import com.tcp.util.RedisUtil;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcp.bean.TPile;
import com.tcp.bean.TTCPMessage;
import com.tcp.core.code.TCPCode;
import com.tcp.tcp.base.TCPFactory;
import com.tcp.mapper.TPileMapper;
import com.tcp.mapper.TTCPMessageMapper;
import com.tcp.util.DataUtil;
import com.tcp.util.OutUtil;

/**
 * 报文入口 用于验证报文合法性
 */
@Component
public class TCPMessageEntrance {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	TCPFactory			tcpFactory;

	@Autowired
	PileService pileService;

	@Autowired
	TTCPMessageMapper	messageMapper;

	@Autowired
	RedisUtil redisUtil;

	private int getCmdType(byte[] req) {

		int cmd = (int) ((req[24] & 0xFF) << 8 | (req[25] & 0xFF));
		return cmd;
	}

	public void process(ChannelHandlerContext ctx, byte[] req) {
		try {
			// 进行处理
			if (!DataUtil.check(req)) {
				OutUtil.println("校验失败...." + DataUtil.printHexByte(req));
				return;
			}
			int cmd = ByteToMessageConvert.unWrapConvertCmd(req);
			StringBuilder pileNumSb = new StringBuilder();// 获取桩号
			for (int i = 8; i < 24; i++) {
				pileNumSb.append(DataUtil.byteAsciiToString(req[i]));
			}
			String pileNum = pileNumSb.toString();
			/* 读取桩数据 */
			TPile pile = pileService.getPileById(pileNum);
			/* 如果没有桩数据那么就禁止访问 */
			if (null == pile) {
				logger.debug("桩体【{}】 未在服务器注册！！！！！！", pileNum);
				return;
			}
			/* 必须先注册 */
			if (cmd != TCPCode.PILE_SIGNIN) {
				if (TPile.SIGN_ONLINE != pile.getPileSigninState()) {
                    logger.info("上报指令[{}]时，服务器记录桩体：【{}】还未上线", DataUtil.numToHex16(cmd), pileNum);
                    return;
                }
				/* 将通道放入存储 */
				if (TCPMap.connMap.get(pileNum) == null ||
						(!TCPMap.connMap.get(pileNum).channel().remoteAddress().toString().equalsIgnoreCase(ctx.channel().remoteAddress().toString()))) {
					TCPMap.connMap.put(pileNum, ctx);

					/* 数据缓存 */
					TCPConnectionFo fo = new TCPConnectionFo();
					fo.setChannelId(ctx.channel().id().toString());
					fo.setConntState(1);
					fo.setServiceIp(CoreUtil.getHost());
					fo.setRemoteAddress(ctx.channel().remoteAddress().toString());
//				fo.setMuzzleCount(vo.getGunNum());
					redisUtil.set(RedisCode.REDIS_TCP, pileNum, fo);
				}
            }
			/* 验证是否进行抓包 */
			if (pile.getIsMessage() == 1 || pile.getIsMessage() == 3) {
				TTCPMessage mess = new TTCPMessage();
				//todo:在入口和出口有重复操作?
				mess.setMessageCmd(DataUtil.cmdTohexString(cmd));
				mess.setMessageInfo(DataUtil.printHexByte(req));
				mess.setMessageType(1);
				mess.setPileNum(pileNum);
				mess.setCreateTime(new Date());
				messageMapper.insert(mess);
			}
			// 记录最近 通讯时间
			PileAccessTime accessTime = new PileAccessTime();
			accessTime.setPileNum(pileNum);
			accessTime.setLastAccess(System.currentTimeMillis());
			redisUtil.set(RedisCode.PILE_LAST, pileNum, accessTime);
			tcpFactory.produce(cmd, pileNum, req, ctx);// 进入工厂
		} catch (Exception e) {
			logger.error("上传指令处理异常:{}", e);
			logger.error("上传指令处理异常指令:{}", DataUtil.printHexByte(req));
			e.printStackTrace();
			return;
		}
	}

}
