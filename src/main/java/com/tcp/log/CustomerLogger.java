package com.tcp.log;

import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.JsonObject;
import com.tcp.util.DataUtil;
import com.tcp.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CustomerLogger {

    /**
     * MQ消息类型： 发送消息
     */
    public static final String MQ_SEND = "push";

    /**
     * MQ消息类型： 接受消息
     */
    public static final String MQ_RECIEVE = "recieve";

    /**
     * 指令日志输出
     */
    private static Logger commandLogger = LoggerFactory.getLogger("commandLogger");
    /**
     * MQ日志输出
     */
    private static Logger mqLogger = LoggerFactory.getLogger("mqLogger");

    /**
     * 打印指令日志信息
     * @param tcpCode
     * @param pileNum
     * @param msg
     */
    public static void printCommandLogger(int tcpCode, String pileNum, Object msg) {
        CommandLoggerBody log = new CommandLoggerBody();
        log.setTcpCode(DataUtil.numToHex16(tcpCode));
        log.setPileNum(pileNum);
        log.setTime(DateUtil.format(new Date()));
        log.setMsg(msg);
        commandLogger.info(JSONObject.toJSONString(log));
    }

    /**
     * 打印 mq消息队列日志
     * @param mqCode
     * @param msg
     */
    public static void printMqLogger(int mqCode, String pileNum, Object msg) {
        MqLoggerBody log = new MqLoggerBody();
        log.setMqCode(String.valueOf(mqCode));
        log.setMqPileNum(pileNum);
        log.setMsg(msg);
        log.setTime(DateUtil.format(new Date()));
        mqLogger.info(JSONObject.toJSONString(log));
    }

    /**
     * 打印 mq消息队列日志
     * @param object
     */
    public static void printMqLogger(JsonObject object, String mqTyp, String correlationId) {
        MqLoggerBody log = new MqLoggerBody();
        log.setMqCode(String.valueOf(object.getCode()));
        log.setMqPileNum(object.getPileNum());
        log.setMqType(mqTyp);
        log.setMqCorrelationId(correlationId);
        log.setMsg(object);
        log.setTime(DateUtil.format(new Date()));
        mqLogger.info(JSONObject.toJSONString(log));
    }

    private static class MqLoggerBody{

        private String mqCode;
        private String mqPileNum;
        private String mqType;
        private String mqCorrelationId;
        /**
         * 消息处理时间
         */
        private String time;
        private Object msg;

        public String getMqCode() {
            return mqCode;
        }

        public void setMqCode(String mqCode) {
            this.mqCode = mqCode;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public String getMqPileNum() {
            return mqPileNum;
        }

        public void setMqPileNum(String mqPileNum) {
            this.mqPileNum = mqPileNum;
        }

        public String getMqType() {
            return mqType;
        }

        public void setMqType(String mqType) {
            this.mqType = mqType;
        }

        public String getMqCorrelationId() {
            return mqCorrelationId;
        }

        public void setMqCorrelationId(String mqCorrelationId) {
            this.mqCorrelationId = mqCorrelationId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    private static class CommandLoggerBody{
        /**
         * 指令号
         */
        private String tcpCode;
        /**
         * 桩号
         */
        private String pileNum;
        /**
         * 日志记录时间
         */
        private String time;
        /**
         * 指令内容
         */
        private Object msg;

        public String getTcpCode() {
            return tcpCode;
        }

        public void setTcpCode(String tcpCode) {
            this.tcpCode = tcpCode;
        }

        public String getPileNum() {
            return pileNum;
        }

        public void setPileNum(String pileNum) {
            this.pileNum = pileNum;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
