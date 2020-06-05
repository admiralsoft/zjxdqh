package com.zjxdqh.evcs.interceptor.feign;

import com.zjxdqh.face.config.FeignInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author Yorking
 * @date 2019/05/05
 */

@Component("feignInterceptor")
@Log4j2
public class HappyFeignInterceptor implements FeignInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        log.info("HappyFeignInterceptor feign 请求地址： "+requestTemplate.url());
    }
}
