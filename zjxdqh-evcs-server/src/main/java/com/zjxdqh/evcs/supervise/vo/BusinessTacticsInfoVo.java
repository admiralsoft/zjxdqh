package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.face.vo.PolicyInfo;
import lombok.Data;

import java.util.List;

/**
 * @author: wangqinmin
 * @date: 2019/8/9 10:24
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class BusinessTacticsInfoVo {

    /**
     * 业务策略查询流水号
     * <p>
     * 格式：运营商ID+唯一编号，27字符
     */
    private String EquipBizSeq;

    /**
     * 充电设备接口编号
     * <p>
     * 见T/CEC 102.2-2016
     */
    private String ConnectorID;

    /**
     * 操作结果
     * 0：成功 1：失败
     */
    private Integer SuccStat;

    /**
     * 失败原因
     * 0：无
     * 1：此充电桩业务策略不存在
     */
    private Integer FailReason;

    /**
     * 时段数N
     * 范围：0 - 32
     */
    private Integer SumPeriod;

    /**
     * 计费信息
     * 单项业务策略信息体
     */
    private List<PolicyInfoVo> PolicyInfos;
}
