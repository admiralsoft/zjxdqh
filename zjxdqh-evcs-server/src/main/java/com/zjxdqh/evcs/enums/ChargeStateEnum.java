package com.zjxdqh.evcs.enums;

/**
 * @author: wangqinmin
 * @date: 2019/5/13 14:11
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
public enum ChargeStateEnum implements BaseTypeIntegerEnum {

    /**
     * 监管平台
     * <p>
     * 充电订单状态 ： StartChargeSeqStat
     * 1：启动中   2：充电中   3：停止中   4：已结束   5：未知
     *
     * <p>
     * 运营平台
     * 准备中（2），启动中( 3 )，充电中（0），结算中( 4 )，已完成（1）, 已失效（-1）, 支付中（5）
     */

    KV_STARTUP(3, 1),
    KV_CHARGING(0, 2),
    KV_END(1, 4),
    KV_PAYING(5, 4),
    KV_PREPARE(2, 1),
    KV_SETTLEMENT(4, 3),
    KV_INVALID(-1, 5);


    private Integer key;
    private Integer value;

    ChargeStateEnum(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    public static Integer getChargeStateValue(Integer code) {
        if (code == null || "".equals(code)) {
            return null;
        }
        for (ChargeStateEnum e : ChargeStateEnum.values()) {
            if (code.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }
}
