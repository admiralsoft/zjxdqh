package com.tcp.tcp.vo.receive.vo;

import java.math.BigDecimal;

public class StartChargingVo {
	/**
	 * 枪口号
	 */
	private int gun_num;
	/**
	 * 开始类型
	 */
	private int s_type;
	/**
	 * 启动账户
	 */
	private String s_account;
	/**
	 * 余额
	 */
	private BigDecimal s_balance;
	/**
	 * 预付费
	 */
	private BigDecimal s_prepayment;
	/**
	 * 免费金额
	 */
	private BigDecimal f_money;
	/**
	 * 电表读数
	 */
	private Double readnum;
	/**
	 * 订单号
	 */
	private String sn;

	public int getGun_num() {
		return gun_num;
	}

	public void setGun_num(int gun_num) {
		this.gun_num = gun_num;
	}

	public int getS_type() {
		return s_type;
	}

	public void setS_type(int s_type) {
		this.s_type = s_type;
	}

	public String getS_account() {
		return s_account;
	}

	public void setS_account(String s_account) {
		this.s_account = s_account;
	}

	public BigDecimal getS_balance() {
		return s_balance;
	}

	public void setS_balance(BigDecimal s_balance) {
		this.s_balance = s_balance;
	}

	public BigDecimal getS_prepayment() {
		return s_prepayment;
	}

	public void setS_prepayment(BigDecimal s_prepayment) {
		this.s_prepayment = s_prepayment;
	}

	public BigDecimal getF_money() {
		return f_money;
	}

	public void setF_money(BigDecimal f_money) {
		this.f_money = f_money;
	}

	public Double getReadnum() {
		return readnum;
	}

	public void setReadnum(Double readnum) {
		this.readnum = readnum;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
