package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import lombok.Data;

import java.util.List;

/**
 * @author Yorking
 * @date 2019/05/08
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChargeOrderInfoParam extends BaseChargeSeqParam {


    /**
     * 充电设备接
     * 口编码
     */
    private String ConnectorID;

    /**
     * 开始充电时间
     */
    @JsonFormat(pattern = DateUtils.YMD_HMS)
    private String StartTime;

    /**
     * 结束充电时间
     */
    @JsonFormat(pattern = DateUtils.YMD_HMS)
    private String EndTime;
    /**
     * 启动方式
     * 修改
     */
    private Integer ChargeModel;
    /**
     * 累计总电量
     */
    private Double TotalPower;
    /**
     * 总电费
     */
    private Double TotalElecMoney =0d;
    /**
     * 总服务费
     */
    private Double TotalSeviceMoney =0d;
    /**
     * 累计总金额
     */
    private Double TotalMoney;
    /**
     * 充电结束原因
     */
    private Integer StopReason;
    /**
     * 充电时段数
     */
    private Integer SumPeriod;

    /**
     * 充电明细信息
     */
    private List<ChargeOrderDetailVo> ChargeDetails;

    /**
     * 客户名称
     * 实际充电的客户在运营商侧的名称,<=20 字
     * 符
     */
    private String UserName;

    /**
     * 场站编码
     * 运营商自定义唯一编码， <=20 字符
     */
    private String StationID;

    /**
     * 桩编码
     * 运营商设备唯一编码，
     */
    private String EquipmentID;

    /**
     * 接口额定功率
     * 单位：kW, 小数点后 1 位
     */
    private Double ConnectorPower;

    /**
     * 充电时长
     * 单位：秒
     */
    private Integer  ChargeLast;

    /**
     * 电表总起值
     * 单位: 度， 小数点后 2 位
     */
    private Double MeterValueStart;

    /**
     * 电表总止值
     * 单位: 度， 小数点后 2 位
     */
    private Double MeterValueEnd;
}
