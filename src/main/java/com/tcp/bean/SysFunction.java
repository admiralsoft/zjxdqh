package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 系统菜单功能表
 * @author code_generator
 */
@Table(name = "sys_function")
public class SysFunction implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 功能主键 **/
	@Id
	private String funcId ;

	/** 父级菜单id **/
	@Column(name="parent_id")
	private Long parentId ; 

	/** 功能编码 **/
	@Column(name="func_code")
	private String funcCode ; 

	/** 功能页面名 **/
	@Column(name="func_name")
	private String funcName ; 

	/** 功能页面描述 **/
	@Column(name="func_desc")
	private String funcDesc ; 

	/** 页面url **/
	@Column(name="func_action")
	private String funcAction ; 

	/** 启动状态:1正常0停用 **/
	@Column(name="stauts")
	private byte stauts ; 

	/** 排序 **/
	@Column(name="sort_order")
	private byte sortOrder ; 

	/** 是否父级菜单:1是0否 **/
	@Column(name="is_parent")
	private byte isParent ; 

	/** 一级菜单id **/
	@Column(name="fg_id")
	private Long fgId ; 

	/**  **/
	@Column(name="create_by")
	private String createBy ; 

	/**  **/
	@Column(name="create_date")
	private Date createDate ; 

	/**  **/
	@Column(name="field1")
	private String field1 ; 

	/**  **/
	@Column(name="field2")
	private String field2 ; 

	/**  **/
	@Column(name="field3")
	private String field3 ; 


	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	
	public String getFuncAction() {
		return funcAction;
	}
	public void setFuncAction(String funcAction) {
		this.funcAction = funcAction;
	}
	
	public byte getStauts() {
		return stauts;
	}
	public void setStauts(byte stauts) {
		this.stauts = stauts;
	}
	
	public byte getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(byte sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public byte getIsParent() {
		return isParent;
	}
	public void setIsParent(byte isParent) {
		this.isParent = isParent;
	}
	
	public Long getFgId() {
		return fgId;
	}
	public void setFgId(Long fgId) {
		this.fgId = fgId;
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
	

}
