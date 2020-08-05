package com.tcp.tcp.vo.send;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 上午11:45.
 * @Title:
 * @Description: 离线处理信息
 */
public class SendOfflineInfo {

    /**
     * 记录序号
     */
    private int serialNum;

    /**
     * 处理结果
     */
    private int reusltCode;

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getReusltCode() {
        return reusltCode;
    }

    public void setReusltCode(int reusltCode) {
        this.reusltCode = reusltCode;
    }
}
