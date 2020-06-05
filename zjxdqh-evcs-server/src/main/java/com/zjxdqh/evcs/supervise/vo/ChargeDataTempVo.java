package com.zjxdqh.evcs.supervise.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wangqinmin
 * @since 2019-09-03
 */
@Data
public class ChargeDataTempVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  订单号
     */
    private Integer oid;

    /**
     * 当前soc
     */
    private String Soc;

    private String RemainTime;

    /**
     * 当前电流
     */
    private BigDecimal Voltage;

    /**
     * 当前电压
     */
    private BigDecimal Current;

    /**
     * 当前充电度数
     */
    private BigDecimal electricity;

    /**
     * 当前充电量
     */
    private BigDecimal useele;

    /**
     * 场站id
     */
    private String sid;

    private String whenlong;

}
