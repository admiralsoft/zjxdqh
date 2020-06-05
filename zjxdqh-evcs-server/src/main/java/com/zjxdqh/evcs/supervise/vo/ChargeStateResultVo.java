package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: wangqinmin
 * @date: 2019/5/10 12:37
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChargeStateResultVo {

    /**
     * 充电订单号
     * 格式“运营商 ID+唯一编号”，27 字符
     */
    private String StartChargeSeq;

    /**
     * 操作结果
     * 0:成功；
     * 1:失败
     */
    private Integer SuccStat;
}

