
package com.tcp.tcp.vo.receive.vo;

public class GunTimeInfoVo {

	private Integer	gunNum;

	private Integer	gunAdminState;

	private Integer	gunChargingState;	// 充电状态

	private Integer	networkType;		// 连接方式

	private Integer	networdSignal;		// 网络信号

	private Integer	voltage;			// 当前电压

	private Integer	current;			// 当前电流

	private Integer	electricmeter;		// 当前电表读数

	private Integer	innerTemp;			// 内部温度

	private Integer	gunTemp;			// 枪温度

	private Integer	phaseAVolt;		// a相电压

	private Integer	phaseBVolt;		//

	private Integer	phaseCVolt;		//

	private Integer	phaseACur;			// a相电流

	private Integer	phaseBCur;			//

	private Integer	phaseCCur;

	private Integer	curPowerRate;		// 当前充电壮桩功率

	private Integer	gunWsage;			// 充电枪使用次数

	public Integer getGunNum() {

		return gunNum;
	}

	public void setGunNum(Integer gunNum) {

		this.gunNum = gunNum;
	}

	public Integer getGunAdminState() {

		return gunAdminState;
	}

	public void setGunAdminState(Integer gunAdminState) {

		this.gunAdminState = gunAdminState;
	}

	public Integer getGunChargingState() {

		return gunChargingState;
	}

	public void setGunChargingState(Integer gunChargingState) {

		this.gunChargingState = gunChargingState;
	}

	public Integer getNetworkType() {

		return networkType;
	}

	public void setNetworkType(Integer networkType) {

		this.networkType = networkType;
	}

	public Integer getNetwordSignal() {

		return networdSignal;
	}

	public void setNetwordSignal(Integer networdSignal) {

		this.networdSignal = networdSignal;
	}

	public Integer getVoltage() {

		return voltage;
	}

	public void setVoltage(Integer voltage) {

		this.voltage = voltage;
	}

	public Integer getCurrent() {

		return current;
	}

	public void setCurrent(Integer current) {

		this.current = current;
	}

	public Integer getElectricmeter() {

		return electricmeter;
	}

	public void setElectricmeter(Integer electricmeter) {

		this.electricmeter = electricmeter;
	}

	public Integer getInnerTemp() {

		return innerTemp;
	}

	public void setInnerTemp(Integer innerTemp) {

		this.innerTemp = innerTemp;
	}

	public Integer getGunTemp() {

		return gunTemp;
	}

	public void setGunTemp(Integer gunTemp) {

		this.gunTemp = gunTemp;
	}

	public Integer getPhaseAVolt() {

		return phaseAVolt;
	}

	public void setPhaseAVolt(Integer phaseAVolt) {

		this.phaseAVolt = phaseAVolt;
	}

	public Integer getPhaseBVolt() {

		return phaseBVolt;
	}

	public void setPhaseBVolt(Integer phaseBVolt) {

		this.phaseBVolt = phaseBVolt;
	}

	public Integer getPhaseCVolt() {

		return phaseCVolt;
	}

	public void setPhaseCVolt(Integer phaseCVolt) {

		this.phaseCVolt = phaseCVolt;
	}

	public Integer getPhaseACur() {

		return phaseACur;
	}

	public void setPhaseACur(Integer phaseACur) {

		this.phaseACur = phaseACur;
	}

	public Integer getPhaseBCur() {

		return phaseBCur;
	}

	public void setPhaseBCur(Integer phaseBCur) {

		this.phaseBCur = phaseBCur;
	}

	public Integer getPhaseCCur() {

		return phaseCCur;
	}

	public void setPhaseCCur(Integer phaseCCur) {

		this.phaseCCur = phaseCCur;
	}

	public Integer getCurPowerRate() {

		return curPowerRate;
	}

	public void setCurPowerRate(Integer curPowerRate) {

		this.curPowerRate = curPowerRate;
	}

	public Integer getGunWsage() {

		return gunWsage;
	}

	public void setGunWsage(Integer gunWsage) {

		this.gunWsage = gunWsage;
	}

}
