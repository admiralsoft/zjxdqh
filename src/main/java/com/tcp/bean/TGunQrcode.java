package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 枪口二维码数据
 * @author code_generator
 */
@Table(name = "t_gun_qrcode")
public class TGunQrcode implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 枪口号 **/
	@Column(name="gun_id")
	private Integer gunId ; 

	/** 设置充电桩枪⼜1⼆维码长度 **/
	@Column(name="gun_num_qrcode_length")
	private Integer gunNumQrcodeLength ; 

	/** 枪⼜1⼆维码数据 **/
	@Column(name="gun_num_qrcode_result")
	private String gunNumQrcodeResult ; 

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


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getGunId() {
		return gunId;
	}
	public void setGunId(Integer gunId) {
		this.gunId = gunId;
	}
	
	public Integer getGunNumQrcodeLength() {
		return gunNumQrcodeLength;
	}
	public void setGunNumQrcodeLength(Integer gunNumQrcodeLength) {
		this.gunNumQrcodeLength = gunNumQrcodeLength;
	}
	
	public String getGunNumQrcodeResult() {
		return gunNumQrcodeResult;
	}
	public void setGunNumQrcodeResult(String gunNumQrcodeResult) {
		this.gunNumQrcodeResult = gunNumQrcodeResult;
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
