package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 充电枪信息
 * @author code_generator
 */
@Table(name = "t_gun")
public class TGun implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/**  **/
	@Id
	private String gunId ; 

	/**  **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 枪类型1-国标，2-欧标，3-美标，4-日标 **/
	@Column(name="gun_type")
	private Integer gunType ; 

	/** 1-正常，2-故障，3-离网，4-离线数据上传中，5-维护 **/
	@Column(name="gun_admin_state")
	private Integer gunAdminState ; 

	/** 充电状态：1-空闲，2-充电枪已连接，3-启动中，4-充电中，5-充电完成，6-已预约，7等待充电预约已插枪 **/
	@Column(name="gun_charging_state")
	private Integer gunChargingState ; 

	/** 充电枪温度比例0.1单位度 **/
	@Column(name="gun_temp")
	private Integer gunTemp ; 

	/** 使用次数 **/
	@Column(name="gun_use_num")
	private Integer gunUseNum ; 

	/** 当前功率 **/
	@Column(name="gun_power")
	private Integer gunPower ; 

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

	/**
	 * 枪号
	 */
	@Column(name="gun_num")
	private String gunNum ;


	public String getGunId() {
		return gunId;
	}
	public void setGunId(String gunId) {
		this.gunId = gunId;
	}
	
	public String getPileNum() {
		return pileNum;
	}
	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
	}
	
	public Integer getGunType() {
		return gunType;
	}
	public void setGunType(Integer gunType) {
		this.gunType = gunType;
	}
	
	public Integer getGunAdminState() {
		return gunAdminState;
	}
	public void setGunAdminState(Integer gunAdminState) {
		this.gunAdminState = gunAdminState;
	}
	
	public Integer getGunChargingState() {
		return gunChargingState;
	}
	public void setGunChargingState(Integer gunChargingState) {
		this.gunChargingState = gunChargingState;
	}
	
	public Integer getGunTemp() {
		return gunTemp;
	}
	public void setGunTemp(Integer gunTemp) {
		this.gunTemp = gunTemp;
	}
	
	public Integer getGunUseNum() {
		return gunUseNum;
	}
	public void setGunUseNum(Integer gunUseNum) {
		this.gunUseNum = gunUseNum;
	}
	
	public Integer getGunPower() {
		return gunPower;
	}
	public void setGunPower(Integer gunPower) {
		this.gunPower = gunPower;
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


	public String getGunNum() {
		return gunNum;
	}

	public void setGunNum(String gunNum) {
		this.gunNum = gunNum;
	}
}
