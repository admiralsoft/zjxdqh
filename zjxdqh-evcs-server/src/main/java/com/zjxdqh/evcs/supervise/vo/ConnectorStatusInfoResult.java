package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 设备状态变化推送返回结果
 * @author chenshunhua
 * @date 2019/05/13
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ConnectorStatusInfoResult {


    /**
     *
     * 状态
     * 0:接受,
       1:丢弃/忽略，不需要重试
     *
     */
    private Integer Status;



}
