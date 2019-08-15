package com.zjxdqh.tools.lock;

public interface DistributeLock {

    /**
     * 分布式锁前缀
     */
    public final static String PREFIXLOCK = "DistributeLock:";


    /**
     * 默认锁持有时间(单位:秒)
     */
    public final static int DEFAULT_LOCK_TIME = 300;

    /**
     * 分布式获取超时时间
     * 注如果锁有效期比超时时间短，则使用锁有效期时间
     */
    public final static long TIMEOUT = 10;

    /**
     * 分布式竞争锁(分布式锁请求等待)
     *
     * @param key
     * @param seconds 锁有效期单位为秒
     * @return
     */
    public boolean tryLock(String key, int seconds);

    /**
     * 释放锁
     * 特别注意，锁的释放必须和获取在同一线程里。
     * 意思是，当前线程的锁，必须当前线程释放，别的线程不能释放。
     *
     * @param key
     */
    public void releaseLock(String key);

    /**
     * 分布式唯一锁(请求不等待)
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean trySemaphoreLock(String key, int seconds);

    /**
     * 获取信号锁
     *
     * @param key
     */
    public void releaseSemaphoreLock(String key);
}
