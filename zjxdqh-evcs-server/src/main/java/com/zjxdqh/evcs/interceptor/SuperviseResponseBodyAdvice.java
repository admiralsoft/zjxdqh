package com.zjxdqh.evcs.interceptor;

import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.config.SuperviseConfig;
import com.zjxdqh.evcs.supervise.vo.BaseChargeSeqParam;
import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.tools.AESUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

/**
 * controller  返回值 加密， 及发生异常时 返回内容
 *
 * @author Yorking
 * @date 2019/08/05
 */
@RestControllerAdvice(basePackages = "com.zjxdqh.evcs.controller")
@Log4j2
public class SuperviseResponseBodyAdvice implements ResponseBodyAdvice<ResponseResult> {


    /**
     * 是否加密返回数据
     */
    @Value("${supervise.response.encode:true}")
    private boolean isEncode = true;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public ResponseResult beforeBodyWrite(ResponseResult responseResult, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        encrypt(serverHttpRequest, responseResult);
        return responseResult;
    }


    /**
     * 加密返回内容
     */
    private void encrypt(ServerHttpRequest req, ResponseResult respResult) {

        OperatorInfo operatorInfo = SuperviseUtils.OperatorInfo.get();
        if (operatorInfo != null
                && !StringUtils.isEmpty(operatorInfo.getSn())
                && respResult.getData() instanceof BaseChargeSeqParam) {
            ((BaseChargeSeqParam) respResult.getData()).setStartChargeSeq(operatorInfo.getSn());
        }

        if (isEncode && respResult != null && respResult.getRet() == ResponseResult.RET_OK) {
            String data = JsonUtils.toJsonString(respResult.getData());
            byte[] encrypt = AESUtils.encrypt(data.getBytes(), operatorInfo.getDataSecret().getBytes(), operatorInfo.getDataSecretIv().getBytes());
            respResult.setData(Base64.getEncoder().encodeToString(encrypt));
            SuperviseUtils.sign(respResult, operatorInfo.getSigSecret());
            if (log.isDebugEnabled()) {
                log.debug("第三方平台请求[{}] 请求结果: {} , 加密后:{}",req.getURI(),  data, JsonUtils.toJsonString(respResult));
            }
        }

    }


    @ExceptionHandler
    public ResponseResult<?> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("服务器异常拦截", e);
        return ResponseResult.error(ResponseResult.RET_ERROR, "服务器异常，请稍候再试");
    }


}
