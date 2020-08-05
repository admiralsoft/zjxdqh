package com.tcp.tcp.vo.receive.vo;

import java.util.List;

public class EndChargingVo {
	private int num; // 序号
	private int length; // 长度
	private int gunNum;// 枪号
	private int a_type;// 账号类型
	private Long card_num;// 卡号
	private String accont;// 账号
	private String vin;// vin码
	private int s_type;// 开始类型
	private long stime;// 开始时间
	private long etime;// 结束时间
	private Double power;// 用电量
	private Double server_price;// 服务费
	private Double totleMoney;// 电费
	private int time_num;// 时间段数
	private List<Times> times;// 时间段内容
	private int ssoc;// 开始的soc
	private int esoc;// 结束的soc
	private int sread;// 开始电表度数
	private int eread;// 结束电表度数
	private String sn;// 订单号

	private int end_type;//结束原因

	public int getEnd_type() {
		return end_type;
	}

	public void setEnd_type(int end_type) {
		this.end_type = end_type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getGunNum() {
		return gunNum;
	}

	public void setGunNum(int gunNum) {
		this.gunNum = gunNum;
	}

	public int getA_type() {
		return a_type;
	}

	public void setA_type(int a_type) {
		this.a_type = a_type;
	}

	public Long getCard_num() {
		return card_num;
	}

	public void setCard_num(Long card_num) {
		this.card_num = card_num;
	}

	public String getAccont() {
		return accont;
	}

	public void setAccont(String accont) {
		this.accont = accont;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getS_type() {
		return s_type;
	}

	public void setS_type(int s_type) {
		this.s_type = s_type;
	}

	public long getStime() {
		return stime;
	}

	public void setStime(long stime) {
		this.stime = stime;
	}

	public long getEtime() {
		return etime;
	}

	public void setEtime(long etime) {
		this.etime = etime;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public Double getServer_price() {
		return server_price;
	}

	public void setServer_price(Double server_price) {
		this.server_price = server_price;
	}

	public Double getTotleMoney() {
		return totleMoney;
	}

	public void setTotleMoney(Double totleMoney) {
		this.totleMoney = totleMoney;
	}

	public int getTime_num() {
		return time_num;
	}

	public void setTime_num(int time_num) {
		this.time_num = time_num;
	}

	public List<Times> getTimes() {
		return times;
	}

	public void setTimes(List<Times> times) {
		this.times = times;
	}

	public int getSsoc() {
		return ssoc;
	}

	public void setSsoc(int ssoc) {
		this.ssoc = ssoc;
	}

	public int getEsoc() {
		return esoc;
	}

	public void setEsoc(int esoc) {
		this.esoc = esoc;
	}

	public int getSread() {
		return sread;
	}

	public void setSread(int sread) {
		this.sread = sread;
	}

	public int getEread() {
		return eread;
	}

	public void setEread(int eread) {
		this.eread = eread;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public static class Times {
		private long stimes;
		private long etimes;
		private Double powers;
		private Double server_p_d;
		private Double ele_p_d;
		private Double ele_p;
		private Double server_p;

		public long getStimes() {
			return stimes;
		}

		public void setStimes(long stimes) {
			this.stimes = stimes;
		}

		public long getEtimes() {
			return etimes;
		}

		public void setEtimes(long etimes) {
			this.etimes = etimes;
		}

		public Double getPowers() {
			return powers;
		}

		public void setPowers(Double powers) {
			this.powers = powers;
		}

		public Double getServer_p_d() {
			return server_p_d;
		}

		public void setServer_p_d(Double server_p_d) {
			this.server_p_d = server_p_d;
		}

		public Double getEle_p_d() {
			return ele_p_d;
		}

		public void setEle_p_d(Double ele_p_d) {
			this.ele_p_d = ele_p_d;
		}

		public Double getEle_p() {
			return ele_p;
		}

		public void setEle_p(Double ele_p) {
			this.ele_p = ele_p;
		}

		public Double getServer_p() {
			return server_p;
		}

		public void setServer_p(Double server_p) {
			this.server_p = server_p;
		}

	}
}
