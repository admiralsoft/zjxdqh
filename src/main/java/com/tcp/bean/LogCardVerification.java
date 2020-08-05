package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 刷卡验证
 * @author code_generator
 */
@Table(name = "log_card_verification")
public class LogCardVerification implements java.io.Serializable{
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

	/** 卡号 **/
	@Column(name="card_num")
	private String cardNum ; 

	/** 卡外编号 **/
	@Column(name="card_out_num")
	private String cardOutNum ; 

	/** 卡密码:没有此值填0，为卡用户 密码，可更改 **/
	@Column(name="card_password")
	private String cardPassword ; 

	/** 车辆VIN **/
	@Column(name="vin")
	private String vin ; 

	/** 充电方式:0，默认充满 1，按电量充 2，按金额充 3，按时间充 **/
	@Column(name="charging_type")
	private byte chargingType ; 

	/** 充电方式参数 **/
	@Column(name="charging_type_value")
	private BigDecimal chargingTypeValue ;

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
	
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public String getCardOutNum() {
		return cardOutNum;
	}
	public void setCardOutNum(String cardOutNum) {
		this.cardOutNum = cardOutNum;
	}
	
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public byte getChargingType() {
		return chargingType;
	}
	public void setChargingType(byte chargingType) {
		this.chargingType = chargingType;
	}

	public BigDecimal getChargingTypeValue() {
		return chargingTypeValue;
	}

	public void setChargingTypeValue(BigDecimal chargingTypeValue) {
		this.chargingTypeValue = chargingTypeValue;
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
