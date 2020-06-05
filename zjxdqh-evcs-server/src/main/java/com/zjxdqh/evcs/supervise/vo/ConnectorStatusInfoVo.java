package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 充电设备接口状态
 *
 * @author chenshunhua
 * @date 2019/05/09
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ConnectorStatusInfoVo {

    /**
     *充电设备接口
     编码
     *
     */
    private String ConnectorID;

    /**
     *充电设备接口状
     态
     *
     */
    private Integer Status;
    /**
     *车位状态
     *
     */
    private Integer ParkStatus;
    /**
     *地锁状态
     *
     */
    private Integer LockStatus;








}
