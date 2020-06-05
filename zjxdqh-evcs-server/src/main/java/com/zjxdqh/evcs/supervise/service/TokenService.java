package com.zjxdqh.evcs.supervise.service;

import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.config.SuperviseConfig;
import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.supervise.vo.TokenRequestParam;
import com.zjxdqh.evcs.supervise.vo.TokenResultVO;
import com.zjxdqh.tools.redis.RedisTools;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author Yorking
 * @date 2019/05/07
 */
@Service
@Log4j2
public class TokenService {

    @Autowired
    private SuperviseService superviseService;

    @Value("${supervise.token.to_cache_key_pre}")
    private String toTokenKeyPre;
    @Value("${supervise.token.from_cache_key_pre}")
    private String fromTokenKeyPre;

    @Value("${supervise.token.availableTime}")
    private int availableTime;

    @Autowired
    private SuperviseConfig superviseConfig;

    /**
     * 获取 监管平台访问的accessToken
     *
     * @return
     */
    public synchronized String getAccessToken(OperatorInfo operatorInfo) {
        String accessTokenKey = toTokenKeyPre + ":" + operatorInfo.getOperatorId();

        //String accessToken = (String) RedisTools.get(accessTokenKey);
        String accessToken="OXQEyw1sF0sq+gM/qfPkAGfnG15Z46qmpkAqNPlCVKguYSUklASKqrPMSNwJ/sj/xwBo4i1RhdeT0EVHpqkH2OITW/916imx72mWu0391yM=";

        if (StringUtils.isEmpty(accessToken)) {
            log.info("运营商[{}] accessToken 未找到， 需要重新获取 accessToken", operatorInfo.getOperatorId());
            TokenRequestParam tokenParam = new TokenRequestParam();
            tokenParam.setOperatorID(operatorInfo.getOrgId());
            tokenParam.setOperatorSecret(operatorInfo.getOperatorSecret());
            ResponseResult token = superviseService.getToken(tokenParam);
            TokenResultVO tokenData = (TokenResultVO) token.getData();

            // 保存 accessToken 至 缓存中
            if (tokenData != null && StringUtils.hasLength(tokenData.getAccessToken())) {
                accessToken = tokenData.getAccessToken();
                RedisTools.set(accessTokenKey, tokenData.getAccessToken(), tokenData.getTokenAvailableTime() - 10);
            }
        } else {
            log.info("从缓存中获取到accessToken:{}", accessToken);
        }
        return accessToken;
    }

    public boolean clearToToken(String operatorId) {
        String accessTokenKey = toTokenKeyPre + ":" + operatorId;
        return RedisTools.remove(accessTokenKey);
    }

    public boolean checkAccessToken(String operatorId, String token) {
        String accessTokenKey = fromTokenKeyPre + ":" + operatorId;
        String accessToken = (String) RedisTools.get(accessTokenKey);
        return (StringUtils.hasLength(accessToken) && accessToken.equalsIgnoreCase(token));
    }

    /**
     * 创建 运营商 访问的授权 token
     *
     * @param operatorId
     * @return
     */
    public TokenResultVO createAccessToken(String operatorId) {
        TokenResultVO resultVO = new TokenResultVO();
        String accessTokenKey = fromTokenKeyPre + ":" + operatorId;
        String accessToken = UUID.randomUUID().toString().replace("-", "");
        resultVO.setAccessToken(accessToken);
        resultVO.setOperatorID(SuperviseUtils.OperatorInfo.get().getOrgId());
        resultVO.setTokenAvailableTime(availableTime);
        resultVO.setSuccStat(0);
        RedisTools.set(accessTokenKey, accessToken);
        return resultVO;
    }

}
