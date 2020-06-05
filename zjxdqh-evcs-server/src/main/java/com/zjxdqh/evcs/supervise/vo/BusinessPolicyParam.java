package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: wangqinmin
 * @date: 2019/8/12 16:35
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class BusinessPolicyParam {

    /**
     * 业务策略查询流水号
     * 格式： 运营商ID + 唯一编号
     */
    private String EquipBizSeq;

    /**
     * 充电设备接口编码
     * 见 T/CEC 102.2 - 2016
     */
    private String ConnectorID;
}
