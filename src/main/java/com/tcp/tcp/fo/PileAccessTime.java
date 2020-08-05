package com.tcp.tcp.fo;


/**
 * 记录桩最近通讯时间
 */
public class PileAccessTime {

    /**
     * 桩号
     */
    private String pileNum;
    /**
     * 最近通讯时间
     */
    private long   lastAccess;

    public String getPileNum() {
        return pileNum;
    }

    public void setPileNum(String pileNum) {
        this.pileNum = pileNum;
    }

    public long getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(long lastAccess) {
        this.lastAccess = lastAccess;
    }
}
