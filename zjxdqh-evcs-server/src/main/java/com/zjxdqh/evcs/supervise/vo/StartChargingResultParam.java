package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author chenshunhua
 * @date 2019/08/12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)

public class StartChargingResultParam extends BaseChargeSeqParam {

    /**
     * 充电订单状态
     */
    private  int   StartChargeSeqStat;

    /**
     * 充电设备接
     * 口编码
     */
    private String ConnectorID;

    /**
     * 开始充电时间
     */
    @JsonFormat(pattern = DateUtils.YMD_HMS)
    private Date StartTime;

    /**
     * 停止充电验证码
     */
    private String IdentCode;


    /**
     * 新增字段，启动充电结果
     * 终端客户开启充电方式
     * 0：未知
     * 1：市/省级平台无卡启动
     * 2：有卡启动
     * 3：其他无卡启动
     */
    private Integer ChargeModel;

    /**
     * 新增字段VIN信息
     * 车辆识别码；见 GB-T-27930-2015 国标
     * PGN512 BMS 和车辆辨识报文（BRM）约定，没
     * 有传空串
     */
    private String Vin;
}
