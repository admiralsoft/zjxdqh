package com.tcp.tcp.vo.send.vo;

public class SendStartChargingVo {
	/**
	 * 枪口号
	 */
	private int gunNum;
	/**
	 * 命令
	 */
	private int cmd;
	/**
	 * 启动账户
	 */
	private String s_account;
	/**
	 * 用户余额
	 */
	private String s_balance;
	/**
	 * 预付费
	 */
	private String s_prepayment;
	/**
	 * 免费余额
	 */
	private String f_monney;
	/**
	 * 订单号
	 */
	private String sn;
	/**
	 * 辅源
	 */
	private int support_power;

	public int getSupport_power() {
		return support_power;
	}

	public void setSupport_power(int support_power) {
		this.support_power = support_power;
	}

	public int getGunNum() {
		return gunNum;
	}

	public void setGunNum(int gunNum) {
		this.gunNum = gunNum;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public String getS_account() {
		return s_account;
	}

	public void setS_account(String s_account) {
		this.s_account = s_account;
	}

	public String getS_balance() {
		return s_balance;
	}

	public void setS_balance(String s_balance) {
		this.s_balance = s_balance;
	}

	public String getS_prepayment() {
		return s_prepayment;
	}

	public void setS_prepayment(String s_prepayment) {
		this.s_prepayment = s_prepayment;
	}

	public String getF_monney() {
		return f_monney;
	}

	public void setF_monney(String f_monney) {
		this.f_monney = f_monney;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
