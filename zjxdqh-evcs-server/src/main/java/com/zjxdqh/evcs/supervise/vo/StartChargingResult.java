package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author chenshunhua
 * @date 2019/08/12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)

public class StartChargingResult {

    /**
     * 充电订单号
     */
    private String StartChargeSeq;


    /**
     * 操作结果
     * 0：成功
     * 1：失败
     *
     */
    private Integer SuccStat;


    /**
     *
     * 失败原因
     * 0：无
     * 1：接收失败
     *
     */

    private Integer FailReason;



}
