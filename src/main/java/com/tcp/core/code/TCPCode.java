
package com.tcp.core.code;

public class TCPCode {

	/**
	 * 桩体上报
	 */
	public static final short PILE_SIGNIN = 0x1001, // 注册
			START_CHARGING_BACK = 0x1002, // 启动充电返回报文
			END_CHARGING = 0x1003, // 桩发送充电结束
			PILE_CHARGING_INFO = 0x1005, // 充电中数据
			PILE_TIME_INFO = 0x1006, // 接受定时数据
			PILE_UPD_STATE = 0x1007, // 电桩状态改变
			PILE_HEARTBEAT = 0x1008, // 心跳
			END_CHARGING_INFO = 0x1011, // 桩回复收到结束信息
			TIME_SYNCHRONOUS = 0x1013,//桩请求发送时间同步指令信息
			CAR_VIN = 0X1014,//车辆VIN验证充电
			START_BACK = 0x0201, // 启动第一次回复
			SIGN_CARD = 0x1009, // 桩体请求刷卡验证
			CARD_START = 0x1015, // 桩体上报1015
			WARNING_GUARD = 0x1012, //桩体上报报警信息
			PILE_OFFLINE_INFO = 0x1010, // 发送离线充电数据
			PILE_SCHOOL_TIME = 0x0106,//运营平台发送校时命令
			PILE_ERROR_MESSAGE = 0x1004,//桩体上报故障信息
			PILE_BMS_MESSAGE = 0x1016,//BMS返回数据
			UPDATE_RESULT = 0x0217, //桩体上报软件更新结果
			RESERVATION_RESULT = 0x0212, //充电桩回复预约查询
			QUERY_CONFIG_RESULT = 0x0207,//充电桩回复配置信息查询功能码
			KEY_UODATE_RESULT = 0x0210,//充电桩回复密钥更新功能码
			demo = 00; // 方便添加下一个CODE

	/**
	 * 平台回复
	 */
	public static final short signin = 0x0001, // 注册回复
			start_charging_back = 0x0002, // 启动充电采集返回
			end_charging_back = 0x0003, // 结束充电采集返回
			warring_charging_back = 0x0004, //运营平台接收告警、保护信息数据返回
			charging_info = 0x0005, // 充电中数据回复
			time_info = 0x0006, // 定时数据回复
			state = 0x0007, // 状态改变回复
			heartbeat = 0x0008, // 心跳回复
			end_data_back = 0x0011, // 结算回复
			set_up_heartbeat = 0x0111, // 心跳设置
			set_to_heartbeat = 0x0211, // 心跳设置回复
			sign_card = 0x0009, // 平台回复帐号卡验证结果
			sign_vin = 0x1014, // 平台回复VIN验证结果
			card_start = 0x0015, // 0x0015 刷卡启动结果回复信息
			pile_bms_message_back = 0x0016, // 平台回复启动结果

			warring_result_charging_back = 0x0012,	// 运营平台接收告警、保护信息数据返回

			prescribed_rate = 0x0205, // 回复充电设备费率及时间段
			offline_info = 0x0010, // 处理确认回复离线数据信息
			remote_charging_config = 0x0203, // 发送远程配置充电系统回复配置
            synchronization_code = 0x0013,// 0x0013 运营平台返回同步指令信息
			QUERY_LOG_RESULT = 0x0214,//0x0214 充电桩回复查询记录
			demo1 = 0x00;
	/**
	 * 主动下发
	 */
	public static final short START_CHARGING = 0x0101, // 平台发送启动充电指令
								PILE_LOCAL_CONFIG = 0x0104, // 设置平台配置桩体参数
			                  PILE_PRESCRIBED_RATE = 0x0105, // 设置充电设备费率及时间段
			                  PILE_REMOTE_CHARGING_CONFIG = 0x0103, // 发送远 程配置充电系统
						      PILE_RESERVATION = 0x0102,//0x0102 运营平台发送预约命令
						      BLACKLIST = 0x0115, //下达黑名单
								QUERY_RESERVATION = 0x0112,//运营平台查询预约结果
								FTP_UPDATE = 0x0109,//运营平台发送充电桩软件升级命令（FTP升级）
							UPGRADE_REQUEST = 0x0108,//升级请求
							RESTART_CHARGE = 0x0113,//重启充电桩
							QUERY_CONFIG = 0x0107,//运营平台发送查询充电桩配置信息命令
							KEY_UPDATE = 0x0110,// 运营平台发送密钥更新命令
							DOWN_RESULT = 0x0116,// 运营平台查询软件/固件下载是否成功命令
							QUERY_UPDATE_RESULT = 0x0117,//运营平台查询软件/固件更新是否成功命令
							DOWN_PACKDATA_UPDATE = 0x0118,//0x0118 运营平台发送充电桩软件升级命令(数据包形式升级)
							QUERY_LOG = 0x0114,// 运营平台查询记录命令

	demo2 = 0x00;

	/**
	 * 充电桩回复
	 */
	public static final short SCHOOL_TIME_BACK = 0x0206,//充电桩回复校时功能码
			CAR_VIN_BACK = 0x0014,//车辆VIN验证回复信息
			RESERVATION_BACK = 0x0202,//回复预约指令功能码
			PILE_LOCAL_CONFIG_BACK = 0x0204,//0x0204充电桩回复本地参数配置指令功能码
			PILE_UPGRADE_RESULT = 0x0208,//回复软件升级命令FTP升级
			FTP_UPGRADE_RESULT = 0x0209,//充电桩软件升级命令功能码
			RESTART_CHARGE_BACK = 0x0213,//充电桩回复重启命令
			HeartBeat_BACK = 0x0211,//充电桩回复心跳包设置
			PACK_DATA_DOWN = 0x0218,//充电桩回复软件升级命令(数据包形式升级)

	demo3 = 0x00;

	/**
	 * 0123456789abcdeffedcba9876543210 初始密钥f
	 */
	public static final byte[] KEY = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd,
			(byte) 0xef, (byte) 0xfe, (byte) 0xdc, (byte) 0xba, (byte) 0x98, 0x76, 0x54, 0x32, 0x10 };
}
