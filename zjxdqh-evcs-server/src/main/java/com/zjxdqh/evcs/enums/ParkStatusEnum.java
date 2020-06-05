package com.zjxdqh.evcs.enums;

/**
 * 车位状态
 *
 * 0：未知
   10：空闲
   50：占用
 *
 */
public enum ParkStatusEnum implements BaseTypeStateEnum{


    KV_UNKNOWN(0,"未知"),
    KV_FREE(10,"空闲"),
    KV_OCCUPY(50,"占用");

    private Integer key;
    private String value;

    ParkStatusEnum(Integer key, String value) {
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
}
