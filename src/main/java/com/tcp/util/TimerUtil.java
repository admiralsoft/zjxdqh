
package com.tcp.util;

import java.util.Timer;

import com.tcp.tcp.storage.TCPMap;

/**
 * 心跳控制调度任务管理
 */
public class TimerUtil {

	private TimerUtil() {

	}

	private static final TimerUtil	timerUtil	= new TimerUtil();

	public static TimerUtil getTimerUtil() {

		return timerUtil;
	}

	/** 增加一个timer */
	public void add(String key, Timer timer) {

		if (null != timer) {
			TCPMap.heartbeatMap.put(key, timer);
		}
	}

	/** 获取一个timer */
	public Timer get(String key) {

		return TCPMap.heartbeatMap.get(key);
	}

	/** 移除一个timer */
	public void remove(String key) {

		TCPMap.heartbeatMap.remove(key);
	}

	/** 移除并关闭一个timer */
	public void close(String key) {

		Timer timer = get(key);
		remove(key);
		if (timer != null) {
			timer.cancel();
		}
	}
}
