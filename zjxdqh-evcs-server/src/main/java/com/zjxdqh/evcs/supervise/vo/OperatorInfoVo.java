package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: wangqinmin
 * @date: 2019/5/9 16:33
 * @description: 设备运营商信息
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class OperatorInfoVo {

    /**
     * 运营商 ID： 组织机构代码
     * 长度：9 字符
     */
    private String OperatorID;

    /**
     * 运营商名称：机构全称
     * 长度：<=64 字符
     */
    private String OperatorName;

    /**
     * 运营商客服电话1
     * 长度：字符串 <=32 字符
     */
    private String OperatorTel1;

    /**
     * 运营商客服电话2
     * 长度：字符串 <=32 字符
     */
    private String OperatorTel2;

    /**
     * 运营商注册地址
     * 长度：<=64 字符
     */
    private String OperatorRegAddress;

    /**
     * 备注信息
     * 长度：<=255 字
     */
    private String OperatorNote;
}
