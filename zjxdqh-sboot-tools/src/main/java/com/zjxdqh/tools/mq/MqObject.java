package com.zjxdqh.tools.mq;

import lombok.Data;

/**
 * MQ消息队列
 *
 * @author Yorking
 * @date 2019/05/09
 */
@Data
public class MqObject {

    /**
     * 设备状态改变
     */
    public static final int CODE_PILE_STATE = 1;
    /**
     * 订单状态改变
     */
    public static final int CODE_ORDER_STATE = 2;
    /**
     * 订单停止充电
     */
    public static final int CODE_ORDER_STOP = 3;
    /**
     * 订单信息
     */
    public static final int CODE_ORDER_INFO = 4;

    /**
     * 推送启动充电结果
     */
    public static final int CODE_START_CHARGE_RESULT = 5;
    /**
     * 支付
     */
    public static final int CODE_ORDER_PAY = 6;
    /**
     * 延时退款队列
     */
    public static final int CODE_ORDER_TIMEOUT = 7;


    private int code;

    /**
     * 订单号
     */
    private String orderNo;


    /**
     * 设备号（桩号）
     */
    private String deviceNo;

    /**
     * 枪号
     */
    private String gunNo;

    public static void main(String[] args) {
        MqObject mq = new MqObject();
        mq.setCode(CODE_ORDER_INFO);
        mq.setOrderNo("A2019031817120387710000000000012");
        mq.setDeviceNo("6668880000000123");
        mq.setGunNo("1");
    }

}
