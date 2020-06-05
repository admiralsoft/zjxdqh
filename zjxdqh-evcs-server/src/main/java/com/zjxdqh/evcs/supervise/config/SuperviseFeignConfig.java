package com.zjxdqh.evcs.supervise.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.service.TokenService;
import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
import com.zjxdqh.evcs.supervise.vo.RequestResult;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.exception.BuzzException;
import com.zjxdqh.face.exception.ExceptionEnum;
import com.zjxdqh.face.service.HappyService;
import com.zjxdqh.face.vo.FOrder;
import com.zjxdqh.tools.AESUtils;
import com.zjxdqh.tools.DateUtils;
import com.zjxdqh.tools.annon.ExcludeComponentScan;
import com.zjxdqh.tools.redis.RedisTools;
import feign.*;
import feign.codec.Decoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Yorking
 * @date 2019/05/07
 */
@Configuration
@ExcludeComponentScan
@AutoConfigureAfter(SuperviseConfig.class)
@Log4j2
public class SuperviseFeignConfig {

    /**
     * accessToken 请求 路径
     */
    public static final String URL_QUERY_TOKEN = "/query_token";


    @Autowired
    private TokenService tokenService;

    @Autowired
    private HappyService happyService;


    //连接超时时间
    private static int connectTimeOutMillis = 3000;
    //读取超时时间
    private static int readTimeOutMillis = 12000;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(5000, 20000, 3);
    }

    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new MySpringDecoder(messageConverters);
    }

    private class MySpringDecoder extends SpringDecoder {
        MySpringDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
            super(messageConverters);
        }

        @Override
        public Object decode(final Response response, Type type)
                throws IOException, FeignException {
            Object result = super.decode(response, type);

            if (result instanceof ResponseResult) {
                ResponseResult responseResult = (ResponseResult) result;
                OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
                if (responseResult.getRet() == ResponseResult.RET_TOKEN_ERROR) {
                    tokenService.clearToToken(operatorInfo.getOperatorId());
                    throw new RetryableException("token过期", response.request().httpMethod(), null);
                }
                if (responseResult.getRet() != ResponseResult.RET_OK) {
                    log.error("请求第三方平台[{}] 返回数据: {}", response.request().url(), JsonUtils.toJsonString(responseResult));
                }
                if (StringUtils.hasLength(responseResult.getSig())) {
                    boolean checkSign = SuperviseUtils.checkSign(responseResult, operatorInfo.getSigSecret());
                    log.debug("evcs平台验证 第三方平台返回数据的签名结果:{} ", checkSign);
                    Object data = responseResult.getData();
                    try {
                        if (checkSign && (data instanceof String) && StringUtils.hasLength((String) data)) {
                            byte[] dataBytes = Base64.getDecoder().decode((String) data);
                            byte[] decrypt = AESUtils.decrypt(dataBytes, operatorInfo.getDataSecret().getBytes(), operatorInfo.getDataSecretIv().getBytes());
                            String jsonData = new String(decrypt, StandardCharsets.UTF_8);

                            log.info("请求第三方平台[{}] 返回数据: {}", response.request().url(), jsonData);
                            try {
                                if (type instanceof ParameterizedType) {
                                    Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
                                    if (typeArguments.length == 1) {
                                        Class<?> returnClass = (Class<?>) typeArguments[0];
                                        responseResult.setData(JsonUtils.parse(jsonData, returnClass));
                                    }
                                }
                            } catch (Exception e) {
                                log.error("第三方平台返回DATA数据 JSON转换异常, ", e);
                            }
                        }
                    } catch (Exception e) {
                        log.error("evcs平台返回数据的DATA内容解密失败", e);
                    }
                }
            }
            return result;
        }
    }

    class CustomDoubleSerialize extends JsonSerializer<Number> {
        @Override
        public void serialize(Number value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (value != null) {
                // ROUND_HALF_UP四舍五入
                jsonGenerator.writeString(BigDecimal.valueOf(Double.valueOf(value.toString())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
    }


    @Bean("SuperviseRequestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
           // OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
            //封住参数
            OperatorInfo operatorInfo=new OperatorInfo();
            //平台运营商组织代码
            operatorInfo.setOrgId("MA6DE1HK0");
            //对接方运营组织代码
            operatorInfo.setOperatorId("MA005DBW1");
            //对接方运营商密匙
            operatorInfo.setOperatorSecret("8hrC6kkEOhJYQuNy");
            //签名密匙
            operatorInfo.setSigSecret("8Mqnz4LZMxvofGkm");
            //数据加密密匙
            operatorInfo.setDataSecret("8veZrSgwSN1eSlxE");
            //加密始量
            operatorInfo.setDataSecretIv("8iaxPfKkgAocqQ10");
            //请求接口如url
            operatorInfo.setOperatorUrl("https://chargehubws.fleetingpower.com/evcs/v1.0/");

            if (operatorInfo == null || StringUtils.isEmpty(operatorInfo.getOperatorUrl())) {
                log.error("请求第三方平台的URL配置为: {}", operatorInfo != null ? operatorInfo.getOperatorUrl() : "NULL");
                throw new BuzzException(ExceptionEnum.ERROR_PARAM);
            }
            //设置 第三方推送的 基本路径
            requestTemplate.target(operatorInfo.getOperatorUrl());
            String reqBody = requestTemplate.requestBody().asString();


            // 是否有 第三方 对接订单号
            //=====封装第三方订单号=======
            HashMap<String, Object> param = JsonUtils.parse(reqBody, HashMap.class);
           operatorInfo.setSn(happyService.findFOderBySn(param.get("StartChargeSeq").toString()).getSuperviseSn());

            if (reqBody.contains("StartChargeSeq") && !StringUtils.isEmpty(operatorInfo.getSn())) {
               // HashMap<String, Object> param = JsonUtils.parse(reqBody, HashMap.class);
                if (param != null) {
                    param.put("StartChargeSeq", operatorInfo.getSn());

                    reqBody = JsonUtils.toJsonString(param);
                }
            }

            // 封装请求参数的公共部分
            RequestResult req = new RequestResult();
            req.setOperatorID(operatorInfo.getOrgId());
            req.setTimeStamp(DateUtils.getNowString(DateUtils.YMDHMS));

            // 加密参数内容
            byte[] secretData = AESUtils.encrypt(reqBody.getBytes(),
                    operatorInfo.getDataSecret().getBytes(), operatorInfo.getDataSecretIv().getBytes());
            req.setData(Base64.getEncoder().encodeToString(secretData));
            req.setSeq("0001");

            req = SuperviseUtils.sign(req, operatorInfo.getSigSecret());

            try {
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(req);
                requestTemplate.body(Request.Body.encoded(json.getBytes(), requestTemplate.requestCharset()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            log.info("evcs平台 请求[{}] 数据内容:{}, 加密后:{}", requestTemplate.url(), reqBody, requestTemplate.requestBody().asString());
            String url = requestTemplate.url();
            if (!url.contains(URL_QUERY_TOKEN)) {
                // 对于 非token获取请求， 需要在head中 添加 token认证信息
                requestTemplate.header("Authorization", Collections.emptyList());
                requestTemplate.header("Authorization",
                        "Bearer " + tokenService.getAccessToken(operatorInfo));
            }
        };
    }


}
