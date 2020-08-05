
package com.tcp.core.service;

import com.tcp.bean.*;
import com.tcp.biz.pile.PileService;
import com.tcp.core.code.RedisCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.*;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.fo.TCPConnectionFo;
import com.tcp.tcp.storage.TCPMap;
import com.tcp.tcp.vo.receive.vo.*;
import com.tcp.tcp.vo.send.vo.*;
import com.tcp.util.CoreUtil;
import com.tcp.util.DataUtil;
import com.tcp.util.RedisUtil;
import com.tcp.util.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父级别Service
 * 
 * @author QIk
 * @param <T>
 *            redis缓存类型
 */
@Component
public class BaseService<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/*-----------------------------------下面是Redis实例----------------------------------------------*/

	@Autowired
	protected RedisUtil redisUtil;

	@Autowired
	protected RabbitMqSender sender;

	/*-----------------------------------下面是数据入库的实例---------------------------------------*/
	@Autowired
	protected PileService pileService;


	@Autowired
	protected TTCPMessageMapper messageMapper;

	@Autowired
	protected BChargeEndInfoMapper endInfoMapper;
	@Autowired
	protected BChargeEndChildinfoMapper endChildinfoMapper;

	@Autowired
	protected BChargingInfoMapper chargingInfoMapper;

	@Autowired
	protected MUpgradeBlacklistMapper blacklistMapper;

	@Autowired
	protected TPileStatusInfoMapper tPileStatusInfoMapper;

	@Autowired
	protected BPileCircleDataMapper bPileCircleDataMapper;

	@Autowired
	protected  TGunMapper tGunMapper;

	@Autowired
	protected  LogCardVerificationMapper cardSignMapper;
	/*-----------------------------------下面是解析的实例---------------------------------------*/
	@Autowired
	protected TCPParseService<SigninVo> signinParseImpl;

	@Autowired
	protected TCPParseService<GunStateVo> gunStateParseImpl;

	@Autowired
	protected TCPParseService<BPileCircleData> gunTimeInfoParseImpl;

	@Autowired
	protected TCPParseService<ChargingInfoVo> chargingInfoParseImpl;

	// 启动充电回复
	@Autowired
	protected TCPParseService<StartChargingBackVo> startChargingParseImpl;

	// 启动充电桩回复
	@Autowired
	protected TCPParseService<StartChargingVo> startChargingFromPileParseImpl;
	// 结束充电充电桩回复
	@Autowired
	protected TCPParseService<EndChargingFromPileVo> endChargingFromPileParseImpl;
	// 结束充电充电桩回复结算信息
	@Autowired
	protected TCPParseService<EndChargingVo> endDataFromPileParseImpl;
	// 桩体请求验证帐号卡
	@Autowired
	protected TCPParseService<SignCardRequestVo> signCardRequestParseImpl;
	// 桩体上报刷卡启动结果
	@Autowired
	protected TCPParseService<CardStartResultVo> cardStartResultParseImpl;

	@Autowired
	protected TCPParseService<SendPrescribedRateVo> prescribedRateParseImpl;

	@Autowired
	protected TCPParseService<List<Map<String, Object>>> offlineInfoParseImpl;

	@Autowired
	protected TCPParseService<Integer> remoteChargingConfigParseImpl;

	//充电桩回复校时功能码
	@Autowired
	protected TCPParseService<String> schoolTimeBackParseImpl;

	//车辆VIN验证充电
	@Autowired
	protected TCPParseService<LogVinCharge> carVINParseImpl;

	//车辆VIN验证回复
    @Autowired
	protected TCPParseService<LogVinResult> carVINBackParseImpl;

	//桩体上报故障
	@Autowired
	protected TCPParseService<List<LogFaultCode>> errorMessageParseImpl;

	//故障及保护码上报
	@Autowired
	protected TCPParseService<LogWarningProtectCode> warningAndGuardParseImpl;

	//充电桩回复预约指令功能码
	@Autowired
	protected TCPParseService<Map<String, Integer>> reservationResultParseImpl;

	//充电桩软件升级命令功能码
	@Autowired
	protected TCPParseService<Byte> fTPupgradeParseImpl;

	//充电桩回复软件升级命令（数据包形式升级）
	@Autowired
	protected TCPParseService<MUpgradeResult> mUpgradeResultParseImpl;

	//请求升级回复
	@Autowired
	protected TCPParseService<Integer> upgradeRequestParseImpl;

	//充电桩回复软件/固件更新查询命令
	@Autowired
	protected TCPParseService<Map<String, Integer>> updateResultParseImpl;

	//0x0212充电桩回复预约查询命令
    @Autowired
    protected TCPParseService<ReservationResultVo> preReservationResultParseImpl;

    //0x0213充电桩回复重启命令
	@Autowired
	protected TCPParseService<Byte> restartChargeParseImpl;

	//0x0207充电桩回复配置信息查询功能码
	@Autowired
	protected TCPParseService<TLocalParameterResult> queryConfigParseImpl;

	//0x0210充电桩回复密钥更新功能码
	@Autowired
	protected TCPParseService<Byte> keyUpdateResultParseImpl;

	//0x0214充电桩回复查询信息
	@Autowired
	protected TCPParseService<LogQuery> queryLogParseImpl;

	//0x0218 充电桩回复软件升级命令(数据包形式升级)
	@Autowired
	protected TCPParseService<PackDataDownVo> packDataDownParseImpl;

	/*------------------------------------下面是组装的实例---------------------------------------*/
	@Autowired
	protected CommandService<SendSigninVo> signinResultCommandImpl;

	@Autowired
	protected CommandService<Long> heartbeatResultCommandImpl;

	@Autowired
	protected CommandService<Integer> integerResultCommandImpl;

	@Autowired
	protected CommandService<SendChargingInfoVo> chargingInfoResultCommandImpl;

	@Autowired
	protected CommandService<ActiveSendSetUpHeartbeatVo> activeSendSetUpHeartbeatCommandImpl;
	// 启动充电
	@Autowired
	protected CommandService<SendStartChargingVo> startChargingReultCommantImpl;
	// 启动充电采集回复
	@Autowired
	protected CommandService<StartChargingVo> startChargingToPileResultCommandImpl;
	// 停止充电采集回复
	@Autowired
	protected CommandService<EndChargingFromPileVo> endChargingToPileResultCommandImpl;
	// 结算订单采集回复
	@Autowired
	protected CommandService<EndChargingVo> endDataToPileResultCommandImpl;
	// 刷卡验证结果采集回复
	@Autowired
	protected CommandService<SignCardResultVo> signCardResultCommandImpl;
	// 刷卡启动采集回复
	@Autowired
	protected CommandService<CardStartResultResponseVo> cardStartResultCommandImpl;

	@Autowired
	protected CommandService<PrescribedRateVo> prescribedRateResultCommandImpl;

	@Autowired
	protected CommandService<SendOfflineInfoVo> offlineInfoResultCommandImpl;

	@Autowired
	protected CommandService<RemoteChargingConfigVo> remoteChargingConfigCommandImpl;

	//校时
	@Autowired
	protected CommandService<Object> activeSendSetUpdateTimeCommandImpl;

	//0x0102 运营平台发送预约命令
	@Autowired
	protected CommandService<MPreCommand> activeReservationCommandImpl;

	//查询预约
	@Autowired
	protected CommandService<Integer> queryReservationCommandImpl;

	//升级请求
	@Autowired
	protected CommandService<Integer> upgradeRequestCommandImpl;

	//重启充电桩请求
	@Autowired
	protected CommandService<String>  activeRestartCommandImpl;

	//0x0107 运营平台发送查询充电桩配置信息命令
	@Autowired
	protected CommandService<byte[]> queryConfigCommandImpl;

	//0x0110 运营平台发送密钥更新命令
	@Autowired
	protected CommandService<String> timeUpdateCommandImpl;

	//0x0116 运营平台查询软件/固件下载是否成功命令
	@Autowired
	protected CommandService<JsonObject> activeDownResultCommandImpl;

	//0x0117 运营平台查询软件/固件更新是否成功命令
	@Autowired
	protected CommandService<JsonObject> activeUpdateResultCommandImpl;

	//0x0114 运营平台查询记录命令
	@Autowired
	protected CommandService<Byte> activeQueryLogCommandImpl;

	//主动下发分包下载命令解析
	@Autowired
	protected CommandService<MUpgradeResult> activePackDataDownCommandImpl;

	//分包下载内部解析
	@Autowired
	protected CommandService<PackDataDownVo> packDataDownCommandImpl;

	//ftp升级
	@Autowired
	protected CommandService updateFTPCommandImpl;

	@Autowired
	private TcpSendServer tcpSendServer;

	/*-----------------------------------下面是发送MQ的配置--------------------------------------*/
	/**
	 * 路由名称
	 */
	@Value("${spring.rabbitmq.receive_route_key}")
	public String TRADE_ROUTE_KEY;

	/**
	 * 交换机名称
	 */
	@Value("${spring.rabbitmq.exchange_name}")
	public String EXCHANGE_NAME;


	/*-----------------------------------下面是发送TCP---------------------------------------*/
	/**
	 * 基本用于回复这个就不存在成功失败
	 *
	 * @param ctx
	 *            通道
	 * @param data
	 *            byte数据
	 * @param pileNum
	 *            桩号
	 */
	public void sendMessage(ChannelHandlerContext ctx, byte[] data, String pileNum, int cmd) {
		tcpSendServer.sendMessage(ctx, data, pileNum, cmd);
	}

	/**
	 * 主动下发
	 *
	 * @param data
	 * @param pileNum
	 * @param cmd
	 * @return 0桩不在线，2-该桩不属于本服务器操作，1允许下发
	 */
	public DictCodeEnum.SendMessageResult sendMessage(byte[] data, String pileNum, int cmd) {
		return tcpSendServer.sendMessage(data, pileNum, cmd);
	}

	protected JsonObject getResultObj(String pileNum, String msg,Integer code,Boolean isSuccess) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.setPileNum(pileNum);
		jsonObject.setCode(code);
		jsonObject.setIp(CoreUtil.getHost());
		jsonObject.setMqId(StringUtil.get32UUID());
		jsonObject.setTimestemp(System.currentTimeMillis());
		jsonObject.setSuccess(isSuccess);
		jsonObject.setMsg(msg);
		return jsonObject;
	}

	protected JsonObject getResultObj(String pileNum, String msg,Integer code,Boolean isSuccess,Object obj) {
		JsonObject jsonObject = getResultObj(pileNum, msg, code, isSuccess);
		jsonObject.setObj(obj);
		return jsonObject;
	}

}
