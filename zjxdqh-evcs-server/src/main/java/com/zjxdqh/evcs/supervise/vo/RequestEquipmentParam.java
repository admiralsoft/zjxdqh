package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: wangqinmin
 * @date: 2019/8/9 09:42
 * @description: 仰天大笑出门去，我辈岂是蓬蒿人
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class RequestEquipmentParam {

    /**
     * 设备认证流水号
     * <p>
     * 格式：运营商ID+唯一编号，27字符
     */
    private String EquipAuthSeq;

    /**
     * 充电设备接口编号
     * <p>
     * 见T/CEC 102.2-2016
     */
    private String ConnectorID;
}
