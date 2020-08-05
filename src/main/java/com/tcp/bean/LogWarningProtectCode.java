package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 告警码及保护码
 * @author code_generator
 */
@Table(name = "log_warning_protect_code")
public class LogWarningProtectCode implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 枪口编号 **/
	@Column(name="gun_num")
	private byte gunNum ; 

	/** 告警码1 **/
	@Column(name="warning_code1")
	private String warningCode1 ; 

	/** 告警码2 **/
	@Column(name="warning_code2")
	private String warningCode2 ; 

	/** 保护码1 **/
	@Column(name="protect_code1")
	private String protectCode1 ; 

	/** 保护码2 **/
	@Column(name="protect_code2")
	private String protectCode2 ; 

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
	
	public String getWarningCode1() {
		return warningCode1;
	}
	public void setWarningCode1(String warningCode1) {
		this.warningCode1 = warningCode1;
	}
	
	public String getWarningCode2() {
		return warningCode2;
	}
	public void setWarningCode2(String warningCode2) {
		this.warningCode2 = warningCode2;
	}
	
	public String getProtectCode1() {
		return protectCode1;
	}
	public void setProtectCode1(String protectCode1) {
		this.protectCode1 = protectCode1;
	}
	
	public String getProtectCode2() {
		return protectCode2;
	}
	public void setProtectCode2(String protectCode2) {
		this.protectCode2 = protectCode2;
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
