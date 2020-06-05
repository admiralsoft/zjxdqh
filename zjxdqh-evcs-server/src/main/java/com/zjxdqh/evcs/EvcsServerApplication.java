package com.zjxdqh.evcs;

import com.zjxdqh.tools.annon.ExcludeComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author Yorking
 * @date 2019/05/06
 */
@SpringBootApplication
@ServletComponentScan
@EnableFeignClients(basePackages = {"com.zjxdqh.face", "com.zjxdqh.evcs.supervise.service"})
@ComponentScan(
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {ExcludeComponentScan.class})})
public class EvcsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvcsServerApplication.class, args);
    }
}
