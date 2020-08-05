package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 车辆VIN验证回复信息
 * @author code_generator
 */
@Table(name = "log_vin_result")
public class LogVinResult implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 枪号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 1，验证成功 2，失败，⽆此VIN记录 3，失败，桩车绑定验证失败 **/
	@Column(name="verification_result")
	private byte verificationResult ; 

	/** 枪口号 **/
	@Column(name="gun_num")
	private Integer gunNum ; 

	/** 订单号 **/
	@Column(name="order_num")
	private String orderNum ; 

	/** 充电金额 **/
	@Column(name="recharge_amount")
	private BigDecimal rechargeAmount ; 

	/** 删除状态 **/
	@Column(name="deleted")
	private byte deleted ; 


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
	
	public byte getVerificationResult() {
		return verificationResult;
	}
	public void setVerificationResult(byte verificationResult) {
		this.verificationResult = verificationResult;
	}
	
	public Integer getGunNum() {
		return gunNum;
	}
	public void setGunNum(Integer gunNum) {
		this.gunNum = gunNum;
	}
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	
	public byte getDeleted() {
		return deleted;
	}
	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}
	

}
