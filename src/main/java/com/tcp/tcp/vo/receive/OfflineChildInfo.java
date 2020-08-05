package com.tcp.tcp.vo.receive;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 下午1:59.
 * @Title:
 * @Description: 离线数据信息
 */
public class OfflineChildInfo {

    /**
     * 充电时间段开始时间 4
     */
    private int childStarTime;

    /**
     * 充电时间段结束时间 4
     */
    private int childEndTime;

    /**
     * 充电时间段电量 4  比例 0.01单位 kW
     */
    private int powTotal;

    /**
     * 充电时间段服务费单价  比例 0.01单位 元
     */
    private int chargingServicePrice;

    /**
     * 充电时间段单价  比例 0.01单位 元
     */
    private int price;

    /**
     * 充电时间段服务费金额 比例 0.01单位 元
     */
    private int chargingServiceAmount;

    /**
     * 充电时间段充电金额 比例 0.01单位 元
     */
    private int chargingAmount;


    public int getChildStarTime() {
        return childStarTime;
    }

    public void setChildStarTime(int childStarTime) {
        this.childStarTime = childStarTime;
    }

    public int getChildEndTime() {
        return childEndTime;
    }

    public void setChildEndTime(int childEndTime) {
        this.childEndTime = childEndTime;
    }

    public int getPowTotal() {
        return powTotal;
    }

    public void setPowTotal(int powTotal) {
        this.powTotal = powTotal;
    }

    public int getChargingServicePrice() {
        return chargingServicePrice;
    }

    public void setChargingServicePrice(int chargingServicePrice) {
        this.chargingServicePrice = chargingServicePrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getChargingServiceAmount() {
        return chargingServiceAmount;
    }

    public void setChargingServiceAmount(int chargingServiceAmount) {
        this.chargingServiceAmount = chargingServiceAmount;
    }

    public int getChargingAmount() {
        return chargingAmount;
    }

    public void setChargingAmount(int chargingAmount) {
        this.chargingAmount = chargingAmount;
    }
}
