package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author: wangqinmin
 * @date: 2019/5/9 16:33
 * @description: 设备运营商信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StationInfoVo {

    /**
     * 运营商自定义的唯一编码
     * 长度：<=20 字
     */
    private String StationID;

    /**
     * 运营商 ID
     * 长度：9 字符
     */
    private String OperatorID;

    /**
     * 设备所属运营平台组织机构代码
     * 长度：9 字符
     */
    private String EquipmentOwnerID;

    /**
     * 充电站名称的描述
     * 长度：<=50 字符
     */
    private String StationName;

    /**
     * 充电站国家代码：比如 CN
     * 长度：2 字符
     */
    private String CountryCode;

    /**
     * 充电站省市辖区编码：填写内容为参照GB/T2260-2015
     * 长度：20 字符
     */
    private String AreaCode;

    /**
     * 详细地址
     * 长度：<=50 字符
     */
    private String Address;

    /**
     * 站点电话：能够联系场站工作人员进行协助的联系电话
     * 长度：<=30 字符
     */
    private String StationTel;

    /**
     * 服务电话：平台服务电话，例如400 的电话
     * 长度：<=30 字符
     */
    private String ServiceTel;

    /**
     * 站点类型:
     * 1：公共
     * 50：个人
     * 100：公交（专用）
     * 101：环卫（专用）
     * 102：物流（专用）
     * 103：出租车（专用）
     * 255：其他
     */
    private Integer StationType;

    /**
     * 站点状态:
     * 0：未知
     * 1：建设中
     * 5：关闭下线
     * 6：维护中
     * 50：正常使用
     */
    private Integer StationStatus;

    /**
     * 车位数量:
     * 可停放进行充电的车位总数，默认：0 未知
     */
    private Integer ParkNums;

    /**
     * 经度: GCJ-02 坐标系
     * 长度：保留小数点后6 位
     */
    private Double StationLng;

    /**
     * 纬度: GCJ-02 坐标系
     * 长度：保留小数点后6 位
     */
    private Double StationLat;

    /**
     * 站点引导:
     * 描述性文字，用于引导车主找到充电车位
     * 长度：<=100字符
     */
    private String SiteGuide;

    /**
     * 建设场所
     * 1：居民区
     * 2：公共机构
     * 3：企事业单位
     * 4：写字楼
     * 5：工业园区
     * 6：交通枢纽
     * 7：大型文体设施
     * 8：城市绿地
     * 9：大型建筑配建停车场
     * 10：路边停车位
     * 11：城际高速服务区
     * 255：其他
     */
    private Integer Construction;

    /**
     * 站点照片：充电设备照片、充电车位照片、停车场入口照片
     */
    private String[] Pictures;

    /**
     * 使用车型描述：
     * 描述该站点接受的车大小以及类型，如大巴、物流车、私家乘用车、出租车等
     * 长度：<=100字符
     */
    private String MatchCars;

    /**
     * 车位楼层及数量描述
     * 长度：<=100字符
     */
    private String ParkInfo;

    /**
     * 营业时间描述
     * 长度：<=100字符
     */
    private String BusineHours;

    /**
     * 充电电费率:充电费描述
     * 长度：<=256字符
     */
    private String ElectricityFee;

    /**
     * 服务费率：服务费率描述
     * 长度：<=100字符
     */
    private String ServiceFee;

    /**
     * 停车费：停车费率描述
     * 长度：<=100字符
     */
    private String ParkFee;

    /**
     * 支付方式:
     * 刷卡、线上、现金其中电子钱包类卡为刷卡，身份鉴权卡、微信/ 支付宝、APP 为线上
     * 长度：<=20 字符
     */
    private String Payment;

    /**
     * 是否支持预约：充电设备是否需要提前预约后才能使用。
     * 0 为不支持预约、1 为支持预约。不填默认为 0
     */
    private Integer SupportOrder;

    /**
     * 备注: 其他备注信息
     * 长度：<=100字符
     */
    private String Remark;

    /**
     * 充电设备信息列表:该充电站所有充电设备信息对象集合
     */
    private List<EquipmentInfoVo> EquipmentInfos;
}
