package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 运营平台发送控制启停命令
 * @author code_generator
 */
@Table(name = "m_control_start_stop")
public class MControlStartStop implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ;

	/** 枪号 **/
	@Column(name="gun_num")
	private String gunNum;

	/** 运营平台主动发送命令 1. 运营平台启动2. 运营平台停止3. 手机APP 启动4. 手机APP 停止 **/
	@Column(name="cmd")
	private byte cmd ; 

	/** 启动帐号 **/
	@Column(name="count")
	private String count ; 

	/** 账号余额 **/
	@Column(name="balance")
	private BigDecimal balance ; 

	/** 预付费余额 **/
	@Column(name="pre_balance")
	private BigDecimal preBalance ; 

	/** 免费余额 **/
	@Column(name="free_balance")
	private BigDecimal freeBalance ; 

	/** 订单号 **/
	@Column(name="order_num")
	private String orderNum ; 

	/** 辅助电源选项 1，12V 2，24V **/
	@Column(name="assist_power_option")
	private byte assistPowerOption ; 

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

	/** 执行状态**/
	@Column(name="status")
	private byte status;

	public String getGunNum() {
		return gunNum;
	}

	public void setGunNum(String gunNum) {
		this.gunNum = gunNum;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

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
	
	public byte getCmd() {
		return cmd;
	}
	public void setCmd(byte cmd) {
		this.cmd = cmd;
	}
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BigDecimal getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(BigDecimal preBalance) {
		this.preBalance = preBalance;
	}
	
	public BigDecimal getFreeBalance() {
		return freeBalance;
	}
	public void setFreeBalance(BigDecimal freeBalance) {
		this.freeBalance = freeBalance;
	}
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public byte getAssistPowerOption() {
		return assistPowerOption;
	}
	public void setAssistPowerOption(byte assistPowerOption) {
		this.assistPowerOption = assistPowerOption;
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
