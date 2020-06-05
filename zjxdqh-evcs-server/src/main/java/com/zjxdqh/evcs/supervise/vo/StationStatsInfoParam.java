package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *  充电站统计信息 输入值
 * <p>
 *
 * @author PengWei
 * @date 2019/5/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class StationStatsInfoParam {

    /**
     * 充电站 ID
     */
    private String StationID;

    /**
     * 统计开始时间
     */
    @JsonFormat(pattern = DateUtils.Y_M_D)
    private Date StartTime;

    /**
     * 统计结束时间
     */
    @JsonFormat(pattern = DateUtils.Y_M_D)
    private Date EndTime;
}
