
package com.tcp.core.code;

public class MQCode {

	public static final int HEART = 100, // MQ心跳
			CHARGING = 101, // 充电
							// 101,1(命令类型),5110000000000004(桩编号),2(枪编号),1848218035100000(账号),0000(账号余额),100(预付费),0(免费金额),201807091006(订单号)
			ORDER_CHARGE = 102, // 预约充电
			SEARCH_O = 103, // 查询预约
			RECORD_SEARCH = 104, // 查询记录
			DOWNLOAD_SEARCH = 105, // 下载查询
			CONFIG = 106, // 配置电桩参数
			PILE_HEART = 107, // 设置电桩心跳107,5110000000000004(桩编号),time1,time2
//			PILE_HEART_RESULT = 120, // 设置电桩心跳120,5110000000000004(桩编号),result
			RESTART_PILE = 108, // 重启电桩
			CONFIG_SYS = 109, // 平台远程配置桩体数据
			CONFIG_TIME_PRICE = 110, // 设置时间段以及费率
			IS_UPDATE = 111, // 更新是否成功
			UPDATE_VERSION = 112, // 版本检测更新
			NET_UPDATE = 113, // FTP开始更新
			PACKAGE_UPDATE = 114, // 数据包更新

			GUN_STATES = 115, // 桩状态 115,5110000000000004(桩编号),2(枪编号),1(管理状态),1(枪状态)
			START_RESULT = 116, //充电启动、结束成功失败 116,5110000000000004(桩编号),2(枪编号),11(启动结果),0000(已充电量)
			CHARGING_INFO = 117, // 充电中数据 117,5110000000000004(桩编号),2(枪编号),sn(订单号),soc,剩余时间,电压,电流,充电时长,充电电费
			SIGN_CARD = 118, // 帐号卡验证118,5110000000000004(桩编号),2(枪编号),100(金额),000000000(卡号),1111111(vin)
			SIGN_CARD_RESULT = 119, // 帐号卡 验证结果119,5110000000000004(桩编号),2(枪编号),000000000(卡号),0(验证结果),100(预付费余额),订单号
			UPDATE_TIME = 120, //校时命令
			END_DATA=121,//结算数据
			BLACKLIST_SEND=122,//结算数据
			 UPGRADE_REQUEST = 123,//升级请求(数据包升级)
            QUERY_CONFIG = 124,//查询配置信息
			OFFLINE_INFO=130,
			QUERY_UPDATE_RESULT=131,//查询软件/固件更新是否成功命令
			KEY_UPDATE = 133,//发送密钥更新命令
	        QUERY_DOWN_RESULT=132,//运营平台查询软件/固件下载是否成功命令
			QUERY_LOG = 134,//查询记录

			PACKDATA_DOWN = 135,// 0x0118 运营平台发送充电桩软件升级命令(数据包形式升级)

			GUN_TIME=136,//定时数据

			//VIN验证
			VIN_SIGN=137,
			VIN_SIGN_RESULT=138,

			demo = 00; // 方便添加下一个CODE
}
