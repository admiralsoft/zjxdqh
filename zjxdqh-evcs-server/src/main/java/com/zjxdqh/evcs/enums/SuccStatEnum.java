package com.zjxdqh.evcs.enums;

/**
 * @outhor liusiyu
 * @create 2019-05-13-11:34
 */
public enum SuccStatEnum implements BaseTypeStateEnum {
    /**
     *
     *       监管平台
     * 成功标识
     * 0：成功
     * 1：失败
     *       运营平台
     * 1：成功
     * -：失败
     *
     */
    KV_SUCCEED(0,"1"),
    KV_FAILED(1,"-1");

    private Integer key;
    private String value;

    SuccStatEnum(Integer key, String value) {
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

    public static Integer getPileGunStatusValue(Integer code){
        if(code==null || "".equals(code)){
            return null;
        }
        for (PileGunStatusEnum e : PileGunStatusEnum.values()) {
            if (code.equals(e.getKey())) {
                return Integer.parseInt(e.getValue());
            }
        }
        return null;
    }
}
