package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 电桩注册信息表
 * @author code_generator
 */
@Table(name = "t_pile_info")
public class TPileInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 设备类型:1. 直流快充 2. 直流慢充 3. 交流快充 4. 交流慢充 5. 交直流混合 **/
	@Column(name="type")
	private byte type ; 

	/** 额定功率 **/
	@Column(name="power")
	private Integer power ; 

	/** 额定电压 **/
	@Column(name="voltage")
	private Integer voltage ; 

	/** 经度 **/
	@Column(name="longitude")
	private BigDecimal longitude ; 

	/** 纬度 **/
	@Column(name="latitude")
	private BigDecimal latitude ; 

	/** 所属电站编号 **/
	@Column(name="power_station_num")
	private String powerStationNum ; 

	/** 所属地区编号 **/
	@Column(name="area_num")
	private String areaNum ; 

	/** 营运类型:1. 私有，不对外开放充电系统 2. 私有，对外开放充电系统 3. 公有免费充电系统4. 公有收费充电系统 **/
	@Column(name="operation_type")
	private byte operationType ; 

	/** 桩内编号 **/
	@Column(name="gun_num_in")
	private String gunNumIn ; 

	/** 枪口个数 **/
	@Column(name="muzzle_num")
	private Integer muzzleNum ; 

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
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	
	public Integer getVoltage() {
		return voltage;
	}
	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	
	public String getPowerStationNum() {
		return powerStationNum;
	}
	public void setPowerStationNum(String powerStationNum) {
		this.powerStationNum = powerStationNum;
	}
	
	public String getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}
	
	public byte getOperationType() {
		return operationType;
	}
	public void setOperationType(byte operationType) {
		this.operationType = operationType;
	}
	
	public String getGunNumIn() {
		return gunNumIn;
	}
	public void setGunNumIn(String gunNumIn) {
		this.gunNumIn = gunNumIn;
	}
	
	public Integer getMuzzleNum() {
		return muzzleNum;
	}
	public void setMuzzleNum(Integer muzzleNum) {
		this.muzzleNum = muzzleNum;
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
