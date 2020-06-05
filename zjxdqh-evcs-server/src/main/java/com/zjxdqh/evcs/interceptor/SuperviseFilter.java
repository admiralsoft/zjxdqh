package com.zjxdqh.evcs.interceptor;

import com.zjxdqh.evcs.service.EvcsService;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.config.SuperviseConfig;
import com.zjxdqh.evcs.supervise.config.SuperviseFeignConfig;
import com.zjxdqh.evcs.supervise.service.TokenService;
import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
import com.zjxdqh.evcs.supervise.vo.RequestResult;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.tools.AESUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Yorking
 * @date 2019/05/08
 */

@WebFilter(urlPatterns = "/*", filterName = "authFilter")
@Log4j2
public class SuperviseFilter implements Filter {


    /**
     * 是否验证请求数据
     */
    @Value("${supervise.request.auth:true}")
    private boolean isAuth = true;

    @Autowired
    private SuperviseConfig superviseConfig;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EvcsService evcsService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        SuperviseRequestWrapper request = new SuperviseRequestWrapper((HttpServletRequest) servletRequest);
        SuperviseResponseWrapper response = new SuperviseResponseWrapper((HttpServletResponse) servletResponse);

        String data = request.getData();
        String uri = request.getRequestURI();

        uri = uri.substring(uri.lastIndexOf("/"));

        log.debug("监管平台 请求路径[{}]", uri);
        RequestResult requestBody;
        if (StringUtils.isEmpty(data) || (requestBody = JsonUtils.parse(data, RequestResult.class)) == null
                || StringUtils.isEmpty(requestBody.getOperatorID())) {
            resp(response, ResponseResult.error(ResponseResult.RET_BUZZ_ERROR, "OperatorID not Find!"));
            return;
        }
        OperatorInfo supersiveInfo = evcsService.getSupersiveInfo(requestBody.getOperatorID());
        if (supersiveInfo == null) {
            resp(response, ResponseResult.error(ResponseResult.RET_BUZZ_ERROR, "OperatorID not Find!"));
            return;
        }
        SuperviseUtils.OperatorInfo.set(supersiveInfo);

        // 非获取 TOKEN 请求，则需要验证TOKEN信息
        String auth = getAuth(request);
        if (!SuperviseFeignConfig.URL_QUERY_TOKEN.equalsIgnoreCase(uri)) {
            if (isAuth && !StringUtils.hasLength(auth)) {
                resp(response, ResponseResult.error(ResponseResult.RET_PARAM_ERROR, "请求head 缺少Authorization"));
                return;
            }

            if (isAuth && !checkToken(auth, requestBody.getOperatorID())) {
                resp(response, ResponseResult.error(ResponseResult.RET_TOKEN_ERROR, "Token 未找到或已过期!"));
                return;
            }


        }
        if (!SuperviseUtils.checkSign(requestBody, supersiveInfo.getSigSecret())) {
            // 签名验证不通过
            resp(response, ResponseResult.error(ResponseResult.RET_SIGN_ERROR, "签名验证不通过"));
            return;
        }

        if (requestBody.getData() != null
                && requestBody.getData() instanceof String) {
            // 解密 DATA
            byte[] decrypt = AESUtils.decrypt(Base64.getDecoder().decode((String) requestBody.getData()), supersiveInfo.getDataSecret().getBytes(), supersiveInfo.getDataSecretIv().getBytes());
//                String content = new String(decrypt, StandardCharsets.UTF_8);
            request.setData(decrypt);

        }

        filterChain.doFilter(request, response);

    }

    private void resp(HttpServletResponse response, ResponseResult responseResult) throws IOException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        response.resetBuffer();

        try (ServletOutputStream writer = response.getOutputStream()) {
            String cxt = JsonUtils.toJsonString(responseResult);
            log.debug("请求返回结果: {}", cxt);
            writer.write(cxt.getBytes());
            writer.flush();
        }

    }

    private boolean checkToken(String accessToken, String operatorId) {
        return tokenService.checkAccessToken(operatorId, accessToken);
    }

    private String getAuth(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (StringUtils.hasLength(auth) && auth.indexOf("Bearer ") == 0) {
            auth = auth.substring(7);
            log.debug("第三方请求[{}] token值 : [{}]",SuperviseUtils.OperatorInfo.get().getOperatorId(), auth);
            return auth;
        }
        return null;
    }


    /**
     * 包装请求的数据流
     */
    private class SuperviseRequestWrapper extends HttpServletRequestWrapper {

        private byte[] data;

        public SuperviseRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);

            data = IOUtils.toByteArray(request.getInputStream());
        }

        public String getData() {
            return new String(data);
        }

        public void setData(byte[] data) {
            this.data = data;
        }

        @Override
        public ServletInputStream getInputStream() {

            return new MyServletInputStream(new ByteArrayInputStream(data));
        }

        class MyServletInputStream extends ServletInputStream {
            private InputStream inputStream;

            public MyServletInputStream(InputStream inputStream) {
                this.inputStream = inputStream;
            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        }
    }

    /**
     * 包装 response返回的数据流
     */
    class SuperviseResponseWrapper extends HttpServletResponseWrapper {
        /**
         * 我们的分支流
         */
        private ByteArrayOutputStream output;
        private ServletOutputStream filterOutput;

        public SuperviseResponseWrapper(HttpServletResponse response) {
            super(response);
            output = new ByteArrayOutputStream();
        }

        /**
         * 利用TeeOutputStream复制流，解决多次读写问题
         * 用super.getOutputStream来获取源outputstream，也可以用注释的那种方式获取，传过来
         *
         * @return
         * @throws IOException
         */
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            if (filterOutput == null) {
                filterOutput = new ServletOutputStream() {

                    //拿父类的response，初始化的时候，里面还没有数据，只有一些request信息和response信息,但是调用了创建outputStream,
                    private TeeOutputStream teeOutputStream = new TeeOutputStream(SuperviseResponseWrapper.super.getOutputStream(), output);

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setWriteListener(WriteListener writeListener) {

                    }

                    @Override
                    public void write(int b) throws IOException {
                        teeOutputStream.write(b);
                    }

                    @Override
                    public void flush() throws IOException {
                        super.flush();
                    }
                };
            }
            return filterOutput;
        }

        public byte[] toByteArray() {
            return output.toByteArray();
        }
    }

}
