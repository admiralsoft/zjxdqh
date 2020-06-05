package com.zjxdqh.evcs.enums;

/**
 * 车位状态
 *
 *
 *
 0：未知
 10：已解锁
 50：已上锁
 *
 *
 *
 */
public enum LockStatusEnum implements BaseTypeStateEnum{

    KV_UNKNOWN(0,"未知"),
    KV_UNLOCK(10,"已解锁"),
    KV_LOCK(50,"已上锁");

    private Integer key;
    private String value;

    LockStatusEnum(Integer key, String value) {
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
