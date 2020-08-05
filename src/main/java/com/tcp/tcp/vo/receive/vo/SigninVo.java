
package com.tcp.tcp.vo.receive.vo;

/**
 * 注册数据接收类
 * 
 * @author QIk
 */
public class SigninVo {

	private int		pileType;

	private int		pilePower;

	private int		pileVoltage;

	private String	pileLongitude;

	private String	pileLatitude;

	private String	stationNum;		// 电站编号

	private String	regionNum;			// 地区编号

	private int		pileOperateType;	// 运营类型

	private int		number;			// 当前桩所属在该电站所属编号

	private int		gunNum;			// 枪口个数

	public int getNumber() {

		return number;
	}

	public void setNumber(int number) {

		this.number = number;
	}

	public int getGunNum() {

		return gunNum;
	}

	public void setGunNum(int gunNum) {

		this.gunNum = gunNum;
	}

	public int getPileType() {

		return pileType;
	}

	public void setPileType(int pileType) {

		this.pileType = pileType;
	}

	public int getPilePower() {

		return pilePower;
	}

	public void setPilePower(int pilePower) {

		this.pilePower = pilePower;
	}

	public int getPileVoltage() {

		return pileVoltage;
	}

	public void setPileVoltage(int pileVoltage) {

		this.pileVoltage = pileVoltage;
	}

	public String getPileLongitude() {

		return pileLongitude;
	}

	public void setPileLongitude(String pileLongitude) {

		this.pileLongitude = pileLongitude;
	}

	public String getPileLatitude() {

		return pileLatitude;
	}

	public void setPileLatitude(String pileLatitude) {

		this.pileLatitude = pileLatitude;
	}

	public String getStationNum() {

		return stationNum;
	}

	public void setStationNum(String stationNum) {

		this.stationNum = stationNum;
	}

	public String getRegionNum() {

		return regionNum;
	}

	public void setRegionNum(String regionNum) {

		this.regionNum = regionNum;
	}

	public int getPileOperateType() {

		return pileOperateType;
	}

	public void setPileOperateType(int pileOperateType) {

		this.pileOperateType = pileOperateType;
	}

}
