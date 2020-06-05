package com.zjxdqh.evcs.interceptor.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.exception.BuzzException;
import com.zjxdqh.face.exception.ExceptionEnum;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author Yorking
 * @date 2019/08/16
 */
@Configuration
@Log4j2
public class ExceptionErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        try {
            if (response.body() != null) {

                String body = Util.toString(response.body().asReader());

                log.error("feign 请求[{}]异常信息: {}",response.request().url(), body);
                HashMap map = JsonUtils.parse(body, HashMap.class);
                String  message = (String) map.get("message");
                if (body.contains(BuzzException.class.getCanonicalName())) {
                    String[] split = message.split(":");
                    if (split.length > 1) {
                        return new BuzzException(ExceptionEnum.getExcep(Integer.valueOf(split[1])));
                    }
                }
                return new BuzzException(message);

            }
        } catch (Exception var4) {
            log.error("feign 请求[{}] 内容:[{}] 异常信息: {}",
                    response.request().url(),
                    response.request().requestBody().asString(),
                    var4.getMessage());
            return new InternalException(var4.getMessage());
        }
        return new InternalException("系统异常,请联系管理员");
    }

}
