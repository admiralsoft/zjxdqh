package com.zjxdqh.evcs.service;

import com.github.pagehelper.PageInfo;
import com.zjxdqh.evcs.enums.*;
import com.zjxdqh.evcs.mq.RabbitMqSender;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.notification.NotificationService;
import com.zjxdqh.evcs.supervise.vo.ChargeOrderDetailVo;
import com.zjxdqh.evcs.supervise.vo.ChargeStateVo;
import com.zjxdqh.evcs.supervise.vo.*;
import com.zjxdqh.evcs.tools.DateUtil;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.enums.OrderEnum;
import com.zjxdqh.face.exception.BuzzException;
import com.zjxdqh.face.exception.ExceptionEnum;
import com.zjxdqh.face.param.DeviceStatusParam;
import com.zjxdqh.face.param.QueryPileGunStateParam;
import com.zjxdqh.face.param.StartChargeParam;
import com.zjxdqh.face.service.CollectService;
import com.zjxdqh.face.service.HappyOrderService;
import com.zjxdqh.face.service.HappyService;
import com.zjxdqh.face.service.HappyUserService;
import com.zjxdqh.face.vo.*;
import com.zjxdqh.face.vo.user.FUser;
import com.zjxdqh.tools.MathUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static com.zjxdqh.evcs.supervise.SuperviseUtils.getConnectorID;

/**
 * MQ信息业务处理类
 *
 * @author Yorking
 * @date 2019/05/09
 */
@Service
@Log4j2
public class EvcsService {

    /**
     * 监管平台id
     */
    @Value("${supervise.supersiveId}")
    public String supersiveId;

    @Value("${chongqin.applySubsidyPrice}")
    public Double applySubsidyPrice;

    @Value("${chongqin.time}")
    public Integer time;

    @Resource
    private HappyService happyService;

    @Resource
    HappyUserService happyUserService;

    @Resource
    private HappyOrderService orderService;

    @Resource(name = "notificationService")
    private NotificationService notificationService;

    @Autowired
    private RabbitMqSender rabbitMqSender;

    @Autowired
    private CollectService collectService;


    /**
     * 推送订单信息
     *
     * @param orderNo 订单id
     */
    //申领奖补修改
    public void pushOrderInfo(String orderNo) {

        com.zjxdqh.face.param.ChargeStateParam chargeStatParam = new com.zjxdqh.face.param.ChargeStateParam();
        chargeStatParam.setStartChargeSeq(orderNo);
        ChargeStateBaseVo baseOrder = happyService.queryChargeStateBaseVo(chargeStatParam);

        if (baseOrder != null &&
                (baseOrder.getStartChargeSeqStat()==OrderEnum.Ostat.FINISH.key()
                ||baseOrder.getStartChargeSeqStat()==OrderEnum.Ostat.PAYING.key()) ) {
            ChargeOrderInfoParam orderInfoParam = new ChargeOrderInfoParam();

            orderInfoParam.setConnectorID(SuperviseUtils.getConnectorID(baseOrder.getPid(), baseOrder.getGunnumber()));
            orderInfoParam.setStartChargeSeq(baseOrder.getStartChargeSeq());
            orderInfoParam.setChargeModel(3);//充电启动方式，必填，默认无卡启动
            orderInfoParam.setStartTime(DateUtil.getYYYYMMDDHHMMSS(baseOrder.getStartTime()));
            orderInfoParam.setEndTime(DateUtil.getYYYYMMDDHHMMSS(baseOrder.getEndTime()));


            orderInfoParam.setTotalPower(baseOrder.getUseEle());

            ChargeSuccessStateVo chargeSuccessStateVo = happyService.queryChargeSuccessStateVo(chargeStatParam);
            if (chargeSuccessStateVo != null) {
                orderInfoParam.setTotalMoney(chargeSuccessStateVo.getTotalMoney());
                orderInfoParam.setTotalElecMoney(chargeSuccessStateVo.getElecMoney());
                orderInfoParam.setTotalSeviceMoney(chargeSuccessStateVo.getSeviceMoney());
            }
            orderInfoParam.setStopReason(baseOrder.getEndReason() == 5 ? 2 : 0);

            // 分时结算电价

            orderInfoParam.setChargeDetails(Collections.EMPTY_LIST);
            orderInfoParam.setSumPeriod(orderInfoParam.getChargeDetails().size());
            List<FOrderSettlementDetail> fOrderSettlementDetail = happyService.getFOrderSettlementDetailBySn(orderNo);
            if (CollectionUtils.isEmpty(fOrderSettlementDetail)) {
                orderInfoParam.setChargeDetails(Collections.EMPTY_LIST);
                orderInfoParam.setSumPeriod(orderInfoParam.getChargeDetails().size());
            } else {
                // 设置 结算的分时电价明细
                List<ChargeOrderDetailVo> details = convertChargeOrderDetailVo(fOrderSettlementDetail, baseOrder, chargeSuccessStateVo);
                orderInfoParam.setChargeDetails(details);
                orderInfoParam.setSumPeriod(details.size());
            }

            //查询客户姓名
            FUser fUser = happyUserService.getUserById(baseOrder.getUserId());
            if (baseOrder == null){
                log.info("未查询客户数据，订单推送失败");
                return;
            }

            //查询桩信息
            FPile fPile = happyService.getPileByNum(baseOrder.getPid());
            if(fPile == null){
                log.info("未查询桩数据，订单推送失败");
                return;
            }

            //组装用户、桩、站、额定电压等信息
            orderInfoParam.setUserName(fUser.getUname());
            orderInfoParam.setEquipmentID(baseOrder.getPid());
            orderInfoParam.setStationID(fPile.getSid()+"");
            orderInfoParam.setConnectorPower(Double.parseDouble(fPile.getPower()));

           /* //查询充电结束信息
            BChargeEndInfo bChargeEndInfo = happyService.getBChargeEndInfo(baseOrder.getSn());

            //未查询到桩充电结束返回数据，订单推送失败
            if (bChargeEndInfo == null){
                log.info("未查询到桩充电结束返回数据，订单推送失败");
                return;
            }
            //电表起止值，采集已有数据,若是已有数据为空，默认为起值为0
            if (bChargeEndInfo.getInitKilowatt() == null){
                bChargeEndInfo.setInitKilowatt(0);
            }

            orderInfoParam.setMeterValueStart((double) bChargeEndInfo.getInitKilowatt());
            //由于桩通信传过来的电表止值有问题，手动以10%电损计算止值
            double d = baseOrder.getUseEle() * 1.1 + bChargeEndInfo.getInitKilowatt();
            BigDecimal b = new BigDecimal(d);
            d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            orderInfoParam.setMeterValueEnd(d);*/

            //计算充电时间
            if (baseOrder.getEndTime() == null || baseOrder.getStartTime() == null){
                orderInfoParam.setChargeLast(0);
            }else{
                Long time = baseOrder.getEndTime().getTime() - baseOrder.getStartTime().getTime();
                if(time < 0){
                    orderInfoParam.setChargeLast(0);
                }else{
                    orderInfoParam.setChargeLast((int)(time/1000));
                }
            }

            ResponseResult<ChargeOrderInfoResult> orderInfoResult = notificationService.notificationChargeOrderInfo(orderInfoParam);

        }
    }

    /**
     * 定时推送设备状态
     */
    public void timerPushPileState(){
        List<EvcsPushPile> evcsPushPiles = happyService.getEvcsPushPile();
        for (EvcsPushPile evcsPushPile: evcsPushPiles) {
            pushPileState(evcsPushPile.getPilenum(),evcsPushPile.getGunnumber());
        }
    }

    /**
     * 推送设备状态改变
     *
     * @param pileNO
     * @param gunNo
     */
    public void pushPileState(String pileNO, String gunNo) {

        DeviceStatusParam param = new DeviceStatusParam();
        param.setGnum(gunNo);
        param.setPilenum(pileNO);

        PileStatusInfoVo infoVo = happyService.getPilesByPilegnum(param);

        if(infoVo==null)
        {
            log.error("推送设备状态改变查询为空，桩号[{}]，枪号[{}]", pileNO,gunNo);
            return;
        }

        //设备状态变化推送
        ConnectorStatusInfoVo statusInfoVo = new ConnectorStatusInfoVo();
        statusInfoVo.setConnectorID(SuperviseUtils.getConnectorID(infoVo.getPilenum(), infoVo.getGnum()));
        statusInfoVo.setStatus(PileGunStatusEnum.getPileGunStatusValue(infoVo.getGstate()));
        statusInfoVo.setLockStatus(LockStatusEnum.KV_UNKNOWN.getKey());
        statusInfoVo.setParkStatus(ParkStatusEnum.KV_UNKNOWN.getKey());
        ConnectorStatusInfoParam reqParam = new ConnectorStatusInfoParam();
        reqParam.setConnectorStatusInfo(statusInfoVo);
        notificationService.notificationStationStatus(reqParam);

    }

    /**
     * 推送订单状态改变
     * 条件： 50秒推送一次订单数据  (未写)
     *
     * @param orderNo
     */
    public void pushOrderStat(String orderNo) {
        com.zjxdqh.face.param.ChargeStateParam chargeStateParam = new com.zjxdqh.face.param.ChargeStateParam();
        chargeStateParam.setStartChargeSeq(orderNo);
        // 充电状态接口：数据库一定能返回的数据
        ChargeStateBaseVo chargeStateBaseVo = happyService.queryChargeStateBaseVo(chargeStateParam);
        // 充电状态接口：充电成功后，返回部分数据
        ChargeSuccessStateVo chargeSuccessStateVo = happyService.queryChargeSuccessStateVo(chargeStateParam);
        if (chargeStateBaseVo != null) {
            ChargeStateVo evchargeStateVo = new ChargeStateVo();
            BeanUtils.copyProperties(chargeStateBaseVo, evchargeStateVo);
            if (chargeSuccessStateVo != null) {
                BeanUtils.copyProperties(chargeSuccessStateVo, evchargeStateVo);
            }

            evchargeStateVo.setStartChargeSeq(orderNo);
            evchargeStateVo.setConnectorID(SuperviseUtils.getConnectorID(chargeStateBaseVo.getPid(), chargeStateBaseVo.getGunnumber()));
            evchargeStateVo.setConnectorStatus(Integer.parseInt(PileGunStatusEnum.KV_FREE.getValue()));
            evchargeStateVo.setChargeModel(3);


//            //  TODO 添加分时结算电价
//            List<FOrderSettlementDetail> settleDetails = happyService.getFOrderSettlementDetailBySn(chargeStateBaseVo.getStartChargeSeq());
//            List<ChargeOrderDetailVo> details = convertChargeOrderDetailVo(settleDetails, chargeStateBaseVo, chargeSuccessStateVo);
//            chargeStateBaseVo.setChargeDetails(details);
//            chargeStateBaseVo.setSumPeriod(details.size());

            evchargeStateVo.setChargeDetails(Collections.EMPTY_LIST);
            evchargeStateVo.setSumPeriod(evchargeStateVo.getChargeDetails().size());

            evchargeStateVo.setStartChargeSeqStat(StopChargeEnum.getChargingStatusValue(chargeStateBaseVo.getStartChargeSeqStat()));
            ChargeDataTemp chargeDataTemp = happyService.getVoltageCurrent(orderNo);
            log.debug("查询充电中 基本数据结果:[{}]", JsonUtils.toJsonString(chargeDataTemp));
            if (!ObjectUtils.isEmpty(chargeDataTemp)) {
                evchargeStateVo.setTotalPower(chargeDataTemp.getUseele() == null ? 0 : chargeDataTemp.getUseele().doubleValue());
                evchargeStateVo.setVoltageA(chargeDataTemp.getVoltage().doubleValue());
                evchargeStateVo.setSoc(Double.parseDouble(chargeDataTemp.getSoc()));
                evchargeStateVo.setCurrentA(chargeDataTemp.getCurrent().doubleValue());

                if (evchargeStateVo.getStartTime() == null) {
                    evchargeStateVo.setStartTime(chargeStateBaseVo.getCreateTime());
                }
                evchargeStateVo.setEndTime(DateUtil.getNowDate());
            }
            // 推送充电状态数据
            notificationService.notificationEquipChargeStatus(evchargeStateVo);
        }
    }

    /**
     * 推送停止充电结果
     *
     * @param orderNo
     */
    public void pushOrderStop(String orderNo) {
        PushStopChargeParam push = new PushStopChargeParam();
        StopChargeResultVo res = happyService.getStopChargeResult(orderNo);
        if (res == null) {
            return;
        }
        String connectorid = getConnectorID(res.getPileNum(), res.getGunNum());
        push.setConnectorID(connectorid);
        push.setStartChargeSeq(orderNo);
        push.setStartChargeSeqStat(StopChargeEnum.getChargingStatusValue(res.getStartChargeSeqStat()));
        push.setSuccStat(SuccStatEnum.getPileGunStatusValue(res.getSuccStat()));
        push.setFailReason(FailReasonEnum.getPileGunStatusValue(res.getFailReason()));
        //推送停止充电结果
        notificationService.notificationStopChargeResult(push);
    }


    /**
     * <p>查询充电状态</p>
     * <p>
     * 此接口用于市级平台请求设备归属运营商旗下充电设备的充电状态信息。
     * </p>
     * 使用要求：查询接口返回结果响应时间应不大于 1 秒。充电桩正在充电中状态时，市级平台可主动
     * 查询充电状态，查询频率大于 120 秒。
     *
     * @param chargeStateParam
     * @return
     */
    public ChargeStateVo queryEquipChargeStatus(@RequestBody ChargeStateParam chargeStateParam) {
        // 同步对象
        com.zjxdqh.face.param.ChargeStateParam happychargeChargeStateParam = new com.zjxdqh.face.param.ChargeStateParam();
        happychargeChargeStateParam.setStartChargeSeq(SuperviseUtils.convert2OrderNo(chargeStateParam.getStartChargeSeq()));

        OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
        if (operatorInfo == null) {
            log.error("获取 请求运营商为NULL ");
            return null;
        }

        // 如果 是第三方充电对接平台， 使用第三方订单号
        if (operatorInfo.getRegulatory() == OperatorInfo.IS_THIRD_STAGE) {
            happychargeChargeStateParam.setStartChargeSeq(null);
            happychargeChargeStateParam.setSuperviseId(operatorInfo.getSupersiveId());
            happychargeChargeStateParam.setSn(chargeStateParam.getStartChargeSeq());
        }
        // 充电状态接口：数据库一定能返回的数据
        ChargeStateBaseVo chargeStateBaseVo = happyService.queryChargeStateBaseVo(happychargeChargeStateParam);
        if (chargeStateBaseVo == null) {
            log.error("获取 订单基本信息为空, 参数:{} ", JsonUtils.toJsonString(happychargeChargeStateParam));
            return null;
        }
        if (operatorInfo.getSupersiveId().intValue() == chargeStateBaseVo.getSuperviseId()) {
            operatorInfo.setSn(chargeStateParam.getStartChargeSeq());
        }
        happychargeChargeStateParam.setSn(null);
        happychargeChargeStateParam.setSuperviseId(null);
        happychargeChargeStateParam.setStartChargeSeq(chargeStateBaseVo.getStartChargeSeq());


        // 最终返回数据对象
        ChargeStateVo evchargeStateVo = new ChargeStateVo();
        BeanUtils.copyProperties(chargeStateBaseVo, evchargeStateVo);

        evchargeStateVo.setStartChargeSeqStat(ChargeStateEnum.getChargeStateValue(evchargeStateVo.getStartChargeSeqStat()));
        evchargeStateVo.setConnectorID(SuperviseUtils.getConnectorID(chargeStateBaseVo.getPid(), chargeStateBaseVo.getGunnumber()));
        evchargeStateVo.setChargeModel(StartModelEnum.getStartModelValue(evchargeStateVo.getChargeModel()));
        // 设置当前采样时间
        evchargeStateVo.setEndTime(DateUtil.getNowDate());
        if (evchargeStateVo.getStartTime() == null) {
            evchargeStateVo.setStartTime(chargeStateBaseVo.getCreateTime());
        }

        // 获取充电设备接口状态
        DeviceStatusParam deviceStatusParam = new DeviceStatusParam();
        deviceStatusParam.setGnum(chargeStateBaseVo.getGunnumber());
        deviceStatusParam.setPilenum(chargeStateBaseVo.getPid());
        PileStatusInfoVo pilegun = happyService.getPilesByPilegnum(deviceStatusParam);
        if (pilegun == null) {
            log.error("查询 枪状态结果为空[参数:{}]", JsonUtils.toJsonString(deviceStatusParam));
            return null;
        }
        evchargeStateVo.setConnectorStatus(PileGunStatusEnum.getPileGunStatusValue(pilegun.getGstate()));


        // 查询充电结束，结算中的数据
        if (chargeStateBaseVo.getStartChargeSeqStat() == OrderEnum.Ostat.FINISH.key()
            || chargeStateBaseVo.getStartChargeSeqStat() == OrderEnum.Ostat.PAYING.key()) {

            // 如果已经结算完成, 则使用订单结算信息中电量及电费
            ChargeSuccessStateVo chargeSuccessStateVo = happyService.queryChargeSuccessStateVo(happychargeChargeStateParam);
            if (chargeSuccessStateVo == null) {
                log.error("已完成订单的结算数据查询结果为空, 参数:[{}]", JsonUtils.toJsonString(happychargeChargeStateParam));
            } else {
                evchargeStateVo.setTotalPower(chargeSuccessStateVo.getTotalPower());
                evchargeStateVo.setElecMoney(chargeSuccessStateVo.getElecMoney());
                evchargeStateVo.setSeviceMoney(chargeSuccessStateVo.getSeviceMoney());
                evchargeStateVo.setSoc(chargeSuccessStateVo.getSoc());

                List<FOrderSettlementDetail> fOrderSettlementDetail = happyService.getFOrderSettlementDetailBySn(happychargeChargeStateParam.getStartChargeSeq());

                // 设置 结算的分时电价明细
                List<ChargeOrderDetailVo> details = convertChargeOrderDetailVo(fOrderSettlementDetail, chargeStateBaseVo, chargeSuccessStateVo);
                evchargeStateVo.setChargeDetails(details);
                evchargeStateVo.setSumPeriod(details.size());
            }

        } else if (chargeStateBaseVo.getStartChargeSeqStat() != OrderEnum.Ostat.CANCEL.key()) {
//            evchargeStateVo.setConnectorStatus(Integer.parseInt(PileGunStatusEnum.KV_STARTUP.getValue()));
            // 查询电流电压
            ChargeDataTemp chargeDataTemp = happyService.getVoltageCurrent(happychargeChargeStateParam.getStartChargeSeq());
            log.debug("查询充电中 基本数据结果:[{}]", JsonUtils.toJsonString(chargeDataTemp));
            if (!ObjectUtils.isEmpty(chargeDataTemp)) {
//                evchargeStateVo.setConnectorStatus(Integer.parseInt(PileGunStatusEnum.KV_CHARGING.getValue()));
                evchargeStateVo.setTotalPower(chargeDataTemp.getUseele() == null ? 0 : chargeDataTemp.getUseele().doubleValue());
                evchargeStateVo.setCurrentA(chargeDataTemp.getCurrent().doubleValue());
                evchargeStateVo.setVoltageA(chargeDataTemp.getVoltage().doubleValue());
                evchargeStateVo.setSoc(Double.parseDouble(chargeDataTemp.getSoc()));
            }
        } else {
            evchargeStateVo.setConnectorStatus(Integer.parseInt(PileGunStatusEnum.KV_FREE.getValue()));
        }

        return evchargeStateVo;
    }

    /**
     * 充电时间段，及ABC相电流电压
     *
     * @param evchargeStateVo
     * @param sumPeriod       时段数 N 范围：0～32
     * @param currentA        A 相电流: 必填参数 单位：A，默认：0 含直流(输出)
     * @param currentB        B 相电流: 非必填参数 单位：A，默认：0
     * @param currentC        C 相电流: 非必填参数 单位：A，默认：0
     * @param voltageA        A 相电压: 必填参数 单位：V，默认：0 含直流(输出)
     * @param voltageB        B 相电压: 非必填参数 单位：V，默认：0
     * @param voltageC        C 相电压: 非必填参数 单位：V，默认：0
     */
    private void setEvchargeStateVoDetail(ChargeStateVo evchargeStateVo, Integer sumPeriod, Double currentA, Double
            currentB, Double currentC, Double voltageA, Double voltageB, Double voltageC) {
        evchargeStateVo.setSumPeriod(sumPeriod);
        evchargeStateVo.setCurrentA(currentA);
        evchargeStateVo.setCurrentB(currentB);
        evchargeStateVo.setCurrentC(currentC);
        evchargeStateVo.setVoltageA(voltageA);
        evchargeStateVo.setVoltageB(voltageB);
        evchargeStateVo.setVoltageC(voltageC);
    }

    /**
     * 充电明细信息体
     *
     * @param evchargeStateVo
     * @param detailElecMoney   时段 电费
     * @param detailEndTime     电结束时间
     * @param detailPower       时段 充电量
     * @param detailSeviceMoney 时段 服务费
     * @param detailStartTime   充电开始时间
     * @param elecPrice         时段电价
     * @param sevicePrice       时段服务费价格
     */
    private void setChargeOrderDetailVo(ArrayList arrayList, ChargeStateVo evchargeStateVo, Double detailElecMoney, Date detailEndTime, Double detailPower, Double detailSeviceMoney, Date detailStartTime, Double elecPrice, Double sevicePrice) {

        ChargeOrderDetailVo chargeOrderDetailVo = new ChargeOrderDetailVo();
        chargeOrderDetailVo.setDetailElecMoney(detailElecMoney);
        chargeOrderDetailVo.setDetailEndTime(detailEndTime);
        chargeOrderDetailVo.setDetailPower(detailPower);
        chargeOrderDetailVo.setDetailSeviceMoney(detailSeviceMoney);
        chargeOrderDetailVo.setDetailStartTime(detailStartTime);
        chargeOrderDetailVo.setElecPrice(elecPrice);
        chargeOrderDetailVo.setSevicePrice(sevicePrice);
        if (arrayList != null) {
            arrayList.add(chargeOrderDetailVo);
        }
        evchargeStateVo.setChargeDetails(arrayList);
    }

    /**
     * 分时段--充电明细体
     *
     * @param details
     * @param chargeStateBaseVo
     * @param chargeSuccessStateVo
     * @return
     */
    private List<ChargeOrderDetailVo> convertChargeOrderDetailVo(List<FOrderSettlementDetail> details, ChargeStateBaseVo chargeStateBaseVo, ChargeSuccessStateVo chargeSuccessStateVo) {
        List<ChargeOrderDetailVo> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(details)) {
            for (FOrderSettlementDetail detail : details) {
                ChargeOrderDetailVo chargeOrderDetailVo = new ChargeOrderDetailVo();
                chargeOrderDetailVo.setDetailElecMoney(detail.getEleMoney().doubleValue());
                chargeOrderDetailVo.setDetailEndTime(detail.getEndTime());
                chargeOrderDetailVo.setDetailPower(detail.getPower().doubleValue());
                chargeOrderDetailVo.setDetailSeviceMoney(detail.getServiceMoney().doubleValue());
                chargeOrderDetailVo.setDetailStartTime(detail.getStartTime());
                chargeOrderDetailVo.setElecPrice(detail.getEprice().doubleValue());
                chargeOrderDetailVo.setSevicePrice(detail.getSprice().doubleValue());
                list.add(chargeOrderDetailVo);
            }
        } else if (chargeStateBaseVo != null && chargeSuccessStateVo != null) {
            ChargeOrderDetailVo chargeOrderDetailVo = new ChargeOrderDetailVo();
            // 总电费
            Double elecMoney = chargeSuccessStateVo.getElecMoney();
            //
            chargeOrderDetailVo.setDetailElecMoney(elecMoney);
            chargeOrderDetailVo.setDetailEndTime(chargeStateBaseVo.getEndTime());
            // 总充电量
            Double totalPower = chargeSuccessStateVo.getTotalPower();
            chargeOrderDetailVo.setDetailPower(totalPower);
            // 总服务费
            Double seviceMoney = chargeSuccessStateVo.getSeviceMoney();
            chargeOrderDetailVo.setDetailSeviceMoney(seviceMoney);
            chargeOrderDetailVo.setDetailStartTime(chargeStateBaseVo.getStartTime());
            // 模拟 电费单价、服务费单价
            // 电费单价 = 总电费/充电量 、  服务费单价 = 总服务费/充电量
            chargeOrderDetailVo.setElecPrice(elecMoney / totalPower);
            chargeOrderDetailVo.setSevicePrice(seviceMoney / totalPower);
            list.add(chargeOrderDetailVo);
        }
        return list;
    }


    /**
     * <p>
     * 此查询用于定期获取每个充电站，在某个周期内的统计信息。
     * </p>
     * 使用方法：由充电运营商方实现此接口，数据需求方调用。
     *
     * @param stationStatsInfoParam
     * @return
     */
    public StationStatsInfoResult queryStationStats(@RequestBody StationStatsInfoParam stationStatsInfoParam) {
        StationStatsInfoVo stationStatsInfoVo = new StationStatsInfoVo();
        stationStatsInfoVo.setStationID(stationStatsInfoParam.getStationID());
        stationStatsInfoVo.setStartTime(stationStatsInfoParam.getStartTime());
        stationStatsInfoVo.setEndTime(stationStatsInfoParam.getEndTime());

        //创建interface的输入值。同步信息；
        com.zjxdqh.face.param.StationStatsInfoParam chargeStationStatsInfoParam = new com.zjxdqh.face.param.StationStatsInfoParam();
        BeanUtils.copyProperties(stationStatsInfoParam, chargeStationStatsInfoParam);

        //获取返回值，同步信息
        List<com.zjxdqh.face.vo.StationStatsInfo> chargeStationStatsInfos = happyService.queryStationStats(chargeStationStatsInfoParam);
        List<EvcsStationStatsInfo> stationStatsInfos = new ArrayList<>();

        for (com.zjxdqh.face.vo.StationStatsInfo chargeStationStatsInfo : chargeStationStatsInfos) {
            EvcsStationStatsInfo stationStatsInfo = new EvcsStationStatsInfo();
            BeanUtils.copyProperties(chargeStationStatsInfo, stationStatsInfo);
            stationStatsInfos.add(stationStatsInfo);
        }

        //获得充电站的总电量
        stationStatsInfoVo.setStationElectricity(0D);
        Double siteEle = stationStatsInfoVo.getStationElectricity();
        for (int i = 0; i < stationStatsInfos.size(); i++) {
            EvcsStationStatsInfo stationStatsInfo = stationStatsInfos.get(i);
            siteEle = MathUtils.add(siteEle, stationStatsInfo.getUseEle(), 2).doubleValue();
        }

        stationStatsInfoVo.setStationElectricity(siteEle);

        //获得桩集合
        List<EquipmentStatsInfoVo> equipmentStatsInfos = stationStatsInfoVo.getEquipmentStatsInfos();
        Map<String, Double> pileMap = new HashMap<>();
        HashMap<GunEle, Double> gunMap = new HashMap<>();

        for (int i = 0; i < stationStatsInfos.size(); i++) {
            EvcsStationStatsInfo stationStatsInfo = stationStatsInfos.get(i);
            //获得桩号，订单电量，枪号
            String pileNum = stationStatsInfo.getPileNum();
            double useEle = stationStatsInfo.getUseEle();
            String gunNum = stationStatsInfo.getGunNum();

            GunEle gunEle = new GunEle();
            gunEle.setPileNum(pileNum);
            gunEle.setGunNum(gunNum);

            //判断是否是同一个枪,叠加电量
            if (!gunMap.containsKey(gunEle)) {
                gunMap.put(gunEle, useEle);
            } else {
                Double gunuUseEle = gunMap.get(gunEle);
                gunuUseEle = MathUtils.add(gunuUseEle, useEle, 2).doubleValue();
                gunMap.put(gunEle, gunuUseEle);
            }

            //判断是否是同一个桩,叠加枪电量
            if (!pileMap.containsKey(pileNum)) {
                pileMap.put(pileNum, useEle);
            } else {
                Double pileUseEle = pileMap.get(pileNum);
                pileUseEle = MathUtils.add(pileUseEle, useEle, 2).doubleValue();
                pileMap.put(pileNum, pileUseEle);
            }
        }

        //注入枪和桩参数
        for (String s : pileMap.keySet()) {
            EquipmentStatsInfoVo equipmentStatsInfoVo = new EquipmentStatsInfoVo();
            equipmentStatsInfoVo.setEquipmentID(s);
            equipmentStatsInfoVo.setEquipmentElectricity(pileMap.get(s));

            //遍历取出枪号和枪电量
            for (GunEle gunele : gunMap.keySet()) {
                ConnectorStatsInfoVo connectorStatsInfoVo = new ConnectorStatsInfoVo();
                connectorStatsInfoVo.setConnectorID(gunele.getGunNum());
                connectorStatsInfoVo.setConnectorElectricity(gunMap.get(gunele));

                if (s.equals(gunele.getPileNum())) {
                    List<ConnectorStatsInfoVo> connectorStatsInfos = equipmentStatsInfoVo.getConnectorStatsInfos();
                    connectorStatsInfos.add(connectorStatsInfoVo);
                    equipmentStatsInfoVo.setConnectorStatsInfos(connectorStatsInfos);
                }
            }
            equipmentStatsInfos.add(equipmentStatsInfoVo);
        }
        stationStatsInfoVo.setEquipmentStatsInfos(equipmentStatsInfos);

        //将返回值包装起来
        StationStatsInfoResult stationStatsInfoResult = new StationStatsInfoResult();
        stationStatsInfoResult.setStationStats(stationStatsInfoVo);

        return stationStatsInfoResult;

    }

    /**
     * 监管平台请求 6.4.设备接口状态查询
     *
     * @param stationIdParam
     * @return
     */
    public StationStatusInfosVo queryStationStatus(StationIdParam stationIdParam) {
        List<StationStatusInfoVo> stlist = new ArrayList<StationStatusInfoVo>();

        List<String> StationIDs = stationIdParam.getStationIDs();

        for (String sta : StationIDs) {
            List<PileStatusInfoVo> lists = happyService.getPilesByStationId(sta);
            List<ConnectorStatusInfoVo> infos = new ArrayList<ConnectorStatusInfoVo>();

            for (PileStatusInfoVo infoVo : lists) {
                ConnectorStatusInfoVo connInfo = new ConnectorStatusInfoVo();
                connInfo.setConnectorID(SuperviseUtils.getConnectorID(infoVo.getPilenum(), infoVo.getGnum()));
                connInfo.setStatus(PileGunStatusEnum.getPileGunStatusValue(infoVo.getGstate()));
                connInfo.setLockStatus(LockStatusEnum.KV_UNKNOWN.getKey());
                connInfo.setParkStatus(ParkStatusEnum.KV_UNKNOWN.getKey());
                infos.add(connInfo);
            }

            StationStatusInfoVo infoVo = new StationStatusInfoVo();
            infoVo.setStationID(sta);
            infoVo.setConnectorStatusInfos(infos);

            stlist.add(infoVo);
        }

        StationStatusInfosVo statusInfosVo = new StationStatusInfosVo();
        statusInfosVo.setStationStatusInfos(stlist);

        return statusInfosVo;
    }


    @Cacheable(value = "evcs:getSuperviseSites", key = "#param")
    public QueryStationsInfoResult getSuperviseSites(QueryStationsInfoParam param) {
        OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
        QueryStationsInfoResult result = new QueryStationsInfoResult();
        PageInfo<FPileSite> pageSites = happyService.getSuperviseSite(param.getOperatorId(), param.getLastQueryTime(), param.getPageNo(), param.getPageSize());

        List<FPileSite> sites = pageSites.getList();
        if (!CollectionUtils.isEmpty(sites)) {
            result.setPageNo(pageSites.getPageNum());
            result.setPageCount(pageSites.getPages());
            result.setItemSize(pageSites.getPageSize());
            List<StationInfoVo> stations = new ArrayList<>();

            sites.forEach(s -> {
                StationInfoVo station = new StationInfoVo();
                station.setAddress(s.getAddress());
                station.setStationID(SuperviseUtils.getStationId(operatorInfo.getOrgId(), s.getSid()));

                station.setOperatorID(operatorInfo.getOperatorId());
                station.setEquipmentOwnerID(station.getOperatorID());
                station.setStationName(s.getSname());
                station.setCountryCode("CN");
                station.setAreaCode(s.getAreaid() != null ? s.getAreaid().toString() : null);
                station.setServiceTel("400-");
                station.setStationType(1);
                station.setStationStatus(50);
                station.setParkNums(s.getPiles().size());
                station.setStationLng(s.getLongitude() != null ? s.getLongitude().doubleValue() : null);
                station.setStationLat(s.getLongitude() != null ? s.getLatitude().doubleValue() : null);
                station.setConstruction(5);
                station.setEquipmentInfos(new ArrayList<>());

                station.setPayment("微信/支付宝");

                station.setEquipmentInfos(initEquipment(s.getPiles()));
                stations.add(station);
            });
            result.setStationInfos(stations);
        }
        return result;
    }

    private List<EquipmentInfoVo> initEquipment(List<FPile> piles) {
        if (!CollectionUtils.isEmpty(piles)) {
            List<EquipmentInfoVo> equipments = new ArrayList<>();
            piles.forEach(p -> {
                EquipmentInfoVo equipment = new EquipmentInfoVo();
                equipment.setEquipmentID(p.getPilenum());
                equipment.setManufacturerName(p.getManufacture());
                equipment.setEquipmentModel(p.getModel());
                equipment.setProductionDate(p.getCreatetime());
                equipment.setEquipmentType(p.getPtype() == 1 ? 1 : 2);
                if (StringUtils.hasLength(p.getPower()) && p.getGuncount() != null) {
                    equipment.setPower((double) (Integer.valueOf(p.getPower()) * p.getGuncount()));
                }

                equipment.setConnectorInfos(new ArrayList<>());
                for (Integer i = 1; i <= p.getGuncount(); i++) {
                    ConnectorInfoVo connector = new ConnectorInfoVo();
                    connector.setConnectorID(SuperviseUtils.getConnectorID(p.getPilenum(), i.toString()));
                    connector.setConnectorType(4);
                    connector.setVoltageLowerLimits(10);
                    if (StringUtils.hasLength(p.getVoltage())) {
                        connector.setVoltageUpperLimits(Integer.valueOf(p.getVoltage()));
                    }
                    if (StringUtils.hasLength(p.getPower())) {
                        connector.setPower((float) Integer.valueOf(p.getPower()));
                    }
                    String current = p.getCurrent().indexOf("-") != -1 ? p.getCurrent().substring(p.getCurrent().indexOf("-") + 1) : p.getCurrent();
                    connector.setCurrent(Integer.valueOf(current));
                    connector.setNationalStandard(2);
                    equipment.getConnectorInfos().add(connector);
                }
                equipments.add(equipment);

            });
            return equipments;
        }
        return null;
    }


    /**
     * 根据 对接方 组织代码查询 密钥信息
     *
     * @param operatorId
     * @return
     */
    public OperatorInfo getSupersiveInfo(String operatorId) {
        return SuperviseUtils.convertOperatorInfo(happyService.getSupersiveInfo(operatorId));
    }

    /**
     * 根据 主键 查询 密钥信息
     *
     * @param superviseId
     * @return
     */
    public OperatorInfo getSupersiveInfoById(String superviseId) {
        return SuperviseUtils.convertOperatorInfo(happyService.getSupersiveInfoById(superviseId));
    }

    /**
     * 根据 桩编号 查询 密钥信息
     *
     * @param pileNum
     * @return
     */
    @Cacheable(value = "getSupersiveInfoByPilenum", key = "#pileNum")
    public List<OperatorInfo> getSupersiveInfoByPilenum(String pileNum) {
        List<SupersiveInfo> supersiveInfos = happyService.getSupersiveInfoByPilenum(pileNum);
        List<OperatorInfo> operatorInfos = new ArrayList<>();
        for (SupersiveInfo supersiveInfo : supersiveInfos) {
            operatorInfos.add(SuperviseUtils.convertOperatorInfo(supersiveInfo));
        }
        return operatorInfos;
    }

    /**
     * 根据 场站编号 查询 密钥信息
     *
     * @param sid
     * @return
     */
    @Cacheable(value = "getSupersiveInfoBySid", key = "#sid")
    public List<OperatorInfo> getSupersiveInfoBySid(String sid) {
        List<SupersiveInfo> supersiveInfos = happyService.getSupersiveInfoBySid(sid);
        List<OperatorInfo> operatorInfos = new ArrayList<>();
        for (SupersiveInfo supersiveInfo : supersiveInfos) {
            operatorInfos.add(SuperviseUtils.convertOperatorInfo(supersiveInfo));
        }
        return operatorInfos;
    }

    /**
     * 请求启动充电
     *
     * @param query
     * @return
     */
    public StartChargeQueryResult startCharge(StartChargeQuery query) {
        StartChargeQueryResult res = new StartChargeQueryResult();
        try {
            OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();

            // 默认设置充电金额 100
            BigDecimal amount = new BigDecimal(100);

            StartChargeParam param = new StartChargeParam();

            param.setPileNum(SuperviseUtils.getPileNumByDeviceNo(query.getConnectorID()));
            param.setGunNum(SuperviseUtils.getGunNumByDeviceNo(query.getConnectorID()));
            String orderNo = SuperviseUtils.getOrderNo(param.getPileNum(), param.getGunNum(), 3);
            param.setOrderNo(orderNo);
            param.setSuperviseId(operatorInfo.getSupersiveId());
            param.setSuperviseSn(query.getStartChargeSeq());

            res.setConnectorID(query.getConnectorID());
            res.setStartChargeSeq(SuperviseUtils.convert2StartChargeSeq(operatorInfo.getOrgId(), orderNo));

            param.setAmount(amount);

            // 获取默认 用户uid
            if (StringUtils.isEmpty(operatorInfo.getAccountUserId())) {

                // 帐户获取 失败
                res.setFailReason(4);
                return res;
            }
            param.setUserId(Integer.valueOf(operatorInfo.getAccountUserId()));
            StartChargeResult result = happyService.startCharge(param);
            if (result != null && !StringUtils.isEmpty(result.getSn())) {
                // 启动中
                res.setStartChargeSeqStat(1);
                res.setSuccStat(0);

                // 发送 开始充电请求
                rabbitMqSender.start(param);
            }
        } catch (BuzzException e) {
            if (e.getCode() == ExceptionEnum.ERROR_GUN_STAT) {
                // 此设备离线
                res.setFailReason(1);
            } else if (e.getCode() == ExceptionEnum.GUN_NOT_CONNET) {
                // 此设备不存在
                res.setFailReason(2);
            } else if (e.getCode() == ExceptionEnum.BALANCE_LACK) {
                // 帐户余额不足
                res.setFailReason(4);
            } else {
                res.setFailReason(3);
            }
            log.error("请求开始充电失败. ", e);
        } catch (Exception e) {
            log.error("请求开始充电失败. ", e);
            res.setFailReason(3);
        }
        return res;
    }


    /**
     * 请求停止充电
     *
     * @param query
     * @return
     */
    public StopChargeResult stopCharge(StopChargeParam query) {

        StopChargeResult stopResult = new StopChargeResult();
        stopResult.setSuccStat(1);
        stopResult.setFailReason(0);
        stopResult.setStartChargeSeq(query.getStartChargeSeq());

        String orderNo = SuperviseUtils.convert2OrderNo(query.getStartChargeSeq());

        // 请求停止充电，按第三方平台 订单号查询
        com.zjxdqh.face.param.ChargeStateParam queryParam = new com.zjxdqh.face.param.ChargeStateParam();
        OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
        if (operatorInfo == null) {
            stopResult.setFailReason(99);
            return stopResult;
        }
        queryParam.setSuperviseId(operatorInfo.getSupersiveId());
        queryParam.setSn(query.getStartChargeSeq());

        try {
            ChargeStateBaseVo order = happyService.queryChargeStateBaseVo(queryParam);

            if (order == null) {
                stopResult.setFailReason(4);
                return stopResult;
            }
            if (order.getStartChargeSeqStat() == OrderEnum.Ostat.FINISH.key()
                    || order.getStartChargeSeqStat() == OrderEnum.Ostat.CANCEL.key()
                    || order.getStartChargeSeqStat() == OrderEnum.Ostat.PAYING.key()) {
                stopResult.setFailReason(3);
                return stopResult;
            }

            StartChargeParam param = new StartChargeParam();
            param.setPileNum(SuperviseUtils.getPileNumByDeviceNo(query.getConnectorID()));
            param.setGunNum(SuperviseUtils.getGunNumByDeviceNo(query.getConnectorID()));
            param.setOrderNo(orderNo);
            param.setAmount(new BigDecimal(order.getAmount()));
            param.setUserId(order.getUserId());

            rabbitMqSender.stop(param);
            stopResult.setSuccStat(0);
            // 状态停止中
            stopResult.setStartChargeSeqStat(3);
        } catch (Exception e) {
            log.error("请求结束充电[{}]出现异常:", orderNo);
            log.error("异常信息:", e);
        }
        return stopResult;
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
    public RequestEquipmentVo queryEquipAuth(RequestEquipmentParam requestEquipmentParam) {
        // 桩号
        String pileNumByDeviceNo = SuperviseUtils.getPileNumByDeviceNo(requestEquipmentParam.getConnectorID());
        // 枪号
        String gunNumByDeviceNo = SuperviseUtils.getGunNumByDeviceNo(requestEquipmentParam.getConnectorID());
//        DeviceStatusParam deviceStatusParam = new DeviceStatusParam();
//        deviceStatusParam.setPilenum(pileNumByDeviceNo);
//        deviceStatusParam.setGnum(gunNumByDeviceNo);
        // 整理设备认证返回参数
        //PileStatusInfoVo pilesByPilegnum = happyService.getPilesByPilegnum(deviceStatusParam);
        RequestEquipmentVo requestEquipmentVo = new RequestEquipmentVo();
        requestEquipmentVo.setConnectorID(requestEquipmentParam.getConnectorID());
        requestEquipmentVo.setEquipAuthSeq(requestEquipmentParam.getEquipAuthSeq());

        // 充电枪未连接
//        if (pilesByPilegnum == null || pilesByPilegnum.getGstate() != 1) {
//            requestEquipmentVo.setConnectorID(requestEquipmentParam.getConnectorID());
//            requestEquipmentVo.setEquipAuthSeq(requestEquipmentParam.getEquipAuthSeq());
//            requestEquipmentVo.setSuccStat(1);
//            if (pilesByPilegnum == null || pilesByPilegnum.getGstate() == null) {
//                requestEquipmentVo.setFailReason(2);
//            } else {
//                Integer queryEquipAuthStateValue = QueryEquipAuthEnum.getQueryEquipAuthStateValue(pilesByPilegnum.getGstate());
//                requestEquipmentVo.setFailReason(queryEquipAuthStateValue);
//            }
//            return requestEquipmentVo;
//        }
        //
        QueryPileGunStateParam param=new QueryPileGunStateParam();
        param.setPilenum(pileNumByDeviceNo);
        param.setGnum(gunNumByDeviceNo);
        List<PileGunStateVo> list=collectService.queryPileGunState(param);
        requestEquipmentVo.setSuccStat(1);
        if(list.size()<=0)
        {
            requestEquipmentVo.setFailReason(2);
            return  requestEquipmentVo;
        }

        PileGunStateVo  stateVo=list.get(0);
        //离线
        if("2".equals(stateVo.getPileSigninState()))
        {
            requestEquipmentVo.setFailReason(PileGunCollectStatusEnum.KV_8.getKey());
            return requestEquipmentVo;
        }
        //2-故障 3-停用
        if("2".equals(stateVo.getBuildStatus()))
        {
            requestEquipmentVo.setFailReason(PileGunCollectStatusEnum.KV_10.getKey());
            return requestEquipmentVo;

        }else if("3".equals(stateVo.getBuildStatus()))
        {
            requestEquipmentVo.setFailReason(PileGunCollectStatusEnum.KV_9.getKey());
            return requestEquipmentVo;
        }

        String gunChargingState=stateVo.getGunChargingState();

        if(!String.valueOf(PileGunCollectStatusEnum.KV_2.getKey()).equals(gunChargingState))
        {
            requestEquipmentVo.setFailReason(PileGunCollectStatusEnum.getPileGunCollectStatusValue(Integer.parseInt(gunChargingState)));

            return requestEquipmentVo;
        }

        requestEquipmentVo.setSuccStat(0);
        requestEquipmentVo.setFailReason(0);
        return requestEquipmentVo;
    }

    /**
     * 6.3 查询业务策略信息结果
     *
     * @param businessPolicyParam
     * @return
     */
    public BusinessTacticsInfoVo queryEquipBusinessPolicy(BusinessPolicyParam businessPolicyParam) {

        List<PolicyInfo> policyInfo = happyService.getPolicyInfos(SuperviseUtils.getPileNumByDeviceNo(businessPolicyParam.getConnectorID()));
        List<PolicyInfoVo> policyInfos = new ArrayList<>();
        for (PolicyInfo info : policyInfo) {
            PolicyInfoVo policyInfoVo = new PolicyInfoVo();
            BeanUtils.copyProperties(info, policyInfoVo);
            // 一个单词之差，重新赋值set方法
            policyInfoVo.setSevicePrice(info.getServicePrice());
            policyInfos.add(policyInfoVo);
        }
        log.info("List<PolicyInfoVo> policyInfos 查询结果=[{}]", policyInfos);
        BusinessTacticsInfoVo businessTacticsInfoVo = new BusinessTacticsInfoVo();
        businessTacticsInfoVo.setConnectorID(businessPolicyParam.getConnectorID());
        businessTacticsInfoVo.setEquipBizSeq(businessPolicyParam.getEquipBizSeq());
        // 此充电桩业务策略不存在
        if (policyInfos.size() == 0) {
            businessTacticsInfoVo.setSuccStat(1);
            businessTacticsInfoVo.setFailReason(1);
        } else {
            businessTacticsInfoVo.setSuccStat(0);
            businessTacticsInfoVo.setFailReason(0);
        }
        businessTacticsInfoVo.setSumPeriod(policyInfos.size());
        businessTacticsInfoVo.setPolicyInfos(policyInfos);

        return businessTacticsInfoVo;
    }


    /**
     * 6.5推送启动充电结果
     *
     * @param orderNo
     */
    public void pushStartChargingResult(String orderNo) {

        com.zjxdqh.face.param.ChargeStateParam chargeStatParam = new com.zjxdqh.face.param.ChargeStateParam();
        chargeStatParam.setStartChargeSeq(orderNo);

        ChargeStateBaseVo baseOrder = happyService.queryChargeStateBaseVo(chargeStatParam);
        StartChargingResultParam resultParam = new StartChargingResultParam();

        resultParam.setStartChargeSeq(baseOrder.getStartChargeSeq());
        resultParam.setConnectorID(SuperviseUtils.getConnectorID(baseOrder.getPid(), baseOrder.getGunnumber()));
        resultParam.setStartChargeSeqStat(ChargeStateEnum.getChargeStateValue(baseOrder.getStartChargeSeqStat()));
        resultParam.setStartTime(baseOrder.getCreateTime());

        //新增推送字段
        resultParam.setChargeModel(3);
        if (baseOrder.getVin() == null){//约定，VIN不存在则传空串
            resultParam.setVin("");
        }else{
            resultParam.setVin(baseOrder.getVin());
        }

        ResponseResult<StartChargingResult> startChargeResult = notificationService.notificationStartChargeResult(resultParam);


    }

    /**
     * 推送 场站费率 变更 提醒
     *
     * @param sids 场站ID 数组
     */
    public void pushSiteChange(String[] sids) {
        StationChangeParam stationParam = new StationChangeParam();
        stationParam.setStationIds(Arrays.asList(sids));
        notificationService.notificationStationChange(stationParam);

    }

    /**
     * 订单超时退款
     *
     * @param orderNo
     * @return
     */
    public int cancelOrderBackMoney(String orderNo) {
        return happyService.backOrder(orderNo);
    }


    /**
     * 推送消息：补贴标准接口
     */
    public void notificationRecordsAmountInfo() {
        // 按月申报
        if (time == 1) {
            getSubsidy(DateUtil.getNowDateStr(DateUtil.formatStr_yyyyMMdd), DateUtil.getFrontOneMonth());
        }
        // 按季度申报
        if (time == 2) {
            getSubsidy(DateUtil.getNowDateStr(DateUtil.formatStr_yyyyMMdd), DateUtil.getFrontThreeMonth());
        }
    }


    private void getSubsidy(String endTime, String startTime) {
        List<Subsidy> subsidys = happyService.notificationRecordsAmountInfo(supersiveId);
        for (Subsidy subsidy : subsidys) {
            SubsidyParam param = new SubsidyParam();
            param.setConnectorID(subsidy.getConnectorID());
            param.setEquipmentID(subsidy.getEquipmentID());
            param.setStationID(subsidy.getStationID());
            param.setEndTime(endTime);
            param.setStartTime(startTime);
            param.setApplySubsidyPrice(applySubsidyPrice);
            param.setStakeType(StakeTypeEnum.getStakeTypeEnumValue(Integer.parseInt(subsidy.getStakeType())));
            log.info("补贴标准接口传入数据：[{}]", param);
            ResponseResult<SubsidyVo> subsidyVo = notificationService.notificationRecordsAmountInfo(param);
//            log.info("补贴标准接口返回数据：[{}]", subsidyVo);
        }
    }

}
