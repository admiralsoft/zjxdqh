package com.tcp.core.retry;

/**
 * @author Yorking
 * @date 2019/02/21
 */
public class RetryRecord {


    /**
     * 当前重试次数
     */
    private int current = 1;

    /**
     * 最大重试次数
     */
    private int maxCount;

    /**
     * 缓存 中 重试 指令的 key值
     */
    private String retryKey;

    /**
     * 过期时间（单位：毫秒）
     */
    private long expire;


    public RetryRecord() {

    }

    public RetryRecord(int maxCount, String retryKey) {
        this.maxCount = maxCount;
        this.retryKey = retryKey;
    }

    public RetryRecord(int maxCount, String retryKey, long expire) {
        this.maxCount = maxCount;
        this.retryKey = retryKey;
        this.expire = expire;
    }



    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getRetryKey() {
        return retryKey;
    }

    public void setRetryKey(String retryKey) {
        this.retryKey = retryKey;
    }


    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
