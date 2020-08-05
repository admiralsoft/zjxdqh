
package com.tcp.tcp.vo.send.vo;

/**
 * 主动下发逻辑类
 */
public class ActiveSendSetUpHeartbeatVo {

	private Integer	reportTime;	// 上报周同期(单位：秒)

	private Integer	overTime;	// 超时时间(单位：秒)

	public Integer getReportTime() {

		return reportTime;
	}

	public void setReportTime(Integer reportTime) {

		this.reportTime = reportTime;
	}

	public Integer getOverTime() {

		return overTime;
	}

	public void setOverTime(Integer overTime) {

		this.overTime = overTime;
	}

}
