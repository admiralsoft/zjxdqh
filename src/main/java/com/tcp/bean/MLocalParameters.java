package com.tcp.bean;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

import java.util.Date;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 运营平台发送远程配置充电设备本地参数命令
 * @author code_generator
 */
@Table(name = "m_local_parameters")
public class MLocalParameters implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 相系 **/
	@Column(name="phase_system")
	@Data(byteType = DataByteTypEnum.Byte)
	private byte phaseSystem ; 

	/** 设置最大输出电流 配置值 0 为不更改当前配置 **/
	@Column(name="max_output")
	@Data(order = 1, byteLen = 2, byteType = DataByteTypEnum.Number)
	private Integer maxOutput ; 

	/** 设置充电桩⼯作模式 1. 恒压模式 2. 恒流模式 **/
	@Column(name="pile_work_pattern")
	@Data(order = 2, byteType = DataByteTypEnum.Byte)
	private byte pileWorkPattern ; 

	/** 设置是否允许离⽹充电 1，允许离⽹充电 2，禁⽌离⽹充电 配置值 0 为不更改当前配置 **/
	@Column(name="off_net_charge")
	@Data(order = 3, byteType = DataByteTypEnum.Byte)
	private byte offNetCharge ; 

	/** 设置离⽹充电记录个数 **/
	@Column(name="off_net_charge_num")
	@Data(order = 4,byteLen = 2,byteType = DataByteTypEnum.Number)
	private Integer offNetChargeNum ; 

	/** 设置可⽤枪⼜个数 **/
	@Column(name="usable_gun_num")
	@Data(order = 5,byteType = DataByteTypEnum.Number)
	private Integer usableGunNum ; 

	/** 设置枪使⽤次数 **/
	@Column(name="gun_use_num")
	@Data(order = 6,byteLen = 2,byteType = DataByteTypEnum.Number)
	private Integer gunUseNum ; 

	/** 设置充电桩是否⽀持预约 1⽀持 2禁⽌ **/
	@Column(name="usable_pre_pile")
	@Data(order = 7, byteType = DataByteTypEnum.Byte)
	private byte usablePrePile ; 

	/** 设置充电桩显⽰屏登陆账号 **/
	@Column(name="pile_view_loading_account")
	@Data(order = 8,byteLen = 16,byteType = DataByteTypEnum.ASCII)
	private String pileViewLoadingAccount ; 

	/** 设置充电桩显⽰屏登陆密码 **/
	@Column(name="pile_view_loading_password")
	@Data(order = 9,byteLen = 16,byteType = DataByteTypEnum.ASCII)
	private String pileViewLoadingPassword ; 

	/** 设置⼼跳包发送间隔时间 **/
	@Column(name="heart_beat_interval_time")
	@Data(order = 10,byteLen = 2,byteType = DataByteTypEnum.Number)
	private Integer heartBeatIntervalTime ; 

	/** 设置服务器IP地址 **/
	@Column(name="server_ip")
	@Data(order = 11,byteLen = 4,byteType = DataByteTypEnum.IPv4)
	private String serverIp ; 

	/** 设置服务器端口号 **/
	@Column(name="server_port")
	@Data(order = 12,byteLen = 2,byteType = DataByteTypEnum.Number)
	private Integer serverPort ; 

	/** 二维码对应的枪口号 **/
	@Column(name="qrcode_pile_num")
	@Data(order = 15,byteLen = 2,byteType = DataByteTypEnum.Number)
	private Integer qrcodePileNum ; 

	/** 枪口号 **/
	@Column(name="gun_id")
	private Integer gunId ;

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
	
	public byte getPhaseSystem() {
		return phaseSystem;
	}
	public void setPhaseSystem(byte phaseSystem) {
		this.phaseSystem = phaseSystem;
	}
	
	public Integer getMaxOutput() {
		return maxOutput;
	}
	public void setMaxOutput(Integer maxOutput) {
		this.maxOutput = maxOutput;
	}
	
	public byte getPileWorkPattern() {
		return pileWorkPattern;
	}
	public void setPileWorkPattern(byte pileWorkPattern) {
		this.pileWorkPattern = pileWorkPattern;
	}
	
	public byte getOffNetCharge() {
		return offNetCharge;
	}
	public void setOffNetCharge(byte offNetCharge) {
		this.offNetCharge = offNetCharge;
	}
	
	public Integer getOffNetChargeNum() {
		return offNetChargeNum;
	}
	public void setOffNetChargeNum(Integer offNetChargeNum) {
		this.offNetChargeNum = offNetChargeNum;
	}
	
	public Integer getUsableGunNum() {
		return usableGunNum;
	}
	public void setUsableGunNum(Integer usableGunNum) {
		this.usableGunNum = usableGunNum;
	}
	
	public Integer getGunUseNum() {
		return gunUseNum;
	}
	public void setGunUseNum(Integer gunUseNum) {
		this.gunUseNum = gunUseNum;
	}
	
	public byte getUsablePrePile() {
		return usablePrePile;
	}
	public void setUsablePrePile(byte usablePrePile) {
		this.usablePrePile = usablePrePile;
	}
	
	public String getPileViewLoadingAccount() {
		return pileViewLoadingAccount;
	}
	public void setPileViewLoadingAccount(String pileViewLoadingAccount) {
		this.pileViewLoadingAccount = pileViewLoadingAccount;
	}
	
	public String getPileViewLoadingPassword() {
		return pileViewLoadingPassword;
	}
	public void setPileViewLoadingPassword(String pileViewLoadingPassword) {
		this.pileViewLoadingPassword = pileViewLoadingPassword;
	}
	
	public Integer getHeartBeatIntervalTime() {
		return heartBeatIntervalTime;
	}
	public void setHeartBeatIntervalTime(Integer heartBeatIntervalTime) {
		this.heartBeatIntervalTime = heartBeatIntervalTime;
	}
	
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	
	public Integer getQrcodePileNum() {
		return qrcodePileNum;
	}
	public void setQrcodePileNum(Integer qrcodePileNum) {
		this.qrcodePileNum = qrcodePileNum;
	}
	
	public Integer getGunId() {
		return gunId;
	}
	public void setGunId(Integer gunId) {
		this.gunId = gunId;
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
