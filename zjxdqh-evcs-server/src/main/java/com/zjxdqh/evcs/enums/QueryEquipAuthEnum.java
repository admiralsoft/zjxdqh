package com.zjxdqh.evcs.enums;

/**
 * @author: wangqinmin
 * @date: 2019/8/12 14:41
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
public enum QueryEquipAuthEnum implements BaseTypeIntegerEnum {
    /**
     * 运营系统
     * -2.离线
     * -1.故障
     * 0.空闲
     * 1.使用中 占用（未充电 ）
     * 3. 启动中 （已发启动命令，等待充电枪连接 。或者启动充电的过程都定义为启动中）
     * 4. 充电中
     * 5. 充电完成
     * 6. 已预约
     * 7. 等待充电中（预约已连接车，但未启动充电状态）
     * <p>
     * <p>
     * 请求设备认证状态：
     * 0： 无
     * 1： 此设备尚未插枪
     * 2： 设备检查失败
     * 3-99 自定义
     * * 3. 启动中 （已发启动命令，等待充电枪连接 。或者启动充电的过程都定义为启动中）
     * * 4. 充电中
     * * 5. 充电完成
     * * 6. 已预约
     * * 7. 等待充电中（预约已连接车，但未启动充电状态）
     */

    KV_FREE(0, 1),
    KV_OFFLINE(-2, 2),
    KV_FAULT(-1, 2),
    KV_STARTUPING(3, 3),
    KV_CHARGING(4, 4),
    KV_COMPLETED(5, 5),
    KV_YUYUE(6, 6),
    KV_WAIT(7, 7);

    private Integer key;
    private Integer value;

    QueryEquipAuthEnum(Integer key, Integer value) {
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

    public static Integer getQueryEquipAuthStateValue(Integer code) {
        if (code == null || "".equals(code)) {
            return null;
        }
        for (QueryEquipAuthEnum e : QueryEquipAuthEnum.values()) {
            if (code.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }
}