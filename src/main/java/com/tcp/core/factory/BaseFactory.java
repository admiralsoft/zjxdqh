
package com.tcp.core.factory;

import com.tcp.bean.JsonObject;
import com.tcp.tcp.vo.send.vo.SendBlacklistVo;
import com.tcp.tcp.vo.send.vo.SendPileLocalConfigVo;
import com.tcp.tcp.vo.send.vo.SignCardResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tcp.tcp.active.ActiveService;
import com.tcp.tcp.base.TCPService;

@Component
public class BaseFactory {

	@Autowired
	@Qualifier("pileHeartbeatServiceImpl")
	protected TCPService pileHeartbeatServiceImpl;

	@Autowired
	@Qualifier("pileSigninServiceImpl")
	protected TCPService pileSigninServiceImpl;

	@Autowired
	@Qualifier("gunStateServiceImpl")
	protected TCPService gunStateServiceImpl;

	@Autowired
	@Qualifier("gunTimeInfoServiceImpl")
	protected TCPService gunTimeInfoServiceImpl;

	@Autowired
	@Qualifier("chargingInfoServiceImpl")
	protected TCPService chargingInfoServiceImpl;

	@Autowired
	@Qualifier("startChargingServiceImpl")
	protected TCPService startChargingServiceImpl;

	@Autowired
	@Qualifier("startChargingFromPileServiceImp")
	protected TCPService startChargingFromPileServiceImp;

	@Autowired
	@Qualifier("endChargingFromPileServiceImp")
	protected TCPService endChargingFromPileServiceImp;

	@Autowired
	@Qualifier("endDataFromPileServiceImp")
	protected TCPService endDataFromPileServiceImp;
	// 桩体请求验证刷卡
	@Autowired
	@Qualifier("signCardRequestServiceImpl")
	protected TCPService signCardRequestServiceImpl;
	// 桩体上报启动结果
	@Autowired
	@Qualifier("cardStartResultServiceImpl")
	protected TCPService cardStartResultServiceImpl;

	/**
	 * 离线充电数据信息
	 */
	@Autowired
	@Qualifier("offlineInfoServiceImpl")
	protected TCPService offlineInfoServiceImpl;

	/**
	 * 发送远程配置充电系统
	 */
	@Autowired
	@Qualifier("remoteChargingConfigServiceImpl")
	protected TCPService remoteChargingConfigServiceImpl;

	/**
	 * 设置费率及时间回复
	 */
	@Autowired
	@Qualifier("prescribedRateServiceImpl")
	protected TCPService prescribedRateServiceImpl;

	/**
	 * 0x0206充电桩回复校时功能码
	 */
	@Autowired
	@Qualifier("schoolTimeBackServiceImpl")
	protected TCPService schoolTimeBackServiceImpl;

	/**
	 * 0x0014 车辆VIN验证回复信息
	 */
	@Autowired
	@Qualifier("carVINBackServiceImpl")
	protected TCPService carVINBackServiceImpl;

	/**
	 * 0x1004 桩体上报故障信息
	 */
	@Autowired
	@Qualifier("errorMessageServiceImpl")
	protected TCPService errorMessageServiceImpl;

	/**
	 * 0x1012 故障码及保护码
	 */
	@Autowired
	@Qualifier("warningAndGuardServiceImpl")
	protected TCPService warningAndGuardServiceImpl;


	/**
	 * 0x0202 充电桩回复预约指令功能码
	 */
	@Autowired
	@Qualifier("reservationServiceImpl")
	protected TCPService reservationServiceImpl;

	/**
	 * 0x0209充电桩软件升级命令功能码
	 */
	@Autowired
	@Qualifier("fTPUpgradeServiceImpl")
	protected TCPService fTPUpgradeServiceImpl;

	/**
	 * 0x1014 车辆VIN验证充电
	 */
	@Autowired
	@Qualifier("carVINServiceImpl")
	protected TCPService carVINServiceImpl;

	/**
	 * 0x0208 回复软件升级命令
	 */
	@Autowired
	@Qualifier("upgradeRequestServiceImpl")
	protected TCPService upgradeRequestServiceImpl;


	/**
	 * 0x0211 充电桩回复设置⼼跳包命令
	 */
	@Autowired
	@Qualifier("setHeartbeatResultServiceImpl")
	protected TCPService setHeartbeatResultServiceImpl;
	/**
	 * 0x0217 充电桩回复软件/固件更新查询命令
	 */
	@Autowired
	@Qualifier("updateResultServiceImpl")
	protected TCPService updateResultServiceImpl;

    /**
     * 0x0212 充电桩回复预约查询命令
     */
    @Autowired
    @Qualifier("reservationResultServiceImpl")
    protected TCPService reservationResultServiceImpl;

	/**
	 *  0x0213充电桩回复重启命令
	 */
	@Autowired
	@Qualifier("restartChargeServiceImpl")
	protected TCPService restartChargeServiceImpl;

	/**
	 * 0x0207充电桩回复配置信息查询功能码
	 */
	@Autowired
	@Qualifier("queryConfigServiceImpl")
	protected TCPService queryConfigServiceImpl;

	/**
	 * 0x1013 发送时间同步指令信息
	 */
	@Autowired
	@Qualifier("timeSynchronousServiceImpl")
	protected TCPService timeSynchronousServiceImpl;

	/**
	 * 0x0210充电桩回复密钥更新功能码
	 */
	@Autowired
	@Qualifier("keyUpdateResultServiceImpl")
	protected TCPService keyUpdateResultServiceImpl;

	/**
	 * 0x0214 充电桩回复查询记录
	 */
	@Autowired
	@Qualifier("queryLogResultServiceImpl")
	protected TCPService queryLogResultServiceImpl;

	/**
	 * 0x0218 充电桩回复软件升级命令(数据包形式升级)
	 */
	@Autowired
	@Qualifier("packDataDownServiceImpl")
	protected TCPService packDataDownServiceImpl;

	/**
	 * 0x1016  BMS返回数据
	 */
	@Autowired
	@Qualifier("pileBMSMessageServiceImpl")
	protected TCPService pileBMSMessageServiceImpl;



	/*-------------------------------MQ逻辑----------------------------------*/

	@Autowired
	@Qualifier("setUpdateTimeServiceImpl")
	protected ActiveService<String[]> setUpdateTimeServiceImpl;
	@Autowired
	@Qualifier("setUpHeartbeatServiceImpl")
	protected ActiveService<Object> setUpHeartbeatServiceImpl;
	/**
	 * 启动充电
	 */
	@Autowired
	@Qualifier("activeStartChargingServiceImp")
	protected ActiveService<String> activeStartChargingServiceImp;

	/**
	 * 设置费率及时间
	 */
	@Autowired
	@Qualifier("activePrescribedRateServiceImpl")
	protected ActiveService<String> activePrescribedRateServiceImpl;


	/**
	 * 设置充电桩本地参数
	 */
	@Autowired
	@Qualifier("activePileLocalConfigServiceImpl")
	protected ActiveService<SendPileLocalConfigVo> activePileLocalConfigServiceImpl;

	/**
	 * 设置黑名单ftp下载地址
	 */
	@Autowired
	@Qualifier("activeBlacklistServiceImpl")
	protected ActiveService<SendBlacklistVo> activeBlacklistServiceImpl;

	/**
	 * 远程配置系统
	 */
	@Autowired
	@Qualifier("activeRemoteChargingConServiceImpl")
	protected ActiveService<String> activeRemoteChargingConServiceImpl;

	/**
	 * 平台回复0009
	 */
	@Autowired
	@Qualifier("activeSignCardResponseServiceImp")
	protected ActiveService<JsonObject> activeSignCardResponseServiceImp;
	/**
	 * 平台回复1014
	 */
	@Autowired
	@Qualifier("activeSignVinResponseServiceImp")
	protected ActiveService<JsonObject> activeSignVinResponseServiceImp;

	/**
	 * 0x0102 运营平台发送预约命令
	 */
	@Autowired
	@Qualifier("activeReservationServiceImpl")
	protected ActiveService<String> activeReservationServiceImpl;

	/**
	 * 0x0112 运营平台查询预约结果
	 */
	@Autowired
	@Qualifier("activeQueryReservationServicelmpl")
	protected ActiveService<String> activeQueryReservationServicelmpl;

	/**
	 * 0x0109 运营平台发送充电桩软件升级命令（FTP升级）
	 */
	@Autowired
	@Qualifier("updateFTPServiceImpl")
	protected ActiveService<String> updateFTPServiceImpl;

	/**
	 * 升级请求
	 */
	@Autowired
	@Qualifier("activeUpgradeRequestImpl")
	protected ActiveService<String> activeUpgradeRequestImpl;

	/**
	 * 0x0113 运营平台重启充电桩命令
	 */
	@Autowired
	@Qualifier("restartServiceImpl")
	protected ActiveService<String> restartServiceImpl;


	/**
	 *  0x0107 运营平台发送查询充电桩配置信息命令
	 */
	@Autowired
	@Qualifier("activeQueryConfigServiceImpl")
	protected ActiveService<String> activeQueryConfigServiceImpl;

	/**
	 * 0x0110 运营平台发送密钥更新命令
	 */
	@Autowired
	@Qualifier("keyUpdateServiceImpl")
	protected ActiveService<String> keyUpdateServiceImpl;

	/**
	 *  0x0114 运营平台查询记录命令
	 */
	@Autowired
	@Qualifier("activeQueryLogServiceImpl")
	protected ActiveService activeQueryLogServiceImpl;

	/**
	 * 0x0116 运营平台查询软件/固件下载是否成功命令
	 */
	@Autowired
	@Qualifier("activeDownResultServiceImpl")
	protected ActiveService<JsonObject>  activeDownResultServiceImpl;

	/**
	 *0x0117 运营平台查询软件/固件更新是否成功命令
	 *
	 */
	@Autowired
	@Qualifier("activeUpdateResultServiceImpl")
	protected ActiveService<JsonObject> activeUpdateResultServiceImpl;

	/**
	 * 0x0118 运营平台发送充电桩软件升级命令(数据包形式升级)
	 */
	@Autowired
	@Qualifier("activePackDataDownServiceImpl")
	protected ActiveService<String> activePackDataDownServiceImpl;


}
