package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 充电设备接口信息
 *
 * @author chenshunhua
 * @date 2019/05/09
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ConnectorInfoVo {

    /**
     *充电设备接口
     编码
     *
     */
    private String ConnectorID;
    /**
     *充电设备接口
     名称
     */
    private String ConnectorName;
    /**
     *充电设备接口
     类型
     */
    private Integer ConnectorType;
    /**
     *额定电压上限
     */
    private Integer VoltageUpperLimits;
    /**
     *额定电压下限
     */
    private Integer VoltageLowerLimits;
    /**
     *额定电流
     */
    private Integer Current;
    /**
     *额定功率
     */
    private Float Power;
    /**
     *车位号
     */
    private String ParkNo;
    /**
     *国家标准
     */
    private Integer NationalStandard;



}
