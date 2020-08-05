package com.tcp.tcp.vo.send.vo;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

public class SignCardResponseVo {
    /**
     * 枪号
     */
    @Data(byteType = DataByteTypEnum.Byte)
    private byte gunNum;
    /**
     * 卡号
     */
    @Data(order = 1, byteType = DataByteTypEnum.ASCII, byteLen = 8)
    private String cardNum;

    /**
     * 卡外编号
     */
    @Data(order = 2, byteType = DataByteTypEnum.ASCII, byteLen = 16)
    private String cardOtherNum;

    /**
     * 验证结果
     * 1. 成功
     * 2. 失败，余额不⾜
     * 3. 失败，权限不⾜
     * 4. 失败，此卡不能在此桩充电
     * 5. 卡号与车辆绑定验证失败
     * 6. 密码错误
     * 7. 非法帐号(卡号)错误
     * 8. 无效卡
     * 9. 挂失卡
     * 10. 数据格式错误
     */
    @Data(order = 3, byteType = DataByteTypEnum.Byte)
    private byte validResult;
    /**
     * 余额
     */
    @Data(order = 4, byteType = DataByteTypEnum.Number, byteLen = 4)
    private Integer balance;
    /**
     * 预付费金额
     */
    @Data(order = 5, byteType = DataByteTypEnum.Number, byteLen = 4)
    private Integer prepayment;

    /**
     * 免费金额
     */
    @Data(order = 6, byteType = DataByteTypEnum.Number, byteLen = 4)
    private Integer freeBalance;

    /**
     * 订单号
     */
    @Data(order = 7, byteType = DataByteTypEnum.ASCII, byteLen = 32)
    private String orderNo;


    public byte getGunNum() {
        return gunNum;
    }

    public void setGunNum(byte gunNum) {
        this.gunNum = gunNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardOtherNum() {
        return cardOtherNum;
    }

    public void setCardOtherNum(String cardOtherNum) {
        this.cardOtherNum = cardOtherNum;
    }

    public byte getValidResult() {
        return validResult;
    }

    public void setValidResult(byte validResult) {
        this.validResult = validResult;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Integer prepayment) {
        this.prepayment = prepayment;
    }

    public Integer getFreeBalance() {
        return freeBalance;
    }

    public void setFreeBalance(Integer freeBalance) {
        this.freeBalance = freeBalance;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
