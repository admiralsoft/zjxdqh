package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: wangqinmin
 * @date: 2019/5/9 16:52
 * @description: 充电设备信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class EquipmentInfoVo {

    /**
     * 设备编码：设备唯一编码，对同一运营商，保证唯一
     * 长度：<=23 字符
     */
    private String EquipmentID;

    /**
     * 设备生产商组织机构代码
     * 长度：9字符
     */
    private String ManufacturerID;

    /**
     * 设备生产商名称
     * 长度：<=30 字符
     */
    private String ManufacturerName;

    /**
     * 设备型号:由设备生厂商定义的设备型号
     * 长度：<=20 字符
     */
    private String EquipmentModel;

    /**
     * 设备生产日期: YYYY-MM-DD
     * 长度：10 字符
     */
    @JsonFormat(pattern = DateUtils.Y_M_D)
    private Date ProductionDate;
    /**
     * 设备类型:
     * 1：直流设备
     * 2：交流设备
     * 3：交直流一体设备
     * 4：无线设备
     * 5：其他
     */
    private Integer EquipmentType;

    /**
     * 充电设备接口列表 : 该充电设备所有的充电设备接口的信息对象集合
     */
    private List<ConnectorInfoVo> ConnectorInfos;

    /**
     * 充电设备经度: GCJ-02 坐标系
     * 长度：保留小数点后 6 位
     */
    private Double EquipmentLng;

    /**
     * 充电设备纬度: GCJ-02 坐标系
     * 长度：保留小数点后 6 位
     */
    private Double EquipmentLat;

    /**
     * 充电设备总功率 :单位:kW
     * 长度：保留小数
     */
    private Double Power;

    /**
     * 充电设备名称
     * 长度：<=30 字符
     */
    private String EquipmentName;
}
