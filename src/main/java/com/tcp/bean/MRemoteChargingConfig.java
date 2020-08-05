package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 主动远程配置系统
 * @author code_generator
 */
@Table(name = "m_remote_charging_config")
public class MRemoteChargingConfig implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 设备类型:1. 直流快充 2. 直流慢充 3. 交流快充 4. 交流慢充 5. 交直流混合,0.为不更改当前配置 **/
	@Column(name="pile_type")
	private Integer pileType ; 

	/** 经度:0不更改 **/
	@Column(name="pile_longitude")
	private BigDecimal pileLongitude ; 

	/** 纬度:0不更改 **/
	@Column(name="pile_latitude")
	private BigDecimal pileLatitude ; 

	/** 车位号:0不更改 **/
	@Column(name="carport_num")
	private Integer carportNum ; 

	/** 停车位号:0不更改 **/
	@Column(name="parking_num")
	private Integer parkingNum ; 

	/** 创建时间 **/
	@Column(name="create_time")
	private Date createTime ; 

	/** 修改时间 **/
	@Column(name="modify_time")
	private Date modifyTime ; 

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

	/** 所属电站编号:0不更改 **/
	@Column(name="power_station_nupower_station_num")
	private Integer powerStationNupowerStationNum ; 

	/** 所属地区编号:0不更改 **/
	@Column(name="area_num")
	private Integer areaNum ; 

	/** 营运类型:1. 私有，不对外开放充电系统 2. 私有，对外开放充电系统 3. 公有免费充电系统 4. 公有收费充电系统,0不更改 **/
	@Column(name="operation_type")
	private Integer operationType ; 

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
	
	public Integer getPileType() {
		return pileType;
	}
	public void setPileType(Integer pileType) {
		this.pileType = pileType;
	}
	
	public BigDecimal getPileLongitude() {
		return pileLongitude;
	}
	public void setPileLongitude(BigDecimal pileLongitude) {
		this.pileLongitude = pileLongitude;
	}
	
	public BigDecimal getPileLatitude() {
		return pileLatitude;
	}
	public void setPileLatitude(BigDecimal pileLatitude) {
		this.pileLatitude = pileLatitude;
	}
	
	public Integer getCarportNum() {
		return carportNum;
	}
	public void setCarportNum(Integer carportNum) {
		this.carportNum = carportNum;
	}
	
	public Integer getParkingNum() {
		return parkingNum;
	}
	public void setParkingNum(Integer parkingNum) {
		this.parkingNum = parkingNum;
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
	
	public Integer getPowerStationNupowerStationNum() {
		return powerStationNupowerStationNum;
	}
	public void setPowerStationNupowerStationNum(Integer powerStationNupowerStationNum) {
		this.powerStationNupowerStationNum = powerStationNupowerStationNum;
	}
	
	public Integer getAreaNum() {
		return areaNum;
	}
	public void setAreaNum(Integer areaNum) {
		this.areaNum = areaNum;
	}
	
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
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
