package com.tcp.tcp.vo.send.vo;

import com.tcp.tcp.vo.send.SendOfflineInfo;

import java.util.LinkedList;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 上午11:38.
 * @Title:
 * @Description: 处理确认回复离线数据信息包装类
 */
public class SendOfflineInfoVo {

    /**
     * 记录个数
     */
    private int num;


    /**
     * 数据集合
     */
    private LinkedList<SendOfflineInfo> sendOfflineInfos;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public LinkedList<SendOfflineInfo> getSendOfflineInfos() {
        return sendOfflineInfos;
    }

    public void setSendOfflineInfos(LinkedList<SendOfflineInfo> sendOfflineInfos) {
        this.sendOfflineInfos = sendOfflineInfos;
    }
}
