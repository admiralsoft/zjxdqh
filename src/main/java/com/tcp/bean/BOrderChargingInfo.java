package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 充电订单信息表
 * @author code_generator
 */
@Table(name = "b_order_charging_info")
public class BOrderChargingInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final byte START_CHARGE = 1;
	public static final byte STOP_CHARGE = 0;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 启动枪口号 **/
	@Column(name="gun_num")
	private byte gunNum ; 

	/** 1. 充电系统启动2. 运营平台发送启动3. 其他方式启动4. 刷卡启动5. VIN启动 **/
	@Column(name="star_type")
	private byte starType ; 

	/** 1. 充满停止2. 主动停止3. 枪连接断开停止4. 故障停止(详情查看故障信息)。5. 异常停止6. 余额不足停止7. 失电停止 **/
	@Column(name="end_type")
	private byte endType ; 

	/** 启动账号 **/
	@Column(name="star_account")
	private String starAccount ; 

	/** 账号余额 **/
	@Column(name="account_balance")
	private BigDecimal accountBalance ; 

	/** 预付费余额 **/
	@Column(name="prepay_balance")
	private BigDecimal prepayBalance ; 

	/** 免费余额 **/
	@Column(name="free_balance")
	private BigDecimal freeBalance ; 

	/** 当前电表读数 **/
	@Column(name="kilowatt_now")
	private BigDecimal kilowattNow ; 

	/** 订单号 **/
	@Column(name="order_no")
	private String orderNo ; 

	/** 订单类型:0停止单,1启动单 **/
	@Column(name="order_type")
	private byte orderType ; 

	/** 创建时间 **/
	@Column(name="create_time")
	private Date createTime ; 

	/** 修改时间 **/
	@Column(name="modify_time")
	private Date modifyTime ; 

	/** 已充电量**/
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
	
	public byte getStarType() {
		return starType;
	}
	public void setStarType(byte starType) {
		this.starType = starType;
	}
	
	public byte getEndType() {
		return endType;
	}
	public void setEndType(byte endType) {
		this.endType = endType;
	}
	
	public String getStarAccount() {
		return starAccount;
	}
	public void setStarAccount(String starAccount) {
		this.starAccount = starAccount;
	}
	
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public BigDecimal getPrepayBalance() {
		return prepayBalance;
	}
	public void setPrepayBalance(BigDecimal prepayBalance) {
		this.prepayBalance = prepayBalance;
	}
	
	public BigDecimal getFreeBalance() {
		return freeBalance;
	}
	public void setFreeBalance(BigDecimal freeBalance) {
		this.freeBalance = freeBalance;
	}
	
	public BigDecimal getKilowattNow() {
		return kilowattNow;
	}
	public void setKilowattNow(BigDecimal kilowattNow) {
		this.kilowattNow = kilowattNow;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public byte getOrderType() {
		return orderType;
	}
	public void setOrderType(byte orderType) {
		this.orderType = orderType;
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
