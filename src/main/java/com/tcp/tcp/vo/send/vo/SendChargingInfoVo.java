
package com.tcp.tcp.vo.send.vo;

/**
 * 充电中数据返回
 */
public class SendChargingInfoVo {

	private int		gunNum;		// 枪号

	private Double		balance;		// 帐号余额

	private Double		prepaidBalance;	// 预付金额

	private Double		freeBalance;	// 免费余额

	private String	orderNum;		// 订单号

	public int getGunNum() {

		return gunNum;
	}

	public void setGunNum(int gunNum) {

		this.gunNum = gunNum;
	}

	public Double getBalance() {

		return balance;
	}

	public void setBalance(Double balance) {

		this.balance = balance;
	}

	public Double getPrepaidBalance() {

		return prepaidBalance;
	}

	public void setPrepaidBalance(Double prepaidBalance) {

		this.prepaidBalance = prepaidBalance;
	}

	public Double getFreeBalance() {

		return freeBalance;
	}

	public void setFreeBalance(Double freeBalance) {

		this.freeBalance = freeBalance;
	}

	public String getOrderNum() {

		return orderNum;
	}

	public void setOrderNum(String orderNum) {

		this.orderNum = orderNum;
	}

}
