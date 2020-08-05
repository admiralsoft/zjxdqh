package com.tcp.tcp.vo.receive.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 下午3:54.
 * @Title:
 * @Description: 充电桩回复预约查询
 */
public class ReservationResultVo {

    /**
     * 回复结果 1. 预约成功 2. 预约失败
     */
    private Integer result;

    /**
     * 预约账号
     */
    private String preAccount;

    /**
     * 预约开始时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date preStarTime;

    /**
     * 预约结束时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date preEndTime;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(String preAccount) {
        this.preAccount = preAccount;
    }

    public Date getPreStarTime() {
        return preStarTime;
    }

    public void setPreStarTime(Date preStarTime) {
        this.preStarTime = preStarTime;
    }

    public Date getPreEndTime() {
        return preEndTime;
    }

    public void setPreEndTime(Date preEndTime) {
        this.preEndTime = preEndTime;
    }
}
