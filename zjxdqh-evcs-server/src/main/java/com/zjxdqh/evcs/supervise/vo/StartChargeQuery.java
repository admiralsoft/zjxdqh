package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Yorking
 * @date 2019/08/12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StartChargeQuery {

    /**
     * 充电订单号
     */
    private String StartChargeSeq;
    /**
     * 充电设备
     */
    private String ConnectorID;

    /**
     * 二维码
     */
    private String QRCode;
}
