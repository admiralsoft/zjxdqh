package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Yorking
 * @date 2019/05/07
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class TokenResultVO {

    /**
     * 运营商标
     */
    private String OperatorID;

    /**
     * 成功状态（0成功，1失败）
     */
    private Integer SuccStat;

    /**
     * 获取的访问凭证，全局唯一凭证
     */
    private String AccessToken;

    /**
     * 凭证有效期，单位秒
     */
    private Integer TokenAvailableTime;

    /**
     * 失败原因
     */
    private Integer FailReason = 0;


    public static TokenResultVO tokenFail(String operatorID, Integer failReason) {
        TokenResultVO tokenResult = new TokenResultVO();
        tokenResult.setSuccStat(1);
        tokenResult.setOperatorID(operatorID);
        tokenResult.setFailReason(failReason);
        return tokenResult;
    }
}
