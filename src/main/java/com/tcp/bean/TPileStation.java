package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 充电站
 * @author code_generator
 */
@Table(name = "t_pile_station")
public class TPileStation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 电站id **/
	@Id
	private String stationId ; 

	/** 站名 **/
	@Column(name="name")
	private String name ; 

	/** 电站地址 **/
	@Column(name="address")
	private String address ; 

	/** 所属城市 **/
	@Column(name="city")
	private String city ; 

	/** 快充次数 **/
	@Column(name="quick_charge")
	private byte quickCharge ; 

	/** 慢充次数 **/
	@Column(name="slow_charge")
	private byte slowCharge ; 

	/** 停车费 **/
	@Column(name="parking_pay")
	private BigDecimal parkingPay ; 

	/** 电费模板 **/
	@Column(name="template_pay")
	private String templatePay ; 

	/** 状态 **/
	@Column(name="status")
	private byte status ; 

	/** 联系人 **/
	@Column(name="linkman")
	private String linkman ; 

	/** 联系电话 **/
	@Column(name="link_phone")
	private String linkPhone ; 

	/** 建站状态 **/
	@Column(name="build_status")
	private byte buildStatus ; 

	/** 审核状态 **/
	@Column(name="audit_status")
	private byte auditStatus ; 


	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public byte getQuickCharge() {
		return quickCharge;
	}
	public void setQuickCharge(byte quickCharge) {
		this.quickCharge = quickCharge;
	}
	
	public byte getSlowCharge() {
		return slowCharge;
	}
	public void setSlowCharge(byte slowCharge) {
		this.slowCharge = slowCharge;
	}
	
	public BigDecimal getParkingPay() {
		return parkingPay;
	}
	public void setParkingPay(BigDecimal parkingPay) {
		this.parkingPay = parkingPay;
	}
	
	public String getTemplatePay() {
		return templatePay;
	}
	public void setTemplatePay(String templatePay) {
		this.templatePay = templatePay;
	}
	
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	
	public byte getBuildStatus() {
		return buildStatus;
	}
	public void setBuildStatus(byte buildStatus) {
		this.buildStatus = buildStatus;
	}
	
	public byte getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(byte auditStatus) {
		this.auditStatus = auditStatus;
	}
	

}
