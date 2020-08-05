package com.tcp.core.enums;

import com.tcp.core.code.MQCode;

public enum MQCodeEnum{

    CHARGING(MQCode.CHARGING, MQ2QueneEnum.ToOperation),
    ORDER_CHARGE(MQCode.ORDER_CHARGE, MQ2QueneEnum.ToOperation), // 预约充电
    SEARCH_O (MQCode.SEARCH_O, MQ2QueneEnum.ToOperation), // 查询预约
    RECORD_SEARCH (MQCode.RECORD_SEARCH, MQ2QueneEnum.ToOperation), // 查询记录
    DOWNLOAD_SEARCH (MQCode.DOWNLOAD_SEARCH, MQ2QueneEnum.ToOperation), // 下载查询
    CONFIG (MQCode.CONFIG, MQ2QueneEnum.ToMainTain), // 配置电桩参数
    PILE_HEART (MQCode.PILE_HEART, MQ2QueneEnum.ToMainTain), // 设置电桩心跳107,5110000000000004(桩编号),time1,time2
//    PILE_HEART_RESULT (MQCode.PILE_HEART_RESULT, MQ2QueneEnum.ToMainTain), // 设置电桩心跳120,5110000000000004(桩编号),result
    RESTART_PILE (MQCode.RESTART_PILE, MQ2QueneEnum.ToMainTain), // 重启电桩
    CONFIG_SYS (MQCode.CONFIG_SYS, MQ2QueneEnum.ToMainTain), // 平台远程配置桩体数据
    CONFIG_TIME_PRICE (MQCode.CONFIG_TIME_PRICE, MQ2QueneEnum.ToMainTain), // 设置时间段以及费率
    IS_UPDATE (MQCode.IS_UPDATE, MQ2QueneEnum.ToMainTain), // 更新是否成功
    UPDATE_VERSION (MQCode.UPDATE_VERSION, MQ2QueneEnum.ToMainTain), // 版本检测更新
    NET_UPDATE (MQCode.NET_UPDATE, MQ2QueneEnum.ToMainTain), // 开始更新
    PACKAGE_UPDATE (MQCode.PACKAGE_UPDATE, MQ2QueneEnum.ToMainTain), // 数据包更新

    GUN_STATES (MQCode.GUN_STATES, MQ2QueneEnum.ToOperation), // 桩状态 115,5110000000000004(桩编号),2(枪编号),1(管理状态),1(枪状态)
    START_RESULT (MQCode.START_RESULT, MQ2QueneEnum.ToOperation), //0x1002 充电启动、结束成功失败 116,5110000000000004(桩编号),2(枪编号),11(启动结果),0000(已充电量)
    CHARGING_INFO (MQCode.CHARGING_INFO, MQ2QueneEnum.ToOperationCharging), // 充电中数据 117,5110000000000004(桩编号),2(枪编号),sn(订单号),soc,剩余时间,电压,电流,充电时长,充电电费
    SIGN_CARD (MQCode.SIGN_CARD, MQ2QueneEnum.ToOperation), // 帐号卡验证118,5110000000000004(桩编号),2(枪编号),100(金额),000000000(卡号),1111111(vin)
    SIGN_CARD_RESULT (MQCode.SIGN_CARD_RESULT, MQ2QueneEnum.ToOperation), // 帐号卡 验证结果119,5110000000000004(桩编号),2(枪编号),000000000(卡号),0(验证结果),100(预付费余额),订单号
    UPDATE_TIME (MQCode.UPDATE_TIME, MQ2QueneEnum.ToMainTain), //校时命令
    END_DATA(MQCode.END_DATA, MQ2QueneEnum.ToOperation),//结算数据
    BLACKLIST_SEND (MQCode.BLACKLIST_SEND, MQ2QueneEnum.ToOperation),//结算数据
    UPGRADE_REQUEST (MQCode.UPGRADE_REQUEST, MQ2QueneEnum.ToMainTain),//升级请求(数据包升级)
    QUERY_CONFIG (MQCode.QUERY_CONFIG, MQ2QueneEnum.ToMainTain),//查询配置信息
    OFFLINE_INFO (MQCode.OFFLINE_INFO, MQ2QueneEnum.ToOperation),
    QUERY_UPDATE_RESULT (MQCode.QUERY_UPDATE_RESULT, MQ2QueneEnum.ToMainTain),//查询软件/固件更新是否成功命令
    KEY_UPDATE (MQCode.KEY_UPDATE, MQ2QueneEnum.ToMainTain),//发送密钥更新命令
    QUERY_DOWN_RESULT (MQCode.QUERY_DOWN_RESULT, MQ2QueneEnum.ToMainTain),//运营平台查询软件/固件下载是否成功命令
    QUERY_LOG(MQCode.QUERY_LOG, MQ2QueneEnum.ToMainTain),//查询记录

    PACKDATA_DOWN (MQCode.PACKDATA_DOWN, MQ2QueneEnum.ToMainTain),//0x0118 运营平台发送充电桩软件升级命令(数据包形式升级)
    GUN_TIME(MQCode.GUN_TIME, MQ2QueneEnum.ToOperationCharging),//定时数据
    ;


    private int code;
    private MQ2QueneEnum toQuene;
    MQCodeEnum(int code, MQ2QueneEnum toQuene){
        this.code = code;
        this.toQuene = toQuene;
    }

    public MQ2QueneEnum getToQuene(){
        return toQuene;
    }

    public static MQCodeEnum getCodeEnum(int mqCode) {
        for (MQCodeEnum e : MQCodeEnum.values()) {
            if (e.code == mqCode) {
                return e;
            }
        }
        return null;
    }
}