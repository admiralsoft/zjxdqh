package com.zjxdqh.evcs.supervise.vo;

import lombok.Data;

/**
 * 操作变量
 * @author Yorking
 * @date 2019/08/07
 */
@Data
public class OperatorInfo {

    /**
     *     监管平台
     */
    public static final int IS_SUPERVISE = 1;

    /**
     *  第三方全流程 平台
     */
    public static final int IS_THIRD_STAGE = 0;

    private Integer supersiveId ;
    /**
     * 平台组织代码
     */
    private String orgId;
    /**
     * 对接方运营组织代码
     */
    private String operatorId;
    /**
     * 秘钥
     */
    private String operatorSecret;
    /**
     * 签名秘钥
     */
    private String sigSecret;
    /**
     * 消息秘钥
     */
    private String dataSecret;
    /**
     * 消息加密向量
     */
    private String dataSecretIv;

    /**
     * 访问地址
     */
    private String operatorUrl;

    /**
     * 运营帐户user_id
     */
    private String accountUserId;

    /**
     * 是否是 监管平台（1是，0否（默认）)
     */
    private Integer regulatory = 0;

    /**
     * 第三方 临时订单号
     */
    private String sn;

}
