
package com.tcp.tcp.vo.receive.vo;

/**
 * 充电枪状态
 */
public class GunStateVo {
	/**
	 * 枪口号
	 */
	private int gunNum;
	/**
	 * 桩状态
	 */
	private int gunAdminState;
	/**
	 * 枪的充电状态
	 */
	private int gunChargingState;

	public int getGunNum() {

		return gunNum;
	}

	public void setGunNum(int gunNum) {

		this.gunNum = gunNum;
	}

	public int getGunAdminState() {

		return gunAdminState;
	}

	public void setGunAdminState(int gunAdminState) {

		this.gunAdminState = gunAdminState;
	}

	public int getGunChargingState() {

		return gunChargingState;
	}

	public void setGunChargingState(int gunChargingState) {

		this.gunChargingState = gunChargingState;
	}

}
