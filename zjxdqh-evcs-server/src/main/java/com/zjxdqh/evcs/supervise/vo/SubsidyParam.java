package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: wangqinmin
 * @date: 2019/8/20 09:28
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SubsidyParam implements Serializable {

    /**
     * 充电设备接口编号
     * <p>
     * 见T/CEC 102.2-2016
     */
    private String ConnectorID;

    /**
     * 设备编码：设备唯一编码，对同一运营商，保证唯一
     * 长度：<=23 字符
     */
    private String EquipmentID;

    /**
     * 运营商自定义的唯一编码
     * 长度：<=20 字
     */
    private String StationID;

    /**
     * 运营商 ID （组织机构代码）
     * 长度：9 字符
     */
    private String OperatorID;

    /**
     * 1; 公交专用充电桩
     * 2： 公用充电桩
     * 3： 其他
     * 4： 自定义
     */
    private Integer StakeType;

    /**
     * 申报开始时间 yyyy-MM-dd
     */
    private String StartTime;

    /**
     * 申报结束时间 yyyy-MM-dd
     */
    private String EndTime;

    /**
     * 申报价格
     */
    private Double ApplySubsidyPrice;
}
