package com.zjxdqh.evcs.enums;

/**
 * @outhor liusiyu
 * @create 2019-05-13-11:20
 */
public enum StopChargeEnum implements BaseTypeStateEnum{

    /**
     *
     *       监管平台
     /**
     * 充电订单状态
     * 1：启动中
     * 2：充电中
     * 3：停止中
     * 4：已结束
     * 5：未知

     *0：充电中（0）
     *1：已完成（1）
     *2：准备中（2）
     *3：启动中( 3 )
     *4：结算中( 4 )
     *-1：已失效（-1）
     *
     */
    KV_START(1,"3"),
    KV_CHARGE(2,"0"),
    KV_STOP(3,"4"),
    KV_END(4,"1"),
    KV_PAYING(4,"5"),
    KV_UNKNOWN(5,"-1");

    private Integer key;
    private String value;

    StopChargeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static Integer getChargingStatusValue(Integer code){
        if(code!=null) {
            for (StopChargeEnum e : StopChargeEnum.values()) {
                if (e.getValue().equals(code.toString())) {
                    return e.getKey();
                }
            }
        }
        return null;
    }
}
