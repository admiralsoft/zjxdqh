package com.tcp.bean;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * BMS返回信息
 * @author code_generator
 */
@Table(name = "log_ms_data")
public class LogMsData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 枪口编号 **/
	@Column(name="gun_num")
	@Data(byteType = DataByteTypEnum.Byte)
	private byte gunNum ; 

	/** 电池类型HEX码01H 铅酸电池；02H镍氢电池；03H磷酸铁锂电池；04H 锰酸锂电池；05H钴酸锂电池；06H 三元材料电池；07H 聚合物锂离⼦电池；08H 钛酸锂电池； **/
	@Column(name="dc_type")
	@Data(order = 1, byteType = DataByteTypEnum.Byte)
	private byte dcType ; 

	/** 电池通信版本 **/
	@Column(name="dc_tcp_type")
	@Data(order = 2,byteType = DataByteTypEnum.ASCII, byteLen = 4)
	private String dcTcpType ; 

	/** BMS 软件版本号 **/
	@Column(name="bms_version")
	@Data(order = 3,byteType = DataByteTypEnum.ASCII, byteLen = 4)
	private String bmsVersion ; 

	/** 电池⽣产⼚商 **/
	@Column(name="dc_dealer")
	@Data(order = 4,byteType = DataByteTypEnum.ASCII, byteLen = 4)
	private String dcDealer ; 

	/** 电池组序号 **/
	@Column(name="dcz_serial")
	@Data(order = 5,byteType = DataByteTypEnum.ASCII, byteLen = 4)
	private String dczSerial ; 

	/** 电池组充电次数 **/
	@Column(name="dcz_charge_number")
	@Data(order = 6,byteType = DataByteTypEnum.Number, byteLen = 4)
	private Integer dczChargeNumber ; 

	/** 电池组⽣产⽇期 **/
	@Column(name="dcz_create_time")
	@Data(order = 7,byteType = DataByteTypEnum.DATE_YMD, byteLen = 4)
	private Date dczCreateTime ; 

	/** 电压需求 **/
	@Column(name="dz_demand")
	@Data(order = 8,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String dzDemand ; 

	/** 电流需求 **/
	@Column(name="dl_demand")
	@Data(order = 9,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String dlDemand ; 

	/** 电池额定容量 **/
	@Column(name="dc_rated_capacity")
	@Data(order = 10,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String dcRatedCapacity ; 

	/** 电池额定总电压 **/
	@Column(name="dc_rated_voltage")
	@Data(order = 11,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String dcRatedVoltage ; 

	/** 单体动⼒蓄电池最⾼允许充电电压 **/
	@Column(name="rechargeable_voltage_max")
	@Data(order = 12,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String rechargeableVoltageMax ; 

	/** 最⾼允许充电电流 **/
	@Column(name="rechargeable_current_max")
	@Data(order = 13,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String rechargeableCurrentMax ; 

	/** 最⾼允许温度 **/
	@Column(name="rechargeable_temperature_max")
	@Data(order = 14,byteType = DataByteTypEnum.Number, byteLen = 2)
	private Integer rechargeableTemperatureMax ; 

	/** 整车动⼒蓄电池荷电状态 0~100%； **/
	@Column(name="rechargeable_status")
	@Data(order = 15,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String rechargeableStatus ; 

	/** 整车动⼒蓄电池当前电池电压 **/
	@Column(name="dc_voltage_now")
	@Data(order = 16,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String dcVoltageNow ; 

	/** 最⾼输出电压 **/
	@Column(name="voltage_out_max")
	@Data(order = 17,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String voltageOutMax ; 

	/** 最低输出电压 **/
	@Column(name="voltage_out_min")
	@Data(order = 18,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String voltageOutMin ; 

	/** 最⼤输出电流 **/
	@Column(name="current_out_max")
	@Data(order = 19,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String currentOutMax ; 

	/** 最小输出电流 **/
	@Column(name="current_out_min")
	@Data(order = 20,byteType = DataByteTypEnum.ASCII, byteLen = 2)
	private String currentOutMin ; 

	/** 创建时间 **/
	@Column(name="create_time")
	private Date createTime ; 

	/** 修改时间 **/
	@Column(name="modify_time")
	private Date modifyTime ; 

	/**  **/
	@Column(name="field1")
	private String field1 ; 

	/**  **/
	@Column(name="field2")
	private String field2 ; 

	/**  **/
	@Column(name="field3")
	private String field3 ; 

	/**  **/
	@Column(name="field4")
	private String field4 ; 

	/**  **/
	@Column(name="field5")
	private String field5 ; 


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPileNum() {
		return pileNum;
	}
	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
	}
	
	public byte getGunNum() {
		return gunNum;
	}
	public void setGunNum(byte gunNum) {
		this.gunNum = gunNum;
	}
	
	public byte getDcType() {
		return dcType;
	}
	public void setDcType(byte dcType) {
		this.dcType = dcType;
	}
	
	public String getDcTcpType() {
		return dcTcpType;
	}
	public void setDcTcpType(String dcTcpType) {
		this.dcTcpType = dcTcpType;
	}
	
	public String getBmsVersion() {
		return bmsVersion;
	}
	public void setBmsVersion(String bmsVersion) {
		this.bmsVersion = bmsVersion;
	}
	
	public String getDcDealer() {
		return dcDealer;
	}
	public void setDcDealer(String dcDealer) {
		this.dcDealer = dcDealer;
	}
	
	public String getDczSerial() {
		return dczSerial;
	}
	public void setDczSerial(String dczSerial) {
		this.dczSerial = dczSerial;
	}
	
	public Integer getDczChargeNumber() {
		return dczChargeNumber;
	}
	public void setDczChargeNumber(Integer dczChargeNumber) {
		this.dczChargeNumber = dczChargeNumber;
	}
	
	public Date getDczCreateTime() {
		return dczCreateTime;
	}
	public void setDczCreateTime(Date dczCreateTime) {
		this.dczCreateTime = dczCreateTime;
	}
	
	public String getDzDemand() {
		return dzDemand;
	}
	public void setDzDemand(String dzDemand) {
		this.dzDemand = dzDemand;
	}
	
	public String getDlDemand() {
		return dlDemand;
	}
	public void setDlDemand(String dlDemand) {
		this.dlDemand = dlDemand;
	}
	
	public String getDcRatedCapacity() {
		return dcRatedCapacity;
	}
	public void setDcRatedCapacity(String dcRatedCapacity) {
		this.dcRatedCapacity = dcRatedCapacity;
	}
	
	public String getDcRatedVoltage() {
		return dcRatedVoltage;
	}
	public void setDcRatedVoltage(String dcRatedVoltage) {
		this.dcRatedVoltage = dcRatedVoltage;
	}
	
	public String getRechargeableVoltageMax() {
		return rechargeableVoltageMax;
	}
	public void setRechargeableVoltageMax(String rechargeableVoltageMax) {
		this.rechargeableVoltageMax = rechargeableVoltageMax;
	}
	
	public String getRechargeableCurrentMax() {
		return rechargeableCurrentMax;
	}
	public void setRechargeableCurrentMax(String rechargeableCurrentMax) {
		this.rechargeableCurrentMax = rechargeableCurrentMax;
	}
	
	public Integer getRechargeableTemperatureMax() {
		return rechargeableTemperatureMax;
	}
	public void setRechargeableTemperatureMax(Integer rechargeableTemperatureMax) {
		this.rechargeableTemperatureMax = rechargeableTemperatureMax;
	}
	
	public String getRechargeableStatus() {
		return rechargeableStatus;
	}
	public void setRechargeableStatus(String rechargeableStatus) {
		this.rechargeableStatus = rechargeableStatus;
	}
	
	public String getDcVoltageNow() {
		return dcVoltageNow;
	}
	public void setDcVoltageNow(String dcVoltageNow) {
		this.dcVoltageNow = dcVoltageNow;
	}
	
	public String getVoltageOutMax() {
		return voltageOutMax;
	}
	public void setVoltageOutMax(String voltageOutMax) {
		this.voltageOutMax = voltageOutMax;
	}
	
	public String getVoltageOutMin() {
		return voltageOutMin;
	}
	public void setVoltageOutMin(String voltageOutMin) {
		this.voltageOutMin = voltageOutMin;
	}
	
	public String getCurrentOutMax() {
		return currentOutMax;
	}
	public void setCurrentOutMax(String currentOutMax) {
		this.currentOutMax = currentOutMax;
	}
	
	public String getCurrentOutMin() {
		return currentOutMin;
	}
	public void setCurrentOutMin(String currentOutMin) {
		this.currentOutMin = currentOutMin;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	

}
