
package com.tcp.tcp.active;

import com.tcp.core.enums.DictCodeEnum;

/**
 * 主动下发接口
 */
public interface ActiveService<T> {

	void send(T t, String pileNum);
	DictCodeEnum.SendMessageResult activeSend(T t, String pileNum);
}
