package com.zjxdqh.evcs.enums;

/**
 * @author: wangqinmin
 * @date: 2019/8/20 12:36
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
public enum StakeTypeEnum implements BaseTypeIntegerEnum {

    KV_BUS(3, 1),
    KV_COMMON(0, 2),
    KV_OTHER(1, 3),
    KV_PERSONAL(2, 3);

    private Integer key;
    private Integer value;

    StakeTypeEnum(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public static Integer getStakeTypeEnumValue(Integer code) {
        if (code == null || "".equals(code)) {
            return null;
        }
        for (StakeTypeEnum e : StakeTypeEnum.values()) {
            if (code.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }
}
