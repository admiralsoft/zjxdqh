
package com.tcp.config;

import com.tcp.config.props.RedisStandaloneProps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置
 */
@Configuration
@EnableCaching
@Import(RedisStandaloneProps.class)
public class RedisConfig {

    @Autowired
    private RedisStandaloneProps redisStandaloneProps;
	/**
	 * 缓存管理器
	 *
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {

		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(redisStandaloneProps.getTimeOut());
		return cacheManager;
	}

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisStandaloneProps.getMaxActive());
        jedisPoolConfig.setMaxIdle(redisStandaloneProps.getMaxIdle());
        jedisPoolConfig.setTestOnBorrow(redisStandaloneProps.getTestOnBorrow());
        jedisPoolConfig.setMinIdle(redisStandaloneProps.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisStandaloneProps.getMaxWait());
        return jedisPoolConfig;
    }

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisStandaloneProps.getIp());
        factory.setPort(redisStandaloneProps.getPort());
        if (StringUtils.isNotBlank(redisStandaloneProps.getPassword())) {
            factory.setPassword(redisStandaloneProps.getPassword());
        }
        factory.setTimeout(redisStandaloneProps.getTimeOut());
        factory.setUsePool(redisStandaloneProps.getUsePool());
        factory.setPoolConfig(jedisPoolConfig);
        return factory;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    public JdkSerializationRedisSerializer jdkSerializationRedisSerializer() {
        return new JdkSerializationRedisSerializer();
    }

    /**
     * 真正实现redis单机操作的bean
     *
     * @param factory
     * @param stringSerializer
     * @param objectSerializer
     * @return
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory factory, StringRedisSerializer stringSerializer, JdkSerializationRedisSerializer objectSerializer) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(objectSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(objectSerializer);
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }


    @Bean(name = "byteRedisTemplate")
    public RedisTemplate byteRedisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory factory, StringRedisSerializer stringSerializer, JdkSerializationRedisSerializer objectSerializer) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(new RedisSerializer<byte[]>() {
            @Override
            public byte[] serialize(byte[] bytes) throws SerializationException {
                return bytes;
            }
            @Override
            public byte[] deserialize(byte[] bytes) throws SerializationException {
                return bytes;
            }
        });

        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }
}
