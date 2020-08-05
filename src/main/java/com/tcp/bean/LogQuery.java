package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 查询记录
 * @author code_generator
 */
@Table(name = "log_query")
public class LogQuery implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 账号类型 **/
	@Column(name="account_type")
	private byte accountType ; 

	/** 卡号 **/
	@Column(name="car_id")
	private String carId ; 

	/** 账号 **/
	@Column(name="amount")
	private String amount ; 

	/** 充电电量 **/
	@Column(name="charge_num")
	private BigDecimal chargeNum ; 

	/** 开始时间 **/
	@Column(name="start_time")
	private Date startTime ; 

	/** 结束时间 **/
	@Column(name="end_time")
	private Date endTime ; 

	/** 起始soc **/
	@Column(name="start_soc")
	private Integer startSoc ; 

	/** 终止soc **/
	@Column(name="end_soc")
	private Integer endSoc ; 

	/** 起始充电时电表读数 **/
	@Column(name="start_ammeter_num")
	private BigDecimal startAmmeterNum ; 

	/** 终止充电时电表读数 **/
	@Column(name="end_ammeter_num")
	private BigDecimal endAmmeterNum ; 

	/** 记录信息 **/
	@Column(name="message")
	private String message ; 

	/** 记录类型 **/
	@Column(name="record_type")
	private byte recordType ; 


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public byte getAccountType() {
		return accountType;
	}
	public void setAccountType(byte accountType) {
		this.accountType = accountType;
	}
	
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public BigDecimal getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(BigDecimal chargeNum) {
		this.chargeNum = chargeNum;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getStartSoc() {
		return startSoc;
	}
	public void setStartSoc(Integer startSoc) {
		this.startSoc = startSoc;
	}
	
	public Integer getEndSoc() {
		return endSoc;
	}
	public void setEndSoc(Integer endSoc) {
		this.endSoc = endSoc;
	}
	
	public BigDecimal getStartAmmeterNum() {
		return startAmmeterNum;
	}
	public void setStartAmmeterNum(BigDecimal startAmmeterNum) {
		this.startAmmeterNum = startAmmeterNum;
	}
	
	public BigDecimal getEndAmmeterNum() {
		return endAmmeterNum;
	}
	public void setEndAmmeterNum(BigDecimal endAmmeterNum) {
		this.endAmmeterNum = endAmmeterNum;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public byte getRecordType() {
		return recordType;
	}
	public void setRecordType(byte recordType) {
		this.recordType = recordType;
	}
	

}
