package com.tcp.bean;

import java.util.Date;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 运营平台发送预约命令
 * @author code_generator
 */
@Table(name = "m_pre_command")
public class MPreCommand implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号号 **/
	@Column(name="pill_num")
	private String pillNum ;

	/*枪号*/
	@Column(name="gunNum")
	private int gunNum;

	/** 预约 1. 开始预约 2. 停止预约 **/
	@Column(name="pre_type")
	private byte preType ; 

	/** 预约账号 **/
	@Column(name="pre_account")
	private String preAccount ; 

	/** 预约开始时间 **/
	@Column(name="pre_start_time")
	private Date preStartTime ; 

	/** 预约结束时间 **/
	@Column(name="pre_end_time")
	private Date preEndTime ; 

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

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getGunNum() {
		return gunNum;
	}

	public void setGunNum(int gunNum) {
		this.gunNum = gunNum;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPillNum() {
		return pillNum;
	}
	public void setPillNum(String pillNum) {
		this.pillNum = pillNum;
	}
	
	public byte getPreType() {
		return preType;
	}
	public void setPreType(byte preType) {
		this.preType = preType;
	}
	
	public String getPreAccount() {
		return preAccount;
	}
	public void setPreAccount(String preAccount) {
		this.preAccount = preAccount;
	}
	
	public Date getPreStartTime() {
		return preStartTime;
	}
	public void setPreStartTime(Date preStartTime) {
		this.preStartTime = preStartTime;
	}
	
	public Date getPreEndTime() {
		return preEndTime;
	}
	public void setPreEndTime(Date preEndTime) {
		this.preEndTime = preEndTime;
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
