package com.tcp.tcp.vo.receive.vo;

public class CardStartResultVo {
	/**
	 * 枪口号
	 */
	private Integer gunNum;
	/**
	 * 结果码
	 */
	private Integer result_code;
	/**
	 * 卡号
	 */
	private String cardNum;
	/**
	 * 订单号
	 */
	private String sn;

	public Integer getGunNum() {
		return gunNum;
	}

	public void setGunNum(Integer gunNum) {
		this.gunNum = gunNum;
	}

	public Integer getResult_code() {
		return result_code;
	}

	public void setResult_code(Integer result_code) {
		this.result_code = result_code;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
