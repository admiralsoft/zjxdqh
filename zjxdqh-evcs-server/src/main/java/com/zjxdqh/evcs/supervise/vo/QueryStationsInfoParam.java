package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author Yorking
 * @date 2019/05/16
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class QueryStationsInfoParam {

    /**
     * 上次查询时间
     */
    @JsonFormat(pattern = DateUtils.YMD_HMS)
    private Date  LastQueryTime;

    /**
     * 查询页码（默认为1）
     */
    private int  PageNo = 1;
    /**
     * 每页数量（默认为10）
     */
    private int  PageSize = 10;

    private String operatorId;


    @Override
    public String toString() {
        return this.operatorId + "_" + this.getPageNo() + "_" + this.LastQueryTime + this.PageSize;
    }

}
