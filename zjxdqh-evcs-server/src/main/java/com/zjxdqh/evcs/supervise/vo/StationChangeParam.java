package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author Yorking
 * @date 2019/10/11
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StationChangeParam {

    private String operatorId;

    /**
     * 变更类型：1站点新增 2站点下线 3站点策略
     */
    private Integer type = 3;


    /**
     * 场站ID列表
     */
    private List<String> stationIds;
}
