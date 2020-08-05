package com.tcp.tcp.vo.receive.vo;

import java.util.List;

/**
 * @author TcT
 *         Date: 2018/8/3.
 *         Time: 上午11:00.
 * @Title:
 * @Description: 故障码Vo类
 */
public class LogFaultCodeVo {

    /** 充电枪口号 **/
    private byte gunNum ;

    /** 故障码 **/
    private List<String> faultCode ;

    public byte getGunNum() {
        return gunNum;
    }

    public void setGunNum(byte gunNum) {
        this.gunNum = gunNum;
    }

    public List<String> getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(List<String> faultCode) {
        this.faultCode = faultCode;
    }
}
