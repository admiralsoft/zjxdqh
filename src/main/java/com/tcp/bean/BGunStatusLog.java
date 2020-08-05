package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 状态改变信息
 * @author code_generator
 */
@Table(name = "b_gun_status_log")
public class BGunStatusLog implements java.io.Serializable{
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

	/** 充电状态:1. 空闲2. 充电枪已连接，未启动充电3. 启动中(已发启动命令，等待充电枪连接 。 或者启动充电的过程都 定义为启动中)4. 充电中5. 充电完成6. 已预约7. 等待充电中(预约已连接车，但未启动充电状态) **/
	@Column(name="charging_state")
	private byte chargingState ; 

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
