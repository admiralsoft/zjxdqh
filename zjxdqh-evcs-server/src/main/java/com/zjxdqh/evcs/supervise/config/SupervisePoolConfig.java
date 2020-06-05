package com.zjxdqh.evcs.supervise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * 推送 第三方平台的 线程池 配置
 *
 * @author Yorking
 * @date 2019/08/16
 */
@Configuration
public class SupervisePoolConfig {

    @Value("${supervise.pool.corePoolSize:10}")
    public int corePoolSize;

    @Value("${supervise.pool.maxPoolSize:20}")
    public int maxPoolSize;
    @Value("${supervise.pool.queueCapacity:100}")
    public int queueCapacity;
    @Value("${supervise.pool.keepAliveSeconds:60}")
    public int keepAliveSeconds;
    @Value("${supervise.pool.awaitTerminationSeconds:60}")
    public int awaitTerminationSeconds;


    @Bean("pushTaskExecutor")
    public Executor pushTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("pushTaskPool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

//        优雅释放资源
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);

        return executor;
    }
}
