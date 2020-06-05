package com.zjxdqh.evcs.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zjxdqh.tools.DateUtils;
import com.zjxdqh.tools.annon.NoNumberScale;
import com.zjxdqh.tools.annon.NumberScale;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author Yorking
 * @date 2019/05/06
 */
@Configuration
@EnableWebMvc
@Log4j2
public class EvcsWebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置Date类型字段 序列化方式 （默认格式：yyyy-MM-dd HH:mm:ss）
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.YMD_HMS, Locale.SIMPLIFIED_CHINESE));

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigDecimal.class, new JsonNumberSerialize());
        simpleModule.addSerializer(Float.class, new JsonNumberSerialize());
        simpleModule.addSerializer(Double.class, new JsonNumberSerialize());
        objectMapper.registerModule(simpleModule);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converters.add(converter);
    }


    private class JsonNumberSerialize extends JsonSerializer<Number> {
        @Override
        public void serialize(Number value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
                Field field = ReflectionUtils.findField(jsonGenerator.getCurrentValue().getClass(), jsonGenerator.getOutputContext().getCurrentName());
                if (field.getAnnotation(NoNumberScale.class) != null) {
                    return;
                }
                NumberScale annotation = field.getAnnotation(NumberScale.class);
                int scale = 2;
                if (annotation != null) {
                    scale = annotation.value();
                }
                // ROUND_HALF_UP四舍五入
                jsonGenerator.writeString(BigDecimal.valueOf(Double.valueOf(value.toString())).setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
    }


//    @Bean
//    public AuthInterceptor getAuthInterceptor(){
//        return new AuthInterceptor();
//    }
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        log.debug("添加拦截器 AuthInterceptor。。");
//        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/query_token");
//    }
}
