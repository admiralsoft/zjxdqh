package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  充电站统计信息
 * <p>
 *
 * @author PengWei
 * @date 2019/5/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StationStatsInfoVo {

    /**
     * 充电站 ID
     */
    private String StationID;

    /**
     * 统计的开始时间
     */
    @JsonFormat(pattern = DateUtils.Y_M_D)
    private Date StartTime;

    /**
     * 统计的结束时间
     */
    @JsonFormat(pattern = DateUtils.Y_M_D)
    private Date EndTime;

    /**
     * 充电站累计电量（累计电量，单位kWh，精度 0.1）
     */
    private Double StationElectricity;

    /**
     * 充电设备统计信息列表（充电站中所有充电设备的统计对象集合）
     */
    private List<EquipmentStatsInfoVo> EquipmentStatsInfos = new ArrayList<>();

}
