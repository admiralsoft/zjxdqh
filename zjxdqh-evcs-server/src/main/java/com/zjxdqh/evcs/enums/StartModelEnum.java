package com.zjxdqh.evcs.enums;

/**
 * @author: wangqinmin
 * @date: 2019/5/13 15:19
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
public enum StartModelEnum implements BaseTypeIntegerEnum {


    /**
     * 监管平台
     * <p>
     * 用户开启充电的方式
     * 0: 未知
     * 1: 市级平台启动
     * 2：有卡启动
     * 3：其他无卡启动
     *
     * <p>
     * 运营平台
     * 0-app余额支付 1-电卡支付 2企业账户
     */

    KV_APP(0, 3),
    KV_CARD(1, 2),
    KV_ENTERPRISE(2, 3);


    private Integer key;
    private Integer value;

    StartModelEnum(Integer key, Integer value) {
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


    public static Integer getStartModelValue(Integer code) {
        if (code == null || "".equals(code)) {
            return null;
        }
        for (StartModelEnum e : StartModelEnum.values()) {
            if (code.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }
}
