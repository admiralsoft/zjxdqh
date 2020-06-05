package com.zjxdqh.evcs.supervise.notification;

import com.zjxdqh.evcs.supervise.annon.CectRequest;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.service.SuperviseService;
import com.zjxdqh.evcs.supervise.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 信息推送 封装类
 *
 * @author Yorking
 * @date 2019/08/08
 */
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SuperviseService superviseService;

    @Override
    public ResponseResult<TokenResultVO> getToken(TokenRequestParam tokenRequestParam) {
        return superviseService.getToken(tokenRequestParam);
    }

    @Override
    @CectRequest(dock = true)
    public ResponseResult<ChargeOrderInfoResult> notificationChargeOrderInfo(ChargeOrderInfoParam orderInfoParam) {
        return superviseService.notificationChargeOrderInfo(orderInfoParam);
    }

    @Override
    @CectRequest(dock = true, excludeSupervise = "202856659")
    public ResponseResult<ChargeStateResultVo> notificationEquipChargeStatus(ChargeStateVo evchargeStateVo) {
        return superviseService.notificationEquipChargeStatus(evchargeStateVo);
    }

    @Override
    @CectRequest(dock = true)
    public ResponseResult<PushStopChargeResult> notificationStopChargeResult(PushStopChargeParam pushStopChargeParam) {
        return superviseService.notificationStopChargeResult(pushStopChargeParam);
    }

    /**
     * 排除 重庆监管平台
     * @param statusInfoParam
     * @return
     */
    @Override
    @CectRequest(dock = true, excludeSupervise = "202856659")
    public ResponseResult<ConnectorStatusInfoResult> notificationStationStatus(ConnectorStatusInfoParam statusInfoParam) {
        return superviseService.notificationStationStatus(statusInfoParam);
    }

    @Override
    public ResponseResult<CheckOrderResult> notificationCheckChargeOrders(CheckOrderParam checkOrderParam) {
        // TODO 推送 对帐 订单结果 信息
        return superviseService.notificationCheckChargeOrders(checkOrderParam);
    }

    @Override
    @CectRequest(dock = true)
    public ResponseResult<StartChargingResult> notificationStartChargeResult(StartChargingResultParam startChargingResultParam) {
        return superviseService.notificationStartChargeResult(startChargingResultParam);
    }

    /**
     * 仅重庆监管平台使用
     * @param param
     * @return
     */
    @Override
    @CectRequest(includeSupervise = "202856659")
    public ResponseResult<SubsidyVo> notificationRecordsAmountInfo(SubsidyParam param) {
        return superviseService.notificationRecordsAmountInfo(param);
    }

    /**
     * 场站费率变更
     * 仅车主邦使用
     * @param param
     * @return
     */
    @Override
    @CectRequest(dock = true, includeSupervise = "MA005DBW1")
    public ResponseResult<SubsidyVo> notificationStationChange(StationChangeParam param) {
        return superviseService.notificationStationChange(param);
    }


}
