
package com.tcp.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil<T> {

	public static final int Default_Expir = 48*60*60;

	@Resource
	private RedisTemplate<String, T>	redisTemplate;

	/**
	 * 进行序列化否则会出现乱码
	 * 
	 * @return
	 */
	@Bean
	public RedisTemplate<String, T> redisTemplateInit() {

		// 设置序列化Key的实例化对象
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 设置序列化Value的实例化对象
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}

	// public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate)
	// {
	// RedisSerializer<?> stringSerializer = new StringRedisSerializer();
	// redisTemplate.setKeySerializer(stringSerializer);
	// redisTemplate.setValueSerializer(stringSerializer);
	// redisTemplate.setHashKeySerializer(stringSerializer);
	// redisTemplate.setHashValueSerializer(stringSerializer);
	// this.redisTemplate = redisTemplate;
	// }

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {

		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(String cacheName, final String pattern) {

		Set<String> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(cacheName + keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(String cacheName, final String key) {

		if (exists(cacheName, key)) {
			redisTemplate.delete(cacheName + key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String cacheName, final String key) {

		return redisTemplate.hasKey(cacheName + key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public T get(String cacheName, final String key) {

		T result = null;
		ValueOperations<String, T> operations = redisTemplate.opsForValue();
		result = operations.get(cacheName + key);
		if (result == null) { return null; }
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String cacheName, String key, T value) {

		boolean result = false;
		try {
			ValueOperations<String, T> operations = redisTemplate.opsForValue();
			operations.set(cacheName + key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setValueExpire(String cacheName, String key, T value) {

		boolean result = false;
		try {
			ValueOperations<String, T> operations = redisTemplate.opsForValue();
			operations.set(cacheName + key, value, Default_Expir, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String cacheName, final String key, T value, Long expireTime) {

		boolean result = false;
		try {
			ValueOperations<String, T> operations = redisTemplate.opsForValue();
			operations.set(cacheName + key, value);
			redisTemplate.expire(cacheName + key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 存放MAP
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean hmset(String cacheName, String key, Map<Object, Object> value) {

		boolean result = false;
		try {
			redisTemplate.opsForHash().putAll(cacheName + key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取MAP
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hmget(String cacheName, String key) {

		Map<Object, Object> result = null;
		try {
			result = redisTemplate.opsForHash().entries(cacheName + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 键值 自增
	 * @param cacheName
	 * @param key
	 * @param expire		单位：TimeUnit.MINUTES
	 * @return
	 */
	public Integer getAndIncrement(String cacheName, String key, Integer expire) {
		ValueOperations<String, Integer> valueOperations = (ValueOperations<String, Integer>) redisTemplate.opsForValue();
		String ckey = cacheName + key;
		Integer val = null;
		try {
			valueOperations.increment(ckey, 1);
			val = Integer.valueOf(valueOperations.get(ckey));
			if (val <= 2) {
				redisTemplate.expire(ckey, expire, TimeUnit.MINUTES);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val != null ? val : 0;
	}
}
