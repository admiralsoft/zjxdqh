package com.tcp.tcp.vo.receive.vo;

public class StartChargingBackVo {
	/**
	 * 结果码11启动成功,12启动失败,21停止成功,22停止失败
	 */
	int result_code;
	/**
	 * 电量
	 */
	String totlePower;
	/**
	 * 枪口号
	 */
	int gunNum;

	/**
	 * 订单号
	 */
	String sn;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getResult_code() {
		return result_code;
	}

	public void setResult_code(int result_code) {
		this.result_code = result_code;
	}

	public String getTotlePower() {
		return totlePower;
	}

	public void setTotlePower(String totlePower) {
		this.totlePower = totlePower;
	}

	public int getGunNum() {
		return gunNum;
	}

	public void setGunNum(int gunNum) {
		this.gunNum = gunNum;
	}

}
