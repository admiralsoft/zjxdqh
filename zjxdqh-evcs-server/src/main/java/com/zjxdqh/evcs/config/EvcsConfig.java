package com.zjxdqh.evcs.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yorking
 * @date 2019/05/06
 */
@Configuration
public class EvcsConfig {


    @Bean
    public Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.FULL;
    }



}
