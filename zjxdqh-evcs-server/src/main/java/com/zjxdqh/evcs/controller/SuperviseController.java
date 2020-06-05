package com.zjxdqh.evcs.controller;

import com.zjxdqh.evcs.service.EvcsService;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.service.TokenService;
import com.zjxdqh.evcs.supervise.vo.*;
import com.zjxdqh.evcs.supervise.vo.BusinessTacticsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监管平台调用接口
 *
 * @author Yorking
 * @date 2019/05/08
 */
@RestController
@RequestMapping("evcs/v1")
public class SuperviseController {


    @Autowired
    private EvcsService evcsService;

    @Autowired
    private TokenService tokenService;

    /**
     * 监管平台请求 停止充电
     *
     * @param tokenRequestParam
     * @return
     */
    @PostMapping("query_token")
    public ResponseResult<TokenResultVO> queryToken(@RequestBody TokenRequestParam tokenRequestParam) {
        OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
        if (operatorInfo == null ||
                tokenRequestParam == null || StringUtils.isEmpty(tokenRequestParam.getOperatorID())) {
            return ResponseResult.success(TokenResultVO.tokenFail(tokenRequestParam.getOperatorID(), 0));
        } else if (!tokenRequestParam.getOperatorID().equalsIgnoreCase(operatorInfo.getOperatorId())) {
            return ResponseResult.success(TokenResultVO.tokenFail(tokenRequestParam.getOperatorID(), 1));
        } else if (!tokenRequestParam.getOperatorSecret().equalsIgnoreCase(operatorInfo.getOperatorSecret())) {
            return ResponseResult.success(TokenResultVO.tokenFail(tokenRequestParam.getOperatorID(), 2));
        }
        return ResponseResult.success(tokenService.createAccessToken(tokenRequestParam.getOperatorID()));
    }


    /**
     * 用于 监管平台 主动查询运营商的充电站的信息
     *
     * @param queryStationsInfoParam
     * @return
     */
    @PostMapping("query_stations_info")
    public ResponseResult<QueryStationsInfoResult> queryStationsInfo(@RequestBody QueryStationsInfoParam queryStationsInfoParam) {

        OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
        queryStationsInfoParam.setOperatorId(operatorInfo.getOperatorId());
        return ResponseResult.success(evcsService.getSuperviseSites(queryStationsInfoParam));
    }


    /**
     * 监管平台请求 停止充电
     *
     * @param stopChargeParam
     * @return
     */
    @PostMapping("query_stop_charge")
    public ResponseResult<StopChargeResult> query_stop_charge(@RequestBody StopChargeParam stopChargeParam) {
        return ResponseResult.success(evcsService.stopCharge(stopChargeParam));
    }


    /**
     * 监管平台请求 6.4.设备接口状态查询
     *
     * @param stationIdParam
     * @return
     */
    @PostMapping("query_station_status")
    public ResponseResult queryStationStatus(@RequestBody StationIdParam stationIdParam) {
        return ResponseResult.success(evcsService.queryStationStatus(stationIdParam));
    }


    /**
     * <p>
     * 查询充电状态
     * </p>
     * 使用要求：查询接口返回结果响应时间应不大于 1 秒。充电桩正在充电中状态时，市级平台可主动
     * 查询充电状态，查询频率大于 120 秒。
     *
     * @param chargeStateParam
     * @return
     */
    @PostMapping("query_equip_charge_status")
    ResponseResult<ChargeStateVo> queryEquipChargeStatus(@RequestBody ChargeStateParam chargeStateParam) {
        return ResponseResult.success(evcsService.queryEquipChargeStatus(chargeStateParam));
    }

    /**
     * <p>
     * 此查询用于定期获取每个充电站，在某个周期内的统计信息。
     * </p>
     * 使用方法：由充电运营商方实现此接口，数据需求方调用。
     *
     * @param param
     * @return
     */
    @PostMapping("query_station_stats")
    ResponseResult<StationStatsInfoResult> queryStationStats(@RequestBody StationStatsInfoParam param) {
        return ResponseResult.success(evcsService.queryStationStats(param));
    }


    /**
     * 请求开始充电
     * @param chargeQuery
     * @return
     */
    @PostMapping("query_start_charge")
    ResponseResult<StartChargeQueryResult> queryStartCharge(@RequestBody StartChargeQuery chargeQuery) {
        return ResponseResult.success(evcsService.startCharge(chargeQuery));
    }



    /**
     * <p>
     * 6.2 此接口用户客户归属运营商请求充电基础设施的认证信息。
     * </p>
     * 使用方法：由基础设施运营商服务平台实现此接口，客户归属运营商服务平台方调用。
     *
     * @param requestEquipmentParam
     * @return
     */
    @PostMapping("query_equip_auth")
    ResponseResult<RequestEquipmentVo> queryEquipAuth(@RequestBody RequestEquipmentParam requestEquipmentParam) {
        return ResponseResult.success(evcsService.queryEquipAuth(requestEquipmentParam));
    }

    /**
     * <p>
     * 6.3 查询业务策略信息结果
     *
     * @param businessPolicyParam
     * @return
     */
    @PostMapping("query_equip_business_policy")
    ResponseResult<BusinessTacticsInfoVo> queryEquipBusinessPolicy(@RequestBody BusinessPolicyParam businessPolicyParam) {
        BusinessTacticsInfoVo businessTacticsInfoVo = evcsService.queryEquipBusinessPolicy(businessPolicyParam);
        return ResponseResult.success(businessTacticsInfoVo);
    }

}
