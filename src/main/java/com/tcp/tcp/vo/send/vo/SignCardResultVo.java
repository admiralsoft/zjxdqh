package com.tcp.tcp.vo.send.vo;

/**
 * @Author: jiangzhilin
 * @Date: 2018/8/13 13:57
 */
public class SignCardResultVo {
    // 帐号卡验证结果119,5110000000000004(桩编号),2(枪编号),000000000(卡号),0(验证结果),100(预付费余额),订单号
    private int gunNum;
    private String cardNum;
    private int resultCode;
    private Double money;
    private String sn;

    public int getGunNum() {
        return gunNum;
    }

    public void setGunNum(int gunNum) {
        this.gunNum = gunNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
