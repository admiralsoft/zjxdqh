package com.tcp.tcp.vo.send.vo;

import java.math.BigDecimal;

/**
 * @Author yaweiXu
 */
public class CarVINInfoVo {
    private int		gunNum;		// 枪号

    private String vin; //车辆VIN

    private int result;//1，验证成功2，失败，⽆此VIN记录 3，失败，桩车绑定验证失败

    private String orderNum;//订单号

    private BigDecimal balance;//充电金额

    public int getGunNum() {
        return gunNum;
    }

    public void setGunNum(int gunNum) {
        this.gunNum = gunNum;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
