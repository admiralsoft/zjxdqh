package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 离线数据信息
 * @author code_generator
 */
@Table(name = "b_offlineInfo")
public class BOfflineinfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 数据个数 **/
	@Column(name="data_num")
	private Integer dataNum ; 

	/** 记录序列号 **/
	@Column(name="serial_num")
	private Integer serialNum ; 

	/** 本序号数据长度 **/
	@Column(name="how_long")
	private Integer howLong ; 

	/** 枪口号 **/
	@Column(name="gun_num")
	private Integer gunNum ; 

	/** 充电账号类型:0.卡号 1.服务器账号 2.VIN启动 **/
	@Column(name="account_type")
	private Integer accountType ; 

	/** 卡号 **/
	@Column(name="cardNum")
	private Long cardnum ; 

	/** 账号 **/
	@Column(name="account")
	private String account ; 

	/** 车辆VIN **/
	@Column(name="vin")
	private String vin ; 

	/** 充电开始时间 **/
	@Column(name="charging_startime")
	private Date chargingStartime ;

	/** 充电结束时间 **/
	@Column(name="charging_endtime")
	private Date chargingEndtime ;

	/** 充电总电量 **/
	@Column(name="power_sum")
	private Double powerSum ;

	/** 充电服务费 **/
	@Column(name="power_servce")
	private BigDecimal powerServce ;

	/** 充电总金额 **/
	@Column(name="power_amount")
	private BigDecimal powerAmount ;

	/** 充电经过的时间段个数 **/
	@Column(name="charging_time_num")
	private Integer chargingTimeNum ; 

	/** 起始SOC **/
	@Column(name="star_soc")
	private Integer starSoc ;

	/** 终止SOC **/
	@Column(name="end_soc")
	private Integer endSoc ;

	/** 起始充电时电表读数 **/
	@Column(name="init_kilowatt")
	private Double initKilowatt ;

	/** 终止充电时电表读数 **/
	@Column(name="end_kilowatt")
	private Double endKilowatt ;

	/** 订单号 **/
	@Column(name="order_no")
	private String orderNo ; 

	/** 是否处理成功:0未处理,1已处理 **/
	@Column(name="state")
	private byte state ; 

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

	public Integer getDataNum() {
		return dataNum;
	}

	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}

	public Integer getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}

	public Integer getHowLong() {
		return howLong;
	}

	public void setHowLong(Integer howLong) {
		this.howLong = howLong;
	}

	public Integer getGunNum() {
		return gunNum;
	}

	public void setGunNum(Integer gunNum) {
		this.gunNum = gunNum;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Long getCardnum() {
		return cardnum;
	}

	public void setCardnum(Long cardnum) {
		this.cardnum = cardnum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Date getChargingStartime() {
		return chargingStartime;
	}

	public void setChargingStartime(Date chargingStartime) {
		this.chargingStartime = chargingStartime;
	}

	public Date getChargingEndtime() {
		return chargingEndtime;
	}

	public void setChargingEndtime(Date chargingEndtime) {
		this.chargingEndtime = chargingEndtime;
	}

	public Double getPowerSum() {
		return powerSum;
	}

	public void setPowerSum(Double powerSum) {
		this.powerSum = powerSum;
	}

	public BigDecimal getPowerServce() {
		return powerServce;
	}

	public void setPowerServce(BigDecimal powerServce) {
		this.powerServce = powerServce;
	}

	public BigDecimal getPowerAmount() {
		return powerAmount;
	}

	public void setPowerAmount(BigDecimal powerAmount) {
		this.powerAmount = powerAmount;
	}

	public Integer getChargingTimeNum() {
		return chargingTimeNum;
	}

	public void setChargingTimeNum(Integer chargingTimeNum) {
		this.chargingTimeNum = chargingTimeNum;
	}

	public Integer getStarSoc() {
		return starSoc;
	}

	public void setStarSoc(Integer starSoc) {
		this.starSoc = starSoc;
	}

	public Integer getEndSoc() {
		return endSoc;
	}

	public void setEndSoc(Integer endSoc) {
		this.endSoc = endSoc;
	}

	public Double getInitKilowatt() {
		return initKilowatt;
	}

	public void setInitKilowatt(Double initKilowatt) {
		this.initKilowatt = initKilowatt;
	}

	public Double getEndKilowatt() {
		return endKilowatt;
	}

	public void setEndKilowatt(Double endKilowatt) {
		this.endKilowatt = endKilowatt;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
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
