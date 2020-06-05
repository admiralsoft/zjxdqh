package com.zjxdqh.evcs.enums;

/**
 * 充电设备接口状态
 *
 */
public enum PileGunStatusEnum implements BaseTypeStateEnum{


    /**
     *
            监管平台
     Status
     0：离网
     1：空闲
     2：占用（未充电 ）
     3：占用（充电中 ）
     4：占用（预约锁定 ）
     255：故障

             运营系统
     -2-离线
     -1-故障
     0-空闲
     4-充电中
     1-使用中 占用（未充电）充电枪已连接





     联盟协议：
     1. 空闲
     2. 充电枪已连接，未启动
     充电
     3. 启动中（已发启动命
     令，等待充电枪连接 。
     或者启动充电的过程都
     定义为启动中）
     4. 充电中
     5. 充电完成
     6. 已预约
     7. 等待充电中（预约已连
     接车，但未启动充电状
     态）
     （必选）
     *
     */

    KV_FREE(0,"1"),
    KV_USED(1,"2"),
    KV_FAULT(-1,"255"),
    KV_OFFLINE(-2,"0"),
    KV_CHARGING(4,"3"),
    KV_CONNECTED(2,"2"),
    KV_STARTUP(3,"2"),
    KV_FINISH(5,"2");

    private Integer key;
    private String value;

    PileGunStatusEnum(Integer key, String value) {
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
