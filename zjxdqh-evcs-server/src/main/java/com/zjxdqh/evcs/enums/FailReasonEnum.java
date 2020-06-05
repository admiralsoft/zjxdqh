package com.zjxdqh.evcs.enums;

/**
 * @outhor liusiyu
 * @create 2019-05-13-11:39
 */
public enum FailReasonEnum implements BaseTypeStateEnum {
    /**
     *
     *       监管平台
     * 停止失败原因
     * 0:无；
     * 1:此设备不存在；
     * 2:此设备离线：
     * 3:设备已停止充电；
     * 4～99:自定义

     *       运营平台
     * -8.桩缓存通道获取错误,
     * -7.桩号错误,
     * -6.枪号错误,
     * -5.余额不足,
     * -4.账户卡未绑定,
     * -3.枪故障,
     * -2.桩离线,
     * -1.未插枪，
     * 0.无操作
     * 1. 充满停止
     * 2. 主动停⽌
     * 3. 枪连接断开停⽌
     * 4. 故障停止（详情息）。
     * 5. 异常停⽌
     * 6. 余额不⾜停⽌
     * 7. 失电停⽌
     * 8.离线结算
     *
     */
    KV_CUSTOM_1(0,"4"),
    KV_CUSTOM_2(1,"0"),
    KV_CUSTOM_3(2,"0"),
    KV_CUSTOM_4(3,"5"),
    KV_CUSTOM_5(4,"6"),
    KV_CUSTOM_6(5,"7"),
    KV_CUSTOM_7(6,"8"),
    KV_CUSTOM_8(7,"2"),
    KV_CUSTOM_9(8,"9"),
    KV_CUSTOM_10(-1,"10"),
    KV_CUSTOM_11(-2,"2"),
    KV_CUSTOM_12(-3,"11"),
    KV_CUSTOM_13(-4,"12"),
    KV_CUSTOM_14(-5,"13"),
    KV_CUSTOM_15(-6,"1"),
    KV_CUSTOM_16(-7,"1"),
    KV_CUSTOM_17(-8,"14");


    private Integer key;
    private String value;

    FailReasonEnum(Integer key, String value) {
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
