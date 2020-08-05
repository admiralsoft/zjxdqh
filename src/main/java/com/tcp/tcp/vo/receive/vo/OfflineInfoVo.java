package com.tcp.tcp.vo.receive.vo;


import com.tcp.bean.BOfflineChildinfo;
import com.tcp.bean.BOfflineinfo;

import java.util.LinkedList;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 上午11:59.
 * @Title:
 * @Description: 离线数据信息
 */
public class OfflineInfoVo extends BOfflineinfo implements java.io.Serializable{


    private static final long serialVersionUID = -5183452237429729317L;
    /**
     * 记录序号数据集
     */
    private LinkedList<BOfflineChildinfo> offlineInfos;


    public LinkedList<BOfflineChildinfo> getOfflineInfos() {
        return offlineInfos;
    }

    public void setOfflineInfos(LinkedList<BOfflineChildinfo> offlineInfos) {
        this.offlineInfos = offlineInfos;
    }
}
