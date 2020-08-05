package com.tcp.tcp.base.message;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

public class BaseMessage<T extends BaseMessageBody> {


    /**
     * 包头
     */
    @Data(byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer markBegin = 1537 ;

    /**
     * 数据长度
     */
    @Data(order = 1, byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer dataLength;

    /**
     * 密钥更新时间
     */
    @Data(order = 2, byteType = DataByteTypEnum.Number, byteLen = 4)
    private Integer keyUptTime;

    /**
     * 设备iD
     */
    @Data(order = 3, byteType = DataByteTypEnum.ASCII, byteLen = 16)
    private String deviceNo;

    /**
     * 检验码
     */
    @Data(order = 5, byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer validCode;

    /**
     * 密文对象
     */
    @Data(order = 4, byteType = DataByteTypEnum.ChildData)
    private T data;

    /**
     * 包尾
     */
    @Data(order = 6, byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer markEnd = 3842 ;

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getKeyUptTime() {
        return keyUptTime;
    }

    public void setKeyUptTime(Integer keyUptTime) {
        this.keyUptTime = keyUptTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getValidCode() {
        return validCode;
    }

    public void setValidCode(Integer validCode) {
        this.validCode = validCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
