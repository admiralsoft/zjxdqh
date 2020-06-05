package com.zjxdqh.evcs.enums;

/**
 * 采集枪状态
 *
 */
public enum PileGunCollectStatusEnum implements BaseTypeStateEnum{

    /**
     *
     监管平台
     Status
     0：无
     1：此设备尚未插抢
     2：设备检测失败
     3~99：自定义

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

    KV_1(1,"1"),
    KV_2(2,"0"),
    KV_3(3,"3"),
    KV_4(4,"4"),
    KV_5(5,"5"),
    KV_6(6,"6"),
    KV_7(7,"7"),
    KV_8(8,"8"),//离线
    KV_9(9,"9"),//停用
    KV_10(10,"10");//故障


    private Integer key;
    private String value;

    PileGunCollectStatusEnum(Integer key, String value) {
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

    public static Integer getPileGunCollectStatusValue(Integer code){
        if(code==null || "".equals(code)){
            return null;
        }
        for (PileGunCollectStatusEnum e : PileGunCollectStatusEnum.values()) {
            if (code.equals(e.getKey())) {
                return Integer.parseInt(e.getValue());
            }
        }
        return null;
    }


}
