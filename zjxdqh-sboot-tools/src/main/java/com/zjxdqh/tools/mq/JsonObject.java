package com.zjxdqh.tools.mq;

import java.io.Serializable;

/**
 * @Author yaweiXu
 */
public class JsonObject implements Serializable {

    /** MQ消息唯一标识 */
    private String mqId;

    /** 头部信息枪号 **/
    private String pileNum;

    /** 头部信息ip**/
    private String ip;

    /** 头部信息mq协议code **/
    private int code;

    /** 头部信息时间戳**/
    private Long timestemp;


    /*请求结果*/
    private Boolean isSuccess;

    /*返回信息*/
    private String msg;

    /** 具体用的实体类可以在此转换参考m开头xxx来接受转换对应的属性 **/
    private Object obj;

    public Boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(Long timestemp) {
        this.timestemp = timestemp;
    }

    public String getPileNum() {
        return pileNum;
    }

    public void setPileNum(String pileNum) {
        this.pileNum = pileNum;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMqId() {
        return mqId;
    }

    public void setMqId(String mqId) {
        this.mqId = mqId;
    }
}
