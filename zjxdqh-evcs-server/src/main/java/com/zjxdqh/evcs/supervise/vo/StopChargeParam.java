package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 停止充电请求
 *
 * @author Yorking
 * @date 2019/05/08
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StopChargeParam {

    /**
     * 充电订单号
     */
    private String StartChargeSeq;
    /**
     * 充电设备接口编码
     */
    private String ConnectorID;

}
