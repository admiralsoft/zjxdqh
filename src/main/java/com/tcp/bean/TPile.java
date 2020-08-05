package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 充电桩信息
 * @author code_generator
 */
@Table(name = "t_pile")
public class TPile implements java.io.Serializable{

	/**
	 * 上线
	 */
	public static final int SIGN_ONLINE = 1;
	/**
	 * 离线
	 */
	public static final int SIGN_OFFLINE = 2;
	/**
	 * 故障
	 */
	public static final int SIGN_FAULT = -1;

	private static final long serialVersionUID = 1L;

	/** 桩号 **/
	@Id
	private String pileNum ; 

	/** 1直流快充，2直流慢充，3交流快充，4交流慢充，5交直流混合 **/
	@Column(name="pile_type")
	private Integer pileType ; 

	/** 功率1单位w **/
	@Column(name="pile_power")
	private Integer pilePower ; 

	/** 电压比例0.1单位V **/
	@Column(name="pile_voltage")
	private Integer pileVoltage ; 

	/** 经度 **/
	@Column(name="pile_longitude")
	private BigDecimal pileLongitude ; 

	/** 纬度 **/
	@Column(name="pile_latitude")
	private BigDecimal pileLatitude ; 

	/** 相系：1-单相，2-三相 **/
	@Column(name="pile_phase_system")
	private Integer pilePhaseSystem ; 

	/** 最大输出电流 **/
	@Column(name="pile_max_output")
	private Integer pileMaxOutput ; 

	/** 离网个数 **/
	@Column(name="pile_offnet_num")
	private Integer pileOffnetNum ; 

	/** 可用枪个数 **/
	@Column(name="pile_usable_gun_num")
	private Integer pileUsableGunNum ; 

	/** 是否支持预约：1-支持，2-不支持 **/
	@Column(name="pile_is_pre")
	private Integer pileIsPre ; 

	/** 屏幕登录帐号 **/
	@Column(name="pile_signin_account")
	private String pileSigninAccount ; 

	/** 屏幕登录密码 **/
	@Column(name="pile_signin_password")
	private String pileSigninPassword ; 

	/** 心调时间比例1单位s **/
	@Column(name="pile_heartbeat_time")
	private Integer pileHeartbeatTime ; 

	/** 服务器ip **/
	@Column(name="pile_service_ip")
	private String pileServiceIp ; 

	/** 服务器端口 **/
	@Column(name="pile_service_port")
	private Integer pileServicePort ; 

	/** 硬件版本 **/
	@Column(name="pile_hardware_version")
	private String pileHardwareVersion ; 

	/** 软件版本 **/
	@Column(name="pile_software_version")
	private String pileSoftwareVersion ; 

	/** 报文密钥 **/
	@Column(name="pile_key")
	private String pileKey ; 

	/** 1-私有不开放，2-私有开放，3-公有免费，4-公有收费 **/
	@Column(name="pile_operate_type")
	private Integer pileOperateType ; 

	/** 1-已经注册入网，2-未注册入网 **/
	@Column(name="pile_signin_state")
	private Integer pileSigninState ; 

	/** 0-不进行监听1-监听接收报文2-发送报文3-全部监听 **/
	@Column(name="pile_is_message")
	private Integer pileIsMessage ; 

	/** 电站id **/
	@Column(name="station_id")
	private String stationId ; 

	/** 创建日期 **/
	@Column(name="creation_time")
	private Date creationTime ; 

	private Integer	 isMessage;

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

	/** 创建时间 **/
	@Column(name="create_time")
	private Date createTime;

	/** 修改时间 **/
	@Column(name="modify_time")
	private Date modifyTime;


    private Date lastOfflineTime;
    private Date lastOnlineTime;

	/** **/
	private byte buildStatus = 0;

	/** **/
	private byte auditStatus = 0;

	/** 厂商id **/
	@Column(name="fac_id")
	private String facId;

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getPileNum() {
		return pileNum;
	}
	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
	}
	
	public Integer getPileType() {
		return pileType;
	}
	public void setPileType(Integer pileType) {
		this.pileType = pileType;
	}
	
	public Integer getPilePower() {
		return pilePower;
	}
	public void setPilePower(Integer pilePower) {
		this.pilePower = pilePower;
	}
	
	public Integer getPileVoltage() {
		return pileVoltage;
	}
	public void setPileVoltage(Integer pileVoltage) {
		this.pileVoltage = pileVoltage;
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
	
	public Integer getPilePhaseSystem() {
		return pilePhaseSystem;
	}
	public void setPilePhaseSystem(Integer pilePhaseSystem) {
		this.pilePhaseSystem = pilePhaseSystem;
	}
	
	public Integer getPileMaxOutput() {
		return pileMaxOutput;
	}
	public void setPileMaxOutput(Integer pileMaxOutput) {
		this.pileMaxOutput = pileMaxOutput;
	}
	
	public Integer getPileOffnetNum() {
		return pileOffnetNum;
	}
	public void setPileOffnetNum(Integer pileOffnetNum) {
		this.pileOffnetNum = pileOffnetNum;
	}
	
	public Integer getPileUsableGunNum() {
		return pileUsableGunNum;
	}
	public void setPileUsableGunNum(Integer pileUsableGunNum) {
		this.pileUsableGunNum = pileUsableGunNum;
	}
	
	public Integer getPileIsPre() {
		return pileIsPre;
	}
	public void setPileIsPre(Integer pileIsPre) {
		this.pileIsPre = pileIsPre;
	}
	
	public String getPileSigninAccount() {
		return pileSigninAccount;
	}
	public void setPileSigninAccount(String pileSigninAccount) {
		this.pileSigninAccount = pileSigninAccount;
	}
	
	public String getPileSigninPassword() {
		return pileSigninPassword;
	}
	public void setPileSigninPassword(String pileSigninPassword) {
		this.pileSigninPassword = pileSigninPassword;
	}
	
	public Integer getPileHeartbeatTime() {
		return pileHeartbeatTime;
	}
	public void setPileHeartbeatTime(Integer pileHeartbeatTime) {
		this.pileHeartbeatTime = pileHeartbeatTime;
	}
	
	public String getPileServiceIp() {
		return pileServiceIp;
	}
	public void setPileServiceIp(String pileServiceIp) {
		this.pileServiceIp = pileServiceIp;
	}
	
	public Integer getPileServicePort() {
		return pileServicePort;
	}
	public void setPileServicePort(Integer pileServicePort) {
		this.pileServicePort = pileServicePort;
	}
	
	public String getPileHardwareVersion() {
		return pileHardwareVersion;
	}
	public void setPileHardwareVersion(String pileHardwareVersion) {
		this.pileHardwareVersion = pileHardwareVersion;
	}
	
	public String getPileSoftwareVersion() {
		return pileSoftwareVersion;
	}
	public void setPileSoftwareVersion(String pileSoftwareVersion) {
		this.pileSoftwareVersion = pileSoftwareVersion;
	}
	
	public String getPileKey() {
		return pileKey;
	}
	public void setPileKey(String pileKey) {
		this.pileKey = pileKey;
	}
	
	public Integer getPileOperateType() {
		return pileOperateType;
	}
	public void setPileOperateType(Integer pileOperateType) {
		this.pileOperateType = pileOperateType;
	}
	
	public Integer getPileSigninState() {
		return pileSigninState;
	}
	public void setPileSigninState(Integer pileSigninState) {
		this.pileSigninState = pileSigninState;
	}
	
	public Integer getPileIsMessage() {
		return pileIsMessage;
	}
	public void setPileIsMessage(Integer pileIsMessage) {
		this.pileIsMessage = pileIsMessage;
	}
	
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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

	public Integer getIsMessage() {
		return isMessage;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
	}

	public Date getLastOfflineTime() {
		return lastOfflineTime;
	}

	public void setLastOfflineTime(Date lastOfflineTime) {
		this.lastOfflineTime = lastOfflineTime;
	}

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }
}
