package com.zjxdqh.evcs.supervise.notification;

import com.zjxdqh.evcs.supervise.vo.*;

/**
 * 请求监管平台 方法定义
 *
 * @author Yorking
 * @date 2019/05/07
 */
public interface NotificationService {

    /**
     * 监管平台获取 Token信息
     *
     * @param tokenRequestParam
     * @return
     */
    ResponseResult<TokenResultVO> getToken(TokenRequestParam tokenRequestParam);

    /**
     * <p>
     * 推送充电订单信息
     * </p>
     * 此接口用于设备归属运营商向市级平台推送各类启动方式完成的充电订单信息。启动方式详见
     * 6.10.3 输入参数中的约定。
     * 使用要求：自充电桩停止充电并生成订单后，订单须在 30 秒内上报到市级平台，如上报失败须按
     * 照以下频率推送订单信息(15/15/30/180/1800/1800/1800/1800/3600，单位秒)
     *
     * @return
    *ResponseResult<ChargeOrderInfoResult> notificationChargeOrderInfo(ChargeOrderInfoParam orderInfoParam);
     */

    /**
     * <p>
     * 推送充电订单信息
     * </p>
     * 此接口用于设备归属运营商向市级平台推送各类启动方式完成的充电订单信息。启动方式详见
     * 6.11.3 输入参数中的约定。
     * 使用要求：自充电桩停止充电并生成订单后，订单须在 30 秒内上报到市级平台，如上报失败须按
     * 照以下频率推送订单信息(15/15/30/180/1800/1800/1800/1800/3600，单位秒)
     *
     * @return
     */
     ResponseResult<ChargeOrderInfoResult> notificationChargeOrderInfo(ChargeOrderInfoParam orderInfoParam);


    /**
     * <p>
     * 推送充电状态
     * </p>
     * 此接口用于设备归属运营商向客户归属运营商推送充电设备的充电状态。
     * 使用要求：充电桩开始充电后(含各类启动方式)，均须每间隔 50 秒向市级平台推送一次充电状态数据
     *
     * @return
     */
    ResponseResult<ChargeStateResultVo> notificationEquipChargeStatus(ChargeStateVo evchargeStateVo);

    /**
     * 此接口用于设备归属运营商向市级平台推送停止充电结果信息。
     * 使用要求：当充电桩实际停止充电后须立即推送结果信息到市级平台，从充电桩收到停止命令到向 市级平台推送充电停止结果时间间隔控制在 50 秒内。
     */
    ResponseResult<PushStopChargeResult> notificationStopChargeResult(PushStopChargeParam pushStopChargeParam);

    /**
     * <p>
     * 设备状态变化推送
     * </p>
     *
     * @return
     */
    ResponseResult<ConnectorStatusInfoResult> notificationStationStatus(ConnectorStatusInfoParam statusInfoParam);


    /**
     * 推送 对帐订单 结果信息
     * @param checkOrderParam
     * @return
     */
    ResponseResult<CheckOrderResult> notificationCheckChargeOrders(CheckOrderParam checkOrderParam);



    /**
     * 推送启动充电结果
     * @param startChargingResultParam
     * @return
     */
    ResponseResult<StartChargingResult> notificationStartChargeResult(StartChargingResultParam startChargingResultParam);


    /**
     * 推送消息：补贴标准接口
     * @param param
     * @return
     */
    ResponseResult<SubsidyVo> notificationRecordsAmountInfo(SubsidyParam param);


    /**
     * 车主邦 推送 场站费率 变更
     * @param stationChangeParam
     * @return
     */
    ResponseResult<SubsidyVo> notificationStationChange(StationChangeParam stationChangeParam);
}
