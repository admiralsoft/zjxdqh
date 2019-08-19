package com.zjxdqh.tools.redis;

import com.zjxdqh.tools.lock.DistributeLock;
import com.zjxdqh.tools.lock.ThreadVariable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DistributeRedisLock implements DistributeLock {


    @Override
    public boolean tryLock(String key) {
        return tryLock(key, DistributeLock.DEFAULT_LOCK_TIME);
    }

    @Override
    public boolean tryLock(String key, int seconds) {
        long _startTime = System.currentTimeMillis();
        long _timeout = TIMEOUT < seconds ? TIMEOUT * 1000 : seconds * 1000;
        String lockKey = generateKey(key);
        try {
            while (System.currentTimeMillis() - _startTime < _timeout) {
                if (RedisTools.setIfAbent(lockKey, 1, _timeout)) {
                    ThreadVariable.put(key, "1");
                    return true;
                }
                TimeUnit.MILLISECONDS.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
            RedisTools.remove(lockKey);
        }
        return false;
    }

    @Override
    public void releaseLock(String key) {
        System.out.println("释放锁：" + key);
        if (ThreadVariable.get(key) != null) {
            ThreadVariable.remove(key);
            RedisTools.remove(generateKey(key));
        }
    }

    private String generateKey(String key) {
        return PREFIXLOCK + key;
    }

    public boolean trySemaphoreLock(String key) {
        return trySemaphoreLock(key, DistributeLock.DEFAULT_LOCK_TIME);
    }
    @Override
    public boolean trySemaphoreLock(String key, int seconds) {
        if (RedisTools.setIfAbent(generateKey(key), 1, seconds)) {
            ThreadVariable.put(key, "1");
            return true;
        }
        return false;
    }

    @Override
    public void releaseSemaphoreLock(String key) {
        if (ThreadVariable.get(key) != null) {
            ThreadVariable.remove(key);
            RedisTools.remove(generateKey(key));
        }
    }

}
