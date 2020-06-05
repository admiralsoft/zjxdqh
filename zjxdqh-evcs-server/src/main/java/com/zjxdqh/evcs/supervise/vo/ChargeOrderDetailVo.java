package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjxdqh.tools.DateUtils;
import com.zjxdqh.tools.annon.NumberScale;
import lombok.Data;

import java.util.Date;

/**
 * 充电明细信息体
 *
 * @author Yorking
 * @date 2019/05/08
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChargeOrderDetailVo {

    /**
     * 充电开始时间
     */
    @JsonFormat(pattern = DateUtils.YMD_HMS)
    private Date DetailStartTime;
    /**
     * 充电结束时间
     */
    @JsonFormat(pattern = DateUtils.YMD_HMS)
    private Date DetailEndTime;
    /**
     * 时段电价
     */
    @NumberScale(4)
    private Double ElecPrice =0d;
    /**
     * 时段服务费价格
     */
    @NumberScale(4)
    private Double SevicePrice =0d;
    /**
     * 时段 充电量
     */
    private Double DetailPower;
    /**
     * 时段 电费
     */
    private Double DetailElecMoney =0d;
    /**
     * 时段 服务费
     */
    private Double DetailSeviceMoney =0d;


}
