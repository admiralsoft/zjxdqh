package com.tcp.core.enums;

import com.tcp.core.code.TCPCode;

/**
 * @Author TcT
 */
public interface DictCodeEnum {

    enum OrderStat{
        /**
         *
         */
        ORDER_CDZ(0, "充电中"),
        ORDER_JSZ(4, "结算中"),
        ORDER_QDZ(3, "启动中"),
        ORDER_YSX(-1, "已失效"),
        ORDER_YWC(1, "已完成"),
        ORDER_ZBZ(2, "准备中");

        private int code;

        private String message;

        OrderStat(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static OrderStat parseCode(Integer code){
            for(OrderStat type:OrderStat.values()){
                if(type.code ==code){
                    return type;
                }
            }
            return null;
        }
    }

    enum OrderEndStat{
        /**
         *
         */
        END_CMTZ(1,"充满停止"),
        END_ZDTZ(2,"主动停止"),
        END_QLJDK(3,"枪连接断开"),
        END_GZ(4,"故障"),
        END_YCTZ(5,"异常停止"),
        END_YEBZ(6,"余额不足"),
        END_SD(7,"失电");

        private int code;

        private String message;

        OrderEndStat(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static OrderEndStat parseCode(Integer code){
            for(OrderEndStat type:OrderEndStat.values()){
                if(type.code ==code){
                    return type;
                }
            }
            return null;
        }
    }

    enum OrderSettlementStat{
        /**
         * 正常结算
         */
        SET_ZC(1,"正常结算"),
        /**
         * 手动结算
         */
        SET_SD(2,"手动结算"),
        /**
         * 离线结算
         */
        SET_LX(3,"离线结算");


        private int code;

        private String message;

        OrderSettlementStat(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static OrderSettlementStat parseCode(Integer code){
            for(OrderSettlementStat type:OrderSettlementStat.values()){
                if(type.code ==code){
                    return type;
                }
            }
            return null;
        }
    }


    /**
     * 充电状态字典
     */
    enum ChargeStat{
        /**
         *
         */
        NULL(1,"空闲"),
        GUN_LINE(2,"充电枪已连接，未启动充电"),
        LOADING(3,"启动中 （已发启动命令，等待充电枪连接 。或者启动充电的过程都定义为启动中）"),
        CHARGING(4,"充电中"),
        CHARGED(5,"充电完成"),
        ORDERED(6,"已预约"),
        WATING(7,"预约完成待充电"),
        ;


        private int code;

        private String message;

        ChargeStat(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static OrderSettlementStat parseCode(Integer code){
            for(OrderSettlementStat type: OrderSettlementStat.values()){
                if(type.code ==code){
                    return type;
                }
            }
            return null;
        }
    }


    /**
     * 采集下发指令 结果
     */
    enum SendMessageResult{

        /**
         * 无下发渠道
         */
        NO_CHANNEL(0),
        /**
         * 非此台服务
         */
        NO_HOST(-1),
        /**
         * 下发成功
         */
        SEND_OK(1),
        /**
         * 下发失败
         */
        SEND_FAIL(2);


        private int code;

        SendMessageResult(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }


    /**
     * 采集协议 上传 下达 指令对 映射 关系
     */
    enum SendRecievCodeMap{

        /**
         * 分时电价 指令映射对
         */
        PRESCRIBED_RATE(TCPCode.PILE_PRESCRIBED_RATE, TCPCode.prescribed_rate);

        /**
         * 下发指令
         */
        private short sendCode;
        /**
         * 上传指令
         */
        private short recieveCode;

        SendRecievCodeMap(short sCode, short revieceCode) {
            this.sendCode = sCode;
            this.recieveCode = revieceCode;
        }


        public static short getRecieveCode(short sendCode) {
            for (SendRecievCodeMap value : SendRecievCodeMap.values()) {
                if (value.sendCode == sendCode) {
                    return value.recieveCode;
                }
            }
            return 0;
        }


        public static short getSendCode(short revieceCode) {
            for (SendRecievCodeMap value : SendRecievCodeMap.values()) {
                if (value.recieveCode == revieceCode) {
                    return value.sendCode;
                }
            }
            return 0;
        }



    }


}
