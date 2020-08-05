package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 后台管理用户表
 * @author code_generator
 */
@Table(name = "sys_user")
public class SysUser implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String userId ;

	/** 账号 **/
	@Column(name="account")
	private String account ; 

	/** 密码 **/
	@Column(name="pwd")
	private String pwd ; 

	/** 用户名 **/
	@Column(name="user_name")
	private String userName ; 

	/** 状态:0停用1启用 **/
	@Column(name="status")
	private Integer status ; 

	/** 最后登录时间 **/
	@Column(name="last_login_date")
	private Date lastLoginDate ; 

	/** 邮箱 **/
	@Column(name="email")
	private String email ; 

	/**  **/
	@Column(name="create_by")
	private String createBy ; 

	/**  **/
	@Column(name="create_date")
	private Date createDate ; 

	/**  **/
	@Column(name="create_ip")
	private String createIp ; 

	/**  **/
	@Column(name="update_by")
	private String updateBy ; 

	/**  **/
	@Column(name="update_date")
	private Date updateDate ; 

	/**  **/
	@Column(name="update_ip")
	private String updateIp ; 

	/**  **/
	@Column(name="ext1")
	private String ext1 ; 

	/**  **/
	@Column(name="ext2")
	private String ext2 ; 

	/**  **/
	@Column(name="ext3")
	private String ext3 ; 

	/**  **/
	@Column(name="ext4")
	private String ext4 ; 


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getCreateIp() {
		return createIp;
	}
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	

}
