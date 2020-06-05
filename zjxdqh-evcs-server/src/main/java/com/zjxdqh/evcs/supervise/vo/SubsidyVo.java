package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: wangqinmin
 * @date: 2019/8/20 09:28
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SubsidyVo {

    /**
     * 充电设备接口编号
     * <p>
     * 见T/CEC 102.2-2016
     */
    private String ConnectorID;

    /**
     * 申报开始时间 yyyy-MM-dd
     */
    private String StartTime;

    /**
     * 申报结束时间 yyyy-MM-dd
     */
    private String EndTime;

    /**
     * 0: 成功
     * 1： 失败
     * 2~99 ：自定义
     */
    private Integer ConfirmResult;
}
