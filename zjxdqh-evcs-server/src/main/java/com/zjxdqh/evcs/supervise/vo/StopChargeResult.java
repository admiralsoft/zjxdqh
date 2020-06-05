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
public class StopChargeResult {

    /**
     * 充电订单号
     */
    private String StartChargeSeq;
    /**
     * 充电订单状态
     *
     * 1：启动中
     * 2：充电中
     * 3：停止中
     * 4：已结束
     * 5：未知
     *
     */
    private Integer   StartChargeSeqStat ;

    /**
     * 操作结果
     * 0:成功；
     * 1:失败
     */
    private Integer  SuccStat;

    /**
     * 失败原因
     *
     * 0:无；
     * 1:此设备不存在；
     * 2:此设备离线：
     * 3:设备已停止充电；
     * 4～99:自定义
     */
    private Integer  FailReason;

}
