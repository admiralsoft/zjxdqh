package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 公司组织管理表
 * @author code_generator
 */
@Table(name = "sys_organization")
public class SysOrganization implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String orgId ;

	/** 组织名称pinyin **/
	@Column(name="org_pinyin")
	private String orgPinyin ; 

	/** 组织全名 **/
	@Column(name="quanlujin")
	private String quanlujin ; 

	/** 组织名 **/
	@Column(name="org_name")
	private String orgName ; 

	/** 组织级别 **/
	@Column(name="org_level")
	private Long orgLevel ; 

	/** 父级id **/
	@Column(name="parent_id")
	private Long parentId ; 

	/** 启用状态 **/
	@Column(name="status")
	private Integer status ; 

	/** 备注 **/
	@Column(name="remark")
	private String remark ; 

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
	@Column(name="field1")
	private String field1 ; 

	/**  **/
	@Column(name="field2")
	private String field2 ; 

	/**  **/
	@Column(name="field3")
	private String field3 ; 

	/**  **/
	@Column(name="field4")
	private String field4 ; 


	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgPinyin() {
		return orgPinyin;
	}
	public void setOrgPinyin(String orgPinyin) {
		this.orgPinyin = orgPinyin;
	}
	
	public String getQuanlujin() {
		return quanlujin;
	}
	public void setQuanlujin(String quanlujin) {
		this.quanlujin = quanlujin;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public Long getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	

}
