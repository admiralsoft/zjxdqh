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
public class StartChargeQueryResult {

    /**
     * 充电订单号
     */
    private String StartChargeSeq;
    /**
     * 设备状态
     */
    private Integer StartChargeSeqStat;
    /**
     * 充电设备
     */
    private String ConnectorID;


    /**
     * 操作结果
     */
    private Integer SuccStat = 1;

    /**
     * 失败原因(1设备不存在，2设备已离线）
     */
    private Integer FailReason = 0;


}
