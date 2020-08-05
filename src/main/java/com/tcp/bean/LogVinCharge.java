package com.tcp.bean;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

import java.util.Date;
import java.math.BigDecimal;
import java.sql.*;
import javax.persistence.*;


/**
 * 注意:注解只能加在属性字段上才会生效!
 * vin验证充电
 * @author code_generator
 */
@Table(name = "log_vin_charge")
public class LogVinCharge implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 **/
	@Id
	private String id ; 

	/** 桩号 **/
	@Column(name="pile_num")
	private String pileNum ; 

	/** 枪口编号 **/
	@Column(name="gun_num")
	@Data(order = 1, byteType = DataByteTypEnum.Byte)
	private byte gunNum ; 

	/** 车辆VIN **/
	@Column(name="vin_num")
	@Data( byteType = DataByteTypEnum.ASCII, byteLen = 17)
	private String vinNum ;

	/**"
	 * 车辆充电方式0，默认充满 1，按电量充 2，按金额充 3，按时间充
	 */
	@Column(name="charging_type")
	@Data(order = 2, byteType = DataByteTypEnum.Number)
	private Integer charging_type;

	/**
	 * 充电参数
	 */
	@Column(name="charging_data")
	@Data(order = 3, byteType = DataByteTypEnum.Decimal2, byteLen = 4)
	private BigDecimal charging_data;

	/** 创建时间 **/
	@Column(name="create_time")
	private Date createTime ; 

	/** 修改时间 **/
	@Column(name="modify_time")
	private Date modifyTime ; 

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

	/**  **/
	@Column(name="field5")
	private String field5 ; 


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPileNum() {
		return pileNum;
	}
	public void setPileNum(String pileNum) {
		this.pileNum = pileNum;
	}
	
	public byte getGunNum() {
		return gunNum;
	}
	public void setGunNum(byte gunNum) {
		this.gunNum = gunNum;
	}
	
	public String getVinNum() {
		return vinNum;
	}
	public void setVinNum(String vinNum) {
		this.vinNum = vinNum;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
	
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}

	public Integer getCharging_type() {
		return charging_type;
	}

	public void setCharging_type(Integer charging_type) {
		this.charging_type = charging_type;
	}

	public BigDecimal getCharging_data() {
		return charging_data;
	}

	public void setCharging_data(BigDecimal charging_data) {
		this.charging_data = charging_data;
	}
}
