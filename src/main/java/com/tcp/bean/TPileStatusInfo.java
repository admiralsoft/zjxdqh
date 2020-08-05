package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 
 * @author code_generator
 */
@Table(name = "t_pile_status_info")
public class TPileStatusInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 上线时间 **/
	@Column(name="online_time")
	private Date onlineTime ;

	/** 离线时间 **/
	@Column(name="leave_time")
	private Date leaveTime ;

	/** 上次离线时间 **/
	@Column(name="last_leave_time")
	private Date lastLeaveTime ;

	/** 当前桩状态 0离线 1在线 **/
	@Column(name="state")
	private byte state ; 

	/** 创建时间 **/
	@Column(name="create_time")
	private Date createTime ; 

	/** 修改时间 **/
	@Column(name="update_time")
	private Date updateTime ; 


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPileNum() {
		return pileNum;
	}
	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
	}
	
	public Date getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Date getLastLeaveTime() {
		return lastLeaveTime;
	}

	public void setLastLeaveTime(Date lastLeaveTime) {
		this.lastLeaveTime = lastLeaveTime;
	}
}
