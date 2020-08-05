package com.tcp.tcp.vo.receive.vo;

import java.math.BigDecimal;

/**
 * 桩体请求刷卡验证实体类
 * 
 * @author jiangzhilin
 *
 */
public class SignCardRequestVo {
	private Integer gunNum; // 枪口号
	private String cardNum; // 卡号
	private String outCardNum; // 卡外编号
	private String pwd; // 卡密码
	private String vin; // 车辆vin
	private Integer charging_type; // 车辆充电方式0，默认充满 1，按电量充 2，按金额充 3，按时间充
	private BigDecimal charging_data; // 充电参数

	public Integer getGunNum() {
		return gunNum;
	}

	public void setGunNum(Integer gunNum) {
		this.gunNum = gunNum;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getOutCardNum() {
		return outCardNum;
	}

	public void setOutCardNum(String outCardNum) {
		this.outCardNum = outCardNum;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getCharging_type() {
		return charging_type;
	}

	public void setCharging_type(Integer charging_type) {
		this.charging_type = charging_type;
	}

	public BigDecimal getCharging_data() {
		return charging_data;
	}

	public void setCharging_data(BigDecimal charging_data) {
		this.charging_data = charging_data;
	}
}
