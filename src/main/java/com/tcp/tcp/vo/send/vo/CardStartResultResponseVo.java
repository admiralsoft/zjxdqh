package com.tcp.tcp.vo.send.vo;

public class CardStartResultResponseVo {
	/**
	 * 枪号
	 */
	private Integer gunNum;
	/**
	 * 订单号
	 */
	private String sn;
	/**
	 * 预付余额
	 */
	private Integer money;

	public Integer getGunNum() {
		return gunNum;
	}

	public void setGunNum(Integer gunNum) {
		this.gunNum = gunNum;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

}
