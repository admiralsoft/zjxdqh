package com.zjxdqh.evcs.supervise.vo;

import lombok.Data;

/**
 * <p>
 *    充电站统计返回结果集对象
 * <p>
 *
 * @author PengWei
 * @date 2019/5/13
 */
@Data
public class EvcsStationStatsInfo {

    /**
     * 充电站id
     */
    private Integer siteNum;

    /**
     * 桩号
     */
    private String pileNum;

    /**
     * 枪号
     */
    private String gunNum;

    /**
     * 充电量
     */
    private Double useEle;
}
