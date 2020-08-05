
package com.tcp.bean;

import java.io.Serializable;
import java.util.Date;

public class TTCPMessage implements Serializable {

	private String				messageId;

	private String				messageCmd;

	private String				messageInfo;

	private Integer				messageType;

	private String				pileNum;

	private Date				createTime;

	private static final long	serialVersionUID	= 1L;

	public String getMessageId() {

		return messageId;
	}

	public void setMessageId(String messageId) {

		this.messageId = messageId;
	}

	public String getMessageCmd() {
		return messageCmd;
	}

	public void setMessageCmd(String messageCmd) {
		this.messageCmd = messageCmd;
	}

	public String getMessageInfo() {

		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {

		this.messageInfo = messageInfo == null ? null : messageInfo.trim();
	}

	public Integer getMessageType() {

		return messageType;
	}

	public void setMessageType(Integer messageType) {

		this.messageType = messageType;
	}

	public String getPileNum() {

		return pileNum;
	}

	public void setPileNum(String pileNum) {

		this.pileNum = pileNum == null ? null : pileNum.trim();
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
	}
}