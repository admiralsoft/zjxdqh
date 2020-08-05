
package com.tcp.core.code;

public class RedisCode {

	/**
	 * 采集端前缀
	 */
	public static final String CJV2 = "TCP:";

	/**
	 * 桩连接信息缓存
	 */
	public static final String REDIS_TCP = CJV2 + "pile:";

	/**
	 * 桩连接 注册次数
	 */
	public static final String REDIS_TCP_REG = REDIS_TCP + "reg:";

	/**
	 * 订单信 息缓存
	 */
	public static final String SN = "sn:";
	/**
	 * netty 渠道信息缓存
	 */
	public static final String CHANNEL = "channel:";

	/**
	 * 桩体通讯时间缓存
	 */
	public static final String PILE_LAST = "pile:last:";
	/**
	 * 桩体信息缓存
	 */
	public static final String PILE_INFO = "pile:info:";
	/**
	 * 桩体验证卡信息缓存
	 */
	public static final String PILE_SIGN_CARD = "pile:card:";
	/**
	 * 桩体升级信息缓存
	 */
	public static final String PILE_UPGRADE = "pile:upgrade:";
	/**
	 * 桩体定时数据缓存
	 */
	public static final String PILE_CIRCLE = "pile:circle:";

	/**
	 * 桩回复信息
	 */
	public static final String QUERY = "query_";

	/**
	 * 桩请求ftp升级
	 */
	public static final String FTP = "FTP_";
	/**
	 * 订单数据缓存
	 */
	public static final String ORDER = "ORDER:MONEY:";
	public static final String ORDER_POWER = "ORDER:POWER:";

}
