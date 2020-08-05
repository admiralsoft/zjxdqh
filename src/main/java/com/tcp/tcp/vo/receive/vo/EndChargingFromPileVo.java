package com.tcp.tcp.vo.receive.vo;

import java.math.BigDecimal;

public class EndChargingFromPileVo {
	/**
	 * 枪口号
	 */
	private int gunNum;
	/**
	 * 结束类型码
	 */
	private int e_type;
	/**
	 * 结束账户
	 */
	private String e_account;
	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	/**
	 * 预付余额
	 */
	private BigDecimal totleMoney;
	/**
	 * 免费余额
	 */
	private BigDecimal free_money;
	/**
	 * 电表读数
	 */
	private BigDecimal readNum;
	/**
	 * 用电量
	 */
	private BigDecimal totlePower;
	/**
	 * 订单号
	 */
	private String sn;

	/**
	 * 预付费
	 * @return
	 */
	private BigDecimal per_money;

	public BigDecimal getPer_money() {
		return per_money;
	}

	public void setPer_money(BigDecimal per_money) {
		this.per_money = per_money;
	}

	public int getGunNum() {
		return gunNum;
	}

	public void setGunNum(int gunNum) {
		this.gunNum = gunNum;
	}

	public int getE_type() {
		return e_type;
	}

	public void setE_type(int e_type) {
		this.e_type = e_type;
	}

	public String getE_account() {
		return e_account;
	}

	public void setE_account(String e_account) {
		this.e_account = e_account;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTotleMoney() {
		return totleMoney;
	}

	public void setTotleMoney(BigDecimal totleMoney) {
		this.totleMoney = totleMoney;
	}

	public BigDecimal getFree_money() {
		return free_money;
	}

	public void setFree_money(BigDecimal free_money) {
		this.free_money = free_money;
	}

	public BigDecimal getReadNum() {
		return readNum;
	}

	public void setReadNum(BigDecimal readNum) {
		this.readNum = readNum;
	}

	public BigDecimal getTotlePower() {
		return totlePower;
	}

	public void setTotlePower(BigDecimal totlePower) {
		this.totlePower = totlePower;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
