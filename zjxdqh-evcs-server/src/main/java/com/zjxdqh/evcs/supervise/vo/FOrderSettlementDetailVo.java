package com.zjxdqh.evcs.supervise.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 结算订单明细 查询结果对象
 * </p>
 *
 * @author wangqinmin
 * @date 2019-09-05
 */
@Data
public class FOrderSettlementDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer settleDetailId;

    private String sn;

    private Date startTime;

    private Date endTime;

    private BigDecimal power;

    private BigDecimal servicePrice;

    private BigDecimal elePrice;

    private BigDecimal sprice;

    private BigDecimal eprice;

    private BigDecimal serviceMoney;

    private BigDecimal eleMoney;

    private Integer discountType;

    private BigDecimal discountValue;

    private Date createTime;
}