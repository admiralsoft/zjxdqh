package com.zjxdqh.tools.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 *
 * Redis 工具操作类
 *
 * @author Yorking
 * @date 2019/05/06
 */
@Log4j2
public class RedisTools {


    private static RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisTools.redisTemplate = redisTemplate;
        log.info("RedisTools.redisTemplate 注入成功 "+ RedisTools.redisTemplate);
    }


    private static RedisTemplate getRedisTemplate(){
        if (RedisTools.redisTemplate == null) {
            throw new NullPointerException("RedisTools.redisTemplate 尚未注入成功， 无法使用");
        }
        return RedisTools.redisTemplate;
    }

    public static Object get(String key) {
        return getRedisTemplate().opsForValue().get(key);
    }

    public static void set(String key, Object value) {
        getRedisTemplate().opsForValue().set(key, value);
    }

    /**
     * 设置缓存 及过期时间
     * @param key
     * @param value
     * @param timeout   单位（秒）
     */
    public static void set(String key, Object value, long timeout) {
        getRedisTemplate().opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public static boolean exits(String key) {
        return getRedisTemplate().hasKey(key);
    }
    public static boolean remove(String key) {
        return getRedisTemplate().delete(key);
    }

    public static boolean setIfAbent(String key, Object value) {
        return getRedisTemplate().opsForValue().setIfAbsent(key, value);
    }
    public static boolean setIfAbent(String key, Object value, long sec) {
        return getRedisTemplate().opsForValue().setIfAbsent(key, value, sec, TimeUnit.SECONDS);
    }

}
