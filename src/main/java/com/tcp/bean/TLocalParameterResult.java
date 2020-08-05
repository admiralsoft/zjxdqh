package com.tcp.bean;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 充电桩回复配置信息
 * @Author Xu
 */
@Table(name = "t_local_parameter_result")
public class TLocalParameterResult {

    /**
     * 升级成功
     */
    public static final byte Upgrade_Result_Succ = 1;
    /**
     * 升级失败
     */
    public static final byte Upgrade_Result_Fail = 2;
    /**
     * 查询
     */
    public static final byte Upgrade_Result_Query = 0;

    /** 主键 **/
    @Id
    private String id;

    /** 桩号 **/
    @Column(name="pile_num")
    private String pileNum ;

    /** 硬件版本号**/
    @Column(name="hardware_version")
    private String hardwareVersion;

    /** 软件版本号**/
    @Column(name = "software_version")
    private String softwareVersion;

    /** 相系 **/
    @Column(name="phase_system")
    private byte phaseSystem ;

    /** 最大输出电流  **/
    @Column(name="max_output")
    private Integer maxOutput ;

    /** 充电桩启动方式 1. 刷卡启动 2. APP启动 3. 刷卡/APP启动**/
    @Column(name = "pile_start")
    private Byte pileStart;

    /** 是否允许离⽹充电 1，允许离⽹充电 2，禁⽌离⽹充电**/
    @Column(name = "pile_offnet")
    private Byte pileOffnet;

    /** 离网个数 **/
    @Column(name="pile_offnet_num")
    private Integer pileOffnetNum ;

    /** 可用枪口个数 **/
    @Column(name="usable_gun_num")
    private Integer usableGunNum ;

    /** 枪使用次数 **/
    @Column(name="gun_use_num")
    private Integer gunUseNum ;

    /** 充电桩是否⽀持预约 1⽀持 2禁⽌ **/
    @Column(name="usable_pre_pile")
    private byte usablePrePile ;

    /** 充电桩显⽰屏登陆账号 **/
    @Column(name="pile_view_loading_account")
    private String pileViewLoadingAccount ;

    /** 设置充电桩显⽰屏登陆密码 **/
    @Column(name="pile_view_loading_password")
    private String pileViewLoadingPassword ;

    /** 升级结果 0，查询返回 1，成功 2，失败**/
    @Column(name = "upgrade_result")
    private Byte upgradeResult;

    /** 创建时间 **/
    @Column(name="create_time")
    private Date createTime ;

    /** 修改时间 **/
    @Column(name="modify_time")
    private Date modifyTime ;

    /** 执行状态**/
    @Column(name="status")
    private byte status;

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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
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

    public Byte getPileStart() {
        return pileStart;
    }

    public void setPileStart(Byte pileStart) {
        this.pileStart = pileStart;
    }

    public Byte getPileOffnet() {
        return pileOffnet;
    }

    public void setPileOffnet(Byte pileOffnet) {
        this.pileOffnet = pileOffnet;
    }

    public Integer getPileOffnetNum() {
        return pileOffnetNum;
    }

    public void setPileOffnetNum(Integer pileOffnetNum) {
        this.pileOffnetNum = pileOffnetNum;
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

    public Byte getUpgradeResult() {
        return upgradeResult;
    }

    public void setUpgradeResult(Byte upgradeResult) {
        this.upgradeResult = upgradeResult;
    }
}
