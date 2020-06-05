package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.List;

/**
 * 充电站状态信息
 *
 * @author chenshunhua
 * @date 2019/05/09
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StationStatusInfoVo {

    /**
     * 充电站 ID
     */
    private String StationID;
    /**
     *
     * 充电设备接口状态列表
     */
    private List<ConnectorStatusInfoVo> ConnectorStatusInfos;


}
