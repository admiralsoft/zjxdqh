package com.tcp.bean;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * 菜单按钮管理表
 * @author code_generator
 */
@Table(name = "sys_button")
public class SysButton implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 按钮主键 **/
	@Id
	private String btId ;


	/** 功能id **/
	@Column(name="func_id")
	private Long funcId ; 

	/** 功能按钮编码 **/
	@Column(name="bt_code")
	private String btCode ; 

	/** 按钮名 **/
	@Column(name="bt_name")
	private String btName ; 

	/** 创建人 **/
	@Column(name="create_by")
	private String createBy ; 

	/** 创建时间 **/
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


	public String getBtId() {
		return btId;
	}
	public void setBtId(String btId) {
		this.btId = btId;
	}
	
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	
	public String getBtCode() {
		return btCode;
	}
	public void setBtCode(String btCode) {
		this.btCode = btCode;
	}
	
	public String getBtName() {
		return btName;
	}
	public void setBtName(String btName) {
		this.btName = btName;
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
