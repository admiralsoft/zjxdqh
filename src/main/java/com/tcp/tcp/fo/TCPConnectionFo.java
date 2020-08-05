
package com.tcp.tcp.fo;

import java.io.Serializable;

/**
 * TCP连接缓存
 * 
 * @author QIk
 */
public class TCPConnectionFo implements Serializable {

	private static final long	serialVersionUID	= 4011739141956275324L;

	private String				channelId;

	private String				serviceIp;									// 服务器Ip

	private Integer				muzzleCount;								// 枪个数

	private Integer				conntState;								// 当前连接状态

	private String				remoteAddress;								// 数据地址

	private String 				pileNum;

	public String getChannelId() {

		return channelId;
	}

	public void setChannelId(String channelId) {

		this.channelId = channelId;
	}

	public String getServiceIp() {

		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {

		this.serviceIp = serviceIp;
	}

	public Integer getMuzzleCount() {

		return muzzleCount;
	}

	public void setMuzzleCount(Integer muzzleCount) {

		this.muzzleCount = muzzleCount;
	}

	public Integer getConntState() {

		return conntState;
	}

	public void setConntState(Integer conntState) {

		this.conntState = conntState;
	}

	public String getRemoteAddress() {

		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {

		this.remoteAddress = remoteAddress;
	}

	public String getPileNum() {
		return pileNum;
	}

	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
	}
}
