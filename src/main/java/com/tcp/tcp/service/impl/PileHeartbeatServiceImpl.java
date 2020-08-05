
package com.tcp.tcp.service.impl;

import java.util.Timer;
import java.util.TimerTask;

import com.tcp.bean.TGun;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tcp.bean.TPile;
import com.tcp.core.code.HeartbeatTimeCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.storage.TCPMap;
import com.tcp.util.OutUtil;
import com.tcp.util.TimerUtil;

import io.netty.channel.ChannelHandlerContext;

/**
 * 心跳包处理
 */
@Service("pileHeartbeatServiceImpl")
@Slf4j
public class PileHeartbeatServiceImpl extends BaseService<Object> implements TCPService {

	/**
	 * 定时器
	 */
	public Timer time;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
		log.info("桩体上报：1008" + "--------" + pileNum);
		// 这里可以不解析心跳包
		sendMessage(ctx, heartbeatResultCommandImpl.getByteInfo(System.currentTimeMillis(), pileNum, TCPCode.heartbeat),
				pileNum, TCPCode.heartbeat);
		log.info("心跳：" + pileNum);
//		TimerUtil.getTimerUtil().close(pileNum); // 移除之前心跳定时任务
//		time(ctx, pileNum);
	}

	/**
	 * 心调超时逻辑
	 * 
	 * @param ctx
	 */
	public void time(final ChannelHandlerContext ctx, final String pileNum) {
		time = new Timer(pileNum);
		time.schedule(new TimerTask() {

			private int num = 0;

			@Override
			public void run() {

				num++;
				OutUtil.println(pileNum + " - " + num);
				if (num >= 1) {
					OutUtil.println("心调超时");
					//* 关闭通道 *//*
					ctx.close();
					//* 删除通道 *//*
					TCPMap.connMap.remove(pileNum);
					//* 删除该桩的心跳验证 *//*
					TimerUtil.getTimerUtil().close(pileNum);
					//* 删除TCP连接缓存 *//*
					redisUtil.remove(RedisCode.REDIS_TCP, pileNum);
					//* 修改数据库状态为离线状态 *//*
					TPile pile = new TPile();
					pile.setPileNum(pileNum);
					pile.setPileSigninState(2);
					pileService.updatePile(pile);
					TGun tGun = new TGun();
					tGun.setPileNum(pileNum);
					tGun.setGunAdminState(3);
					pileService.updateGunAdminState(tGun);
				}
			}
			// 延迟?毫秒后执行 相隔?毫秒执行一次
		}, HeartbeatTimeCode.HEART_BEAT_TIME_OUT * 1000L);
		TimerUtil.getTimerUtil().add(pileNum, time);
	}
}
