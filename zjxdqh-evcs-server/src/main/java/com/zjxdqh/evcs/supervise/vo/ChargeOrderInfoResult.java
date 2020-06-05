package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Yorking
 * @date 2019/05/08
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChargeOrderInfoResult {


    /**
     * 订单号
     */
    private String StartChargeSeq;
    /**
     * 充电设备接口编码
     *
     *
     *
     */
    private String ConnectorID;
    /**
     * 确认结果
     * 0:成功
     * 1:争议交易
     * 2～99:自定义
     *
     */
    private Integer ConfirmResult;


}
