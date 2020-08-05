
package com.tcp.tcp.vo.receive.vo;

/**
 * 充电中的数据
 */
public class ChargingInfoVo {

	private Integer	gunNum;			// 枪号

	private Integer	chargingType;		// 充电类型

	private String	chargingAccounts;	// 充电帐号

	private Double	balance;			// 帐号余额

	private Double	prepaidBalance;	// 预付金额

	private Double	freeBalance;		// 免费余额

	private String	orderNum;			// 订单号

	private Double	useElectricity;	// 使用电量

	private Integer	soc;				// 当前车辆SOC

	private Integer	chargingRate;		// 当前充电桩桩功率

	private Integer	surplusWhenLong;	// 剩余充电时间

	private Integer	accountsType;		// 帐号类型

	private Double	current;			// 电流

	private Double	voltage;			// 电压

	private String	vin;				// 车辆vin

	private Integer	whenLong;			// 已充时长

	private Integer	pileTemperature;	// 桩内温度

	private Integer	muzzTemperature;	// 枪内温度

	private String	cardNum;			// 卡号

	private Double	outputA;			// 输出A相电压

	private Double	outputB;

	private Double	outputC;

	private Double	flowA;				// 交流A相电流

	private Double	flowB;

	private Double	flowC;

	private Double	dCVoltage;			// 直流电压

	private Double	dCurrent;			// 直流电流

	private Integer	dcTemperatureNum;	// 电池最高温度号

	private Double	dcTemperature;		// 电池最高温度

	private Integer	dcdyNum;			// 电池最高电压号号

	private Double	dcdy;				// 电池最高电压

	private Integer	dcdyMinNum;		// 电池最低电压号

	private Double	dcdyMin;			// 电池最低电压

	private Double	yxcdzgdy;			// 允许充电最高电压

	private Double	yxcddjzgdy;		// 允许充电单节最高电压

	private Integer	yxcddjzgwd;		// 允许电池单节最高温度

	private Double	ammeterReading;	// 当前电表读数

	private String sn;//订单号

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getGunNum() {

		return gunNum;
	}

	public void setGunNum(Integer gunNum) {

		this.gunNum = gunNum;
	}

	public Integer getChargingType() {

		return chargingType;
	}

	public void setChargingType(Integer chargingType) {

		this.chargingType = chargingType;
	}

	public String getChargingAccounts() {

		return chargingAccounts;
	}

	public void setChargingAccounts(String chargingAccounts) {

		this.chargingAccounts = chargingAccounts;
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

	public Double getUseElectricity() {

		return useElectricity;
	}

	public void setUseElectricity(Double useElectricity) {

		this.useElectricity = useElectricity;
	}

	public Integer getSoc() {

		return soc;
	}

	public void setSoc(Integer soc) {

		this.soc = soc;
	}

	public Integer getChargingRate() {

		return chargingRate;
	}

	public void setChargingRate(Integer chargingRate) {

		this.chargingRate = chargingRate;
	}

	public Integer getSurplusWhenLong() {

		return surplusWhenLong;
	}

	public void setSurplusWhenLong(Integer surplusWhenLong) {

		this.surplusWhenLong = surplusWhenLong;
	}

	public Integer getAccountsType() {

		return accountsType;
	}

	public void setAccountsType(Integer accountsType) {

		this.accountsType = accountsType;
	}

	public Double getCurrent() {

		return current;
	}

	public void setCurrent(Double current) {

		this.current = current;
	}

	public Double getVoltage() {

		return voltage;
	}

	public void setVoltage(Double voltage) {

		this.voltage = voltage;
	}

	public String getVin() {

		return vin;
	}

	public void setVin(String vin) {

		this.vin = vin;
	}

	public Integer getWhenLong() {

		return whenLong;
	}

	public void setWhenLong(Integer whenLong) {

		this.whenLong = whenLong;
	}

	public Integer getPileTemperature() {

		return pileTemperature;
	}

	public void setPileTemperature(Integer pileTemperature) {

		this.pileTemperature = pileTemperature;
	}

	public Integer getMuzzTemperature() {

		return muzzTemperature;
	}

	public void setMuzzTemperature(Integer muzzTemperature) {

		this.muzzTemperature = muzzTemperature;
	}

	public String getCardNum() {

		return cardNum;
	}

	public void setCardNum(String cardNum) {

		this.cardNum = cardNum;
	}

	public Double getOutputA() {

		return outputA;
	}

	public void setOutputA(Double outputA) {

		this.outputA = outputA;
	}

	public Double getOutputB() {

		return outputB;
	}

	public void setOutputB(Double outputB) {

		this.outputB = outputB;
	}

	public Double getOutputC() {

		return outputC;
	}

	public void setOutputC(Double outputC) {

		this.outputC = outputC;
	}

	public Double getFlowA() {

		return flowA;
	}

	public void setFlowA(Double flowA) {

		this.flowA = flowA;
	}

	public Double getFlowB() {

		return flowB;
	}

	public void setFlowB(Double flowB) {

		this.flowB = flowB;
	}

	public Double getFlowC() {

		return flowC;
	}

	public void setFlowC(Double flowC) {

		this.flowC = flowC;
	}

	public Double getdCVoltage() {

		return dCVoltage;
	}

	public void setdCVoltage(Double dCVoltage) {

		this.dCVoltage = dCVoltage;
	}

	public Double getdCurrent() {

		return dCurrent;
	}

	public void setdCurrent(Double dCurrent) {

		this.dCurrent = dCurrent;
	}

	public Integer getDcTemperatureNum() {

		return dcTemperatureNum;
	}

	public void setDcTemperatureNum(Integer dcTemperatureNum) {

		this.dcTemperatureNum = dcTemperatureNum;
	}

	public Double getDcTemperature() {

		return dcTemperature;
	}

	public void setDcTemperature(Double dcTemperature) {

		this.dcTemperature = dcTemperature;
	}

	public Integer getDcdyNum() {

		return dcdyNum;
	}

	public void setDcdyNum(Integer dcdyNum) {

		this.dcdyNum = dcdyNum;
	}

	public Double getDcdy() {

		return dcdy;
	}

	public void setDcdy(Double dcdy) {

		this.dcdy = dcdy;
	}

	public Integer getDcdyMinNum() {

		return dcdyMinNum;
	}

	public void setDcdyMinNum(Integer dcdyMinNum) {

		this.dcdyMinNum = dcdyMinNum;
	}

	public Double getDcdyMin() {

		return dcdyMin;
	}

	public void setDcdyMin(Double dcdyMin) {

		this.dcdyMin = dcdyMin;
	}

	public Double getYxcdzgdy() {

		return yxcdzgdy;
	}

	public void setYxcdzgdy(Double yxcdzgdy) {

		this.yxcdzgdy = yxcdzgdy;
	}

	public Double getYxcddjzgdy() {

		return yxcddjzgdy;
	}

	public void setYxcddjzgdy(Double yxcddjzgdy) {

		this.yxcddjzgdy = yxcddjzgdy;
	}

	public Integer getYxcddjzgwd() {

		return yxcddjzgwd;
	}

	public void setYxcddjzgwd(Integer yxcddjzgwd) {

		this.yxcddjzgwd = yxcddjzgwd;
	}

	public Double getAmmeterReading() {

		return ammeterReading;
	}

	public void setAmmeterReading(Double ammeterReading) {

		this.ammeterReading = ammeterReading;
	}

}
