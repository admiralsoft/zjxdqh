package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 电桩注册信息详情表
 * @author code_generator
 */
@Table(name = "t_pile_info_childinfo")
public class TPileInfoChildinfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 父键 **/
	@Column(name="parent_id")
	private String parentId ; 

	/** 核对标识:枪口个数 **/
	@Column(name="muzzle_num")
	private Integer muzzleNum ; 

	/** 停车位号:枪又1或者分机1所占的车位号 **/
	@Column(name="parking_num")
	private Integer parkingNum ; 

	/** 充电枪口接口类型:1国标 2欧标3美标 4日标 **/
	@Column(name="gun_type")
	private byte gunType ; 

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
	
	public Integer getMuzzleNum() {
		return muzzleNum;
	}
	public void setMuzzleNum(Integer muzzleNum) {
		this.muzzleNum = muzzleNum;
	}
	
	public Integer getParkingNum() {
		return parkingNum;
	}
	public void setParkingNum(Integer parkingNum) {
		this.parkingNum = parkingNum;
	}
	
	public byte getGunType() {
		return gunType;
	}
	public void setGunType(byte gunType) {
		this.gunType = gunType;
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
