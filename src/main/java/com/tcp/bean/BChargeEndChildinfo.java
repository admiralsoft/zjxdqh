package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 充电结束电价信息详情
 * @author code_generator
 */
@Table(name = "b_charge_end_childinfo")
public class BChargeEndChildinfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Column(name="id")
	private String id ; 

	/**  父键 **/
	@Column(name="parent_id")
	private String parentId ; 

	/** 开始时间 **/
	@Column(name="child_startime")
	private Date childStartime ;

	/** 结束时间 **/
	@Column(name="child_endtime")
	private Date childEndtime ;

	/** 电量 **/
	@Column(name="pow_total")
	private Double powTotal ;

	/** 服务费单价 **/
	@Column(name="charging_service_price")
	private Double chargingServicePrice ;

	/** 单价 **/
	@Column(name="price")
	private Double price ;

	/** 服务费金额(比例 0.01单位 元) **/
	@Column(name="charging_serviceamount")
	private Double chargingServiceamount ;

	/** 充电金额比例 0.01单位 元 **/
	@Column(name="charging_amount")
	private Double chargingAmount ;

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
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public Date getChildStartime() {
		return childStartime;
	}
	public void setChildStartime(Date childStartime) {
		this.childStartime = childStartime;
	}
	
	public Date getChildEndtime() {
		return childEndtime;
	}
	public void setChildEndtime(Date childEndtime) {
		this.childEndtime = childEndtime;
	}
	
	public Double getPowTotal() {
		return powTotal;
	}
	public void setPowTotal(Double powTotal) {
		this.powTotal = powTotal;
	}
	
	public Double getChargingServicePrice() {
		return chargingServicePrice;
	}
	public void setChargingServicePrice(Double chargingServicePrice) {
		this.chargingServicePrice = chargingServicePrice;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getChargingServiceamount() {
		return chargingServiceamount;
	}
	public void setChargingServiceamount(Double chargingServiceamount) {
		this.chargingServiceamount = chargingServiceamount;
	}
	
	public Double getChargingAmount() {
		return chargingAmount;
	}
	public void setChargingAmount(Double chargingAmount) {
		this.chargingAmount = chargingAmount;
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
