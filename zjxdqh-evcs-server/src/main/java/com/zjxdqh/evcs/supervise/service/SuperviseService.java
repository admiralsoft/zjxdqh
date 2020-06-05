package com.zjxdqh.evcs.supervise.service;

import com.zjxdqh.evcs.supervise.config.SuperviseFeignConfig;
import com.zjxdqh.evcs.supervise.notification.NotificationService;
import com.zjxdqh.evcs.supervise.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 请求监管平台 方法定义
 *
 * @author Yorking
 * @date 2019/05/07
 */
@FeignClient(name = "cd-superviseService", url = "${feign.supervise.url}", configuration = SuperviseFeignConfig.class)
public interface SuperviseService extends NotificationService {

    /**
     * 监管平台获取 Token信息
     *
     * @param tokenRequestParam
     * @return
     */
    @Override
    @PostMapping("query_token")
    @ResponseBody
    ResponseResult<TokenResultVO> getToken(@RequestBody TokenRequestParam tokenRequestParam);

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
    @Override
    @PostMapping("notification_charge_order_info")
    @ResponseBody
    ResponseResult<ChargeOrderInfoResult> notificationChargeOrderInfo(@RequestBody ChargeOrderInfoParam orderInfoParam);
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
     @Override
     @PostMapping("notification_charge_order_info_for_bonus")
     @ResponseBody
     ResponseResult<ChargeOrderInfoResult> notificationChargeOrderInfo(@RequestBody ChargeOrderInfoParam orderInfoParam);


    /**
     * <p>
     * 推送充电状态
     * </p>
     * 此接口用于设备归属运营商向客户归属运营商推送充电设备的充电状态。
     * 使用要求：充电桩开始充电后(含各类启动方式)，均须每间隔 50 秒向市级平台推送一次充电状态数据
     *
     * @return
     */
    @Override
    @PostMapping("notification_equip_charge_status")
    @ResponseBody
    ResponseResult<ChargeStateResultVo> notificationEquipChargeStatus(@RequestBody ChargeStateVo evchargeStateVo);

    /**
     * 此接口用于设备归属运营商向市级平台推送停止充电结果信息。
     * 使用要求：当充电桩实际停止充电后须立即推送结果信息到市级平台，从充电桩收到停止命令到向 市级平台推送充电停止结果时间间隔控制在 50 秒内。
     */
    @Override
    @PostMapping("notification_stop_charge_result")
    @ResponseBody
    ResponseResult<PushStopChargeResult> notificationStopChargeResult(@RequestBody PushStopChargeParam pushStopChargeParam);

    /**
     * <p>
     * 设备状态变化推送
     * </p>
     *
     * @return
     */
    @Override
    @PostMapping("notification_stationStatus")
    @ResponseBody
    ResponseResult<ConnectorStatusInfoResult> notificationStationStatus(@RequestBody ConnectorStatusInfoParam statusInfoParam);


    /**
     * <p>
     * 设备状态变化推送
     * </p>
     *
     * @return
     */
    @Override
    @PostMapping("check_charge_order")
    @ResponseBody
    ResponseResult<CheckOrderResult> notificationCheckChargeOrders(@RequestBody CheckOrderParam statusInfoParam);


    /**
     * <p>
     * 推送启动充电结果
     * </p>
     *
     * @return
     */
    @Override
    @PostMapping("notification_start_charge_result")
    @ResponseBody
    ResponseResult<StartChargingResult> notificationStartChargeResult(@RequestBody StartChargingResultParam startChargingResultParam);


    /**
     * <p>
     * 推送消息：补贴标准接口
     * </p>
     *
     * @return
     */
    @Override
    @PostMapping("notification_records_amount_info")
    @ResponseBody
    ResponseResult<SubsidyVo> notificationRecordsAmountInfo(@RequestBody SubsidyParam subsidyParam);

    /**
     * <p>
     * 推送消息：车主邦 场站费率 变更接口
     * </p>
     *
     * @return
     */
    @Override
    @PostMapping("notification_station_change")
    @ResponseBody
    ResponseResult<SubsidyVo> notificationStationChange(@RequestBody StationChangeParam stationChangeParam);


}
