package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 充电数据表
 * @author code_generator
 */
@Table(name = "b_charging_info")
public class BChargingInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 枪号 **/
	@Column(name="gun_num")
	private byte gunNum ; 

	/** 1. 直流 2. 交流 **/
	@Column(name="charging_type")
	private byte chargingType ; 

	/** 输出交流A相电压 **/
	@Column(name="outputA")
	private String outputa ; 

	/** 输出交流B相电压 **/
	@Column(name="outputB")
	private String outputb ; 

	/** 输出交流C相电压 **/
	@Column(name="outputC")
	private String outputc ; 

	/** 交流A相电流 **/
	@Column(name="flowA")
	private String flowa ; 

	/** 交流B相电流 **/
	@Column(name="flowB")
	private String flowb ; 

	/** 交流C相电流 **/
	@Column(name="flowC")
	private String flowc ; 

	/** 充电直流电压 **/
	@Column(name="dc_voltage")
	private String dcVoltage ; 

	/** 充电直流电流 **/
	@Column(name="d_current")
	private String dCurrent ; 

	/** 当前充电桩功率 **/
	@Column(name="charging_rate")
	private Integer chargingRate ; 

	/** 已充电量 **/
	@Column(name="use_power")
	private String usePower ; 

	/** 当前车辆SOC **/
	@Column(name="soc")
	private String soc ; 

	/** 车辆VIN号 **/
	@Column(name="vin")
	private String vin ; 

	/** 当前电表读数 **/
	@Column(name="ammeter_reading")
	private String ammeterReading ; 

	/** 已充时长 **/
	@Column(name="when_long")
	private Integer whenLong ; 

	/** 估算剩余充电时间 **/
	@Column(name="surplus_when_long")
	private String surplusWhenLong ; 

	/** 充电桩内部温度 **/
	@Column(name="pile_temperature")
	private String pileTemperature ; 

	/** 充电枪温度 **/
	@Column(name="muzz_temperature")
	private String muzzTemperature ; 

	/** 单节电池最高温度号 **/
	@Column(name="dc_temperature_num")
	private byte dcTemperatureNum ; 

	/** 单节电池最高温度 **/
	@Column(name="dcTemperature")
	private String dctemperature ; 

	/** 单节电池最高电压号 **/
	@Column(name="dcdy_num")
	private byte dcdyNum ; 

	/** 单节电池最高电压 **/
	@Column(name="dcdy")
	private String dcdy ; 

	/** 电池最低电压号 **/
	@Column(name="dcdy_min_num")
	private byte dcdyMinNum ; 

	/** 单节电池最低电压 **/
	@Column(name="dcdy_min")
	private String dcdyMin ; 

	/** 允许充电最高电压 **/
	@Column(name="yxcdzgdy")
	private String yxcdzgdy ; 

	/** 允许充电单节最高电压 **/
	@Column(name="yxcddjzgdy")
	private String yxcddjzgdy ; 

	/** 允许充电单节最高温度 **/
	@Column(name="yxcddjzgwd")
	private String yxcddjzgwd ; 

	/** 充电账号类型:0.卡号 1.服务器账号 2.VIN启动 **/
	@Column(name="accounts_type")
	private byte accountsType ; 

	/** 卡号:充电账号类型为 卡号时 读取此数 据 **/
	@Column(name="card_num")
	private String cardNum ; 

	/** 账号:充电账号为服务器账号时读取次数 据 **/
	@Column(name="charging_accounts")
	private String chargingAccounts ; 

	/** 订单号 **/
	@Column(name="order_num")
	private String orderNum ; 

	/** 账号实时余额 **/
	@Column(name="balance")
	private String balance ; 

	/** 预付费余额 **/
	@Column(name="prepaid_balance")
	private String prepaidBalance ; 

	/** 免费余额 **/
	@Column(name="free_balance")
	private String freeBalance ; 

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
	
	public byte getChargingType() {
		return chargingType;
	}
	public void setChargingType(byte chargingType) {
		this.chargingType = chargingType;
	}
	
	public String getOutputa() {
		return outputa;
	}
	public void setOutputa(String outputa) {
		this.outputa = outputa;
	}
	
	public String getOutputb() {
		return outputb;
	}
	public void setOutputb(String outputb) {
		this.outputb = outputb;
	}
	
	public String getOutputc() {
		return outputc;
	}
	public void setOutputc(String outputc) {
		this.outputc = outputc;
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
	
	public String getDcVoltage() {
		return dcVoltage;
	}
	public void setDcVoltage(String dcVoltage) {
		this.dcVoltage = dcVoltage;
	}
	
	public String getDCurrent() {
		return dCurrent;
	}
	public void setDCurrent(String dCurrent) {
		this.dCurrent = dCurrent;
	}
	
	public Integer getChargingRate() {
		return chargingRate;
	}
	public void setChargingRate(Integer chargingRate) {
		this.chargingRate = chargingRate;
	}
	
	public String getUsePower() {
		return usePower;
	}
	public void setUsePower(String usePower) {
		this.usePower = usePower;
	}
	
	public String getSoc() {
		return soc;
	}
	public void setSoc(String soc) {
		this.soc = soc;
	}
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getAmmeterReading() {
		return ammeterReading;
	}
	public void setAmmeterReading(String ammeterReading) {
		this.ammeterReading = ammeterReading;
	}
	
	public Integer getWhenLong() {
		return whenLong;
	}
	public void setWhenLong(Integer whenLong) {
		this.whenLong = whenLong;
	}
	
	public String getSurplusWhenLong() {
		return surplusWhenLong;
	}
	public void setSurplusWhenLong(String surplusWhenLong) {
		this.surplusWhenLong = surplusWhenLong;
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
	
	public byte getDcTemperatureNum() {
		return dcTemperatureNum;
	}
	public void setDcTemperatureNum(byte dcTemperatureNum) {
		this.dcTemperatureNum = dcTemperatureNum;
	}
	
	public String getDctemperature() {
		return dctemperature;
	}
	public void setDctemperature(String dctemperature) {
		this.dctemperature = dctemperature;
	}
	
	public byte getDcdyNum() {
		return dcdyNum;
	}
	public void setDcdyNum(byte dcdyNum) {
		this.dcdyNum = dcdyNum;
	}
	
	public String getDcdy() {
		return dcdy;
	}
	public void setDcdy(String dcdy) {
		this.dcdy = dcdy;
	}
	
	public byte getDcdyMinNum() {
		return dcdyMinNum;
	}
	public void setDcdyMinNum(byte dcdyMinNum) {
		this.dcdyMinNum = dcdyMinNum;
	}
	
	public String getDcdyMin() {
		return dcdyMin;
	}
	public void setDcdyMin(String dcdyMin) {
		this.dcdyMin = dcdyMin;
	}
	
	public String getYxcdzgdy() {
		return yxcdzgdy;
	}
	public void setYxcdzgdy(String yxcdzgdy) {
		this.yxcdzgdy = yxcdzgdy;
	}
	
	public String getYxcddjzgdy() {
		return yxcddjzgdy;
	}
	public void setYxcddjzgdy(String yxcddjzgdy) {
		this.yxcddjzgdy = yxcddjzgdy;
	}
	
	public String getYxcddjzgwd() {
		return yxcddjzgwd;
	}
	public void setYxcddjzgwd(String yxcddjzgwd) {
		this.yxcddjzgwd = yxcddjzgwd;
	}
	
	public byte getAccountsType() {
		return accountsType;
	}
	public void setAccountsType(byte accountsType) {
		this.accountsType = accountsType;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public String getChargingAccounts() {
		return chargingAccounts;
	}
	public void setChargingAccounts(String chargingAccounts) {
		this.chargingAccounts = chargingAccounts;
	}
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public String getPrepaidBalance() {
		return prepaidBalance;
	}
	public void setPrepaidBalance(String prepaidBalance) {
		this.prepaidBalance = prepaidBalance;
	}
	
	public String getFreeBalance() {
		return freeBalance;
	}
	public void setFreeBalance(String freeBalance) {
		this.freeBalance = freeBalance;
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
