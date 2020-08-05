package com.tcp.core.cache;

public class OrderCache {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 帐号
     */
    private String accountNo;
    /**
     * 卡号
     */
    private String cardNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
