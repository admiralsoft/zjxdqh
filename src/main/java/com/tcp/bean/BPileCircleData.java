package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 定时数据
 * @author code_generator
 */
@Table(name = "b_pile_circle_data")
public class BPileCircleData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 枪口号 **/
	@Column(name="gun_num")
	private byte gunNum ; 

	/** 管理状态:1. 正常运营状态2. 故障3. 离网(用于充电桩标记自己当前状态，无法上传到运营平台)4. 离网数据上传中5. 维护 **/
	@Column(name="manager_state")
	private byte managerState ; 

	/** 充电状态:1. 空闲2. 充电枪已连接，未启动充电3. 启动中(已发启动命令，等待充电枪连接 。或者启动充  电的过程都定义为启动中)4. 充电中5. 充电完成6. 已预约7. 等待充电中(预约已连接  车，但未启动充电状态) **/
	@Column(name="charging_state")
	private byte chargingState ; 

	/** 网络连接方式:0 有线网络 1无线网络 2未知连接 **/
	@Column(name="net_type")
	private byte netType ; 

	/** 网络信号 **/
	@Column(name="network")
	private byte network ; 

	/** 充电桩当前电压 **/
	@Column(name="pile_voltage")
	private String pileVoltage ; 

	/** 充电桩当前电流 **/
	@Column(name="pile_current")
	private String pileCurrent ; 

	/** 当前电表读数 **/
	@Column(name="ammeter_reading")
	private String ammeterReading ; 

	/** 充电桩内部温度 **/
	@Column(name="pile_temperature")
	private String pileTemperature ; 

	/** 充电枪温度 **/
	@Column(name="muzz_temperature")
	private String muzzTemperature ; 

	/** 输入A相电压 **/
	@Column(name="inputA")
	private String inputa ; 

	/** 输入B相电压 **/
	@Column(name="inputB")
	private String inputb ; 

	/** 输入C相电压 **/
	@Column(name="inputC")
	private String inputc ; 

	/** A相电流 **/
	@Column(name="flowA")
	private String flowa ; 

	/** B相电流 **/
	@Column(name="flowB")
	private String flowb ; 

	/** C相电流 **/
	@Column(name="flowC")
	private String flowc ; 

	/** 当前充电桩功率 **/
	@Column(name="charging_rate")
	private Integer chargingRate ; 

	/** 充电枪使用次数 **/
	@Column(name="gun_use_num")
	private Long gunUseNum ; 

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
	
	public byte getManagerState() {
		return managerState;
	}
	public void setManagerState(byte managerState) {
		this.managerState = managerState;
	}
	
	public byte getChargingState() {
		return chargingState;
	}
	public void setChargingState(byte chargingState) {
		this.chargingState = chargingState;
	}
	
	public byte getNetType() {
		return netType;
	}
	public void setNetType(byte netType) {
		this.netType = netType;
	}
	
	public byte getNetwork() {
		return network;
	}
	public void setNetwork(byte network) {
		this.network = network;
	}
	
	public String getPileVoltage() {
		return pileVoltage;
	}
	public void setPileVoltage(String pileVoltage) {
		this.pileVoltage = pileVoltage;
	}
	
	public String getPileCurrent() {
		return pileCurrent;
	}
	public void setPileCurrent(String pileCurrent) {
		this.pileCurrent = pileCurrent;
	}
	
	public String getAmmeterReading() {
		return ammeterReading;
	}
	public void setAmmeterReading(String ammeterReading) {
		this.ammeterReading = ammeterReading;
	}
	
	public String getPileTemperature() {
		return pileTemperature;
	}
	public void setPileTemperature(String pileTemperature) {
		this.pileTemperature = pileTemperature;
	}
	
	public String getMuzzTemperature() {
		return muzzTemperature;
	}
	public void setMuzzTemperature(String muzzTemperature) {
		this.muzzTemperature = muzzTemperature;
	}
	
	public String getInputa() {
		return inputa;
	}
	public void setInputa(String inputa) {
		this.inputa = inputa;
	}
	
	public String getInputb() {
		return inputb;
	}
	public void setInputb(String inputb) {
		this.inputb = inputb;
	}
	
	public String getInputc() {
		return inputc;
	}
	public void setInputc(String inputc) {
		this.inputc = inputc;
	}
	
	public String getFlowa() {
		return flowa;
	}
	public void setFlowa(String flowa) {
		this.flowa = flowa;
	}
	
	public String getFlowb() {
		return flowb;
	}
	public void setFlowb(String flowb) {
		this.flowb = flowb;
	}
	
	public String getFlowc() {
		return flowc;
	}
	public void setFlowc(String flowc) {
		this.flowc = flowc;
	}
	
	public Integer getChargingRate() {
		return chargingRate;
	}
	public void setChargingRate(Integer chargingRate) {
		this.chargingRate = chargingRate;
	}
	
	public Long getGunUseNum() {
		return gunUseNum;
	}
	public void setGunUseNum(Long gunUseNum) {
		this.gunUseNum = gunUseNum;
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
