package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 *  运营平台发送充电桩软件升级命令 数据包形式升级
 * @author code_generator
 */
@Table(name = "m_upgrade_result")
public class MUpgradeResult implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 升级指⽰ 1，进⼊升级模式 2，升级数据  3，数据包下发完毕 **/
	@Column(name="upgrade_type")
	private byte upgradeType ; 

	/** 软件版本号 **/
	@Column(name="version")
	private String version ; 

	/** 总数据长度 **/
	@Column(name="result_length")
	private String resultLength ; 

	/** 总数据包数 **/
	@Column(name="result_num")
	private String resultNum ; 

	/** 已发送数据长度 **/
	@Column(name="sent_result_length")
	private String sentResultLength ; 

	/** 当前包数 **/
	@Column(name="now_result_num")
	private String nowResultNum ; 

	/** 升级地址 **/
	@Column(name="upgrade_local")
	private String upgradeLocal ; 

	/** 升级数据长度 **/
	@Column(name="upgrade_result_length")
	private String upgradeResultLength ; 

	/** 升级数据 **/
	@Column(name="uograde_result")
	private String uogradeResult ; 

	/** ip **/
	@Column(name="ip")
	private String ip ; 

	/** 版本信息 **/
	@Column(name="version_information")
	private String versionInformation ; 

	/** 命令 **/
	@Column(name="command")
	private Integer command ; 

	/** 传输时间戳 **/
	@Column(name="m_time")
	private Long mTime ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

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


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public byte getUpgradeType() {
		return upgradeType;
	}
	public void setUpgradeType(byte upgradeType) {
		this.upgradeType = upgradeType;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getResultLength() {
		return resultLength;
	}
	public void setResultLength(String resultLength) {
		this.resultLength = resultLength;
	}
	
	public String getResultNum() {
		return resultNum;
	}
	public void setResultNum(String resultNum) {
		this.resultNum = resultNum;
	}
	
	public String getSentResultLength() {
		return sentResultLength;
	}
	public void setSentResultLength(String sentResultLength) {
		this.sentResultLength = sentResultLength;
	}
	
	public String getNowResultNum() {
		return nowResultNum;
	}
	public void setNowResultNum(String nowResultNum) {
		this.nowResultNum = nowResultNum;
	}
	
	public String getUpgradeLocal() {
		return upgradeLocal;
	}
	public void setUpgradeLocal(String upgradeLocal) {
		this.upgradeLocal = upgradeLocal;
	}
	
	public String getUpgradeResultLength() {
		return upgradeResultLength;
	}
	public void setUpgradeResultLength(String upgradeResultLength) {
		this.upgradeResultLength = upgradeResultLength;
	}
	
	public String getUogradeResult() {
		return uogradeResult;
	}
	public void setUogradeResult(String uogradeResult) {
		this.uogradeResult = uogradeResult;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getVersionInformation() {
		return versionInformation;
	}
	public void setVersionInformation(String versionInformation) {
		this.versionInformation = versionInformation;
	}
	
	public Integer getCommand() {
		return command;
	}
	public void setCommand(Integer command) {
		this.command = command;
	}
	
	public Long getMTime() {
		return mTime;
	}
	public void setMTime(Long mTime) {
		this.mTime = mTime;
	}
	
	public String getPileNum() {
		return pileNum;
	}
	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
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
