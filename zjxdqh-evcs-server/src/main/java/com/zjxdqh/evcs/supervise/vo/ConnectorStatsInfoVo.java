package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * <p>
 *  充电设备接口统计信息
 * <p>
 *
 * @author PengWei
 * @date 2019/5/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ConnectorStatsInfoVo {

    /**
     * 充电设备接口编码(同一运营商内唯一)
     */
    private String ConnectorID;

    /**
     * 充电设备接口累计电量(累计电量，单位 kWh，精度0.1)
     */
    private Double ConnectorElectricity;

}
