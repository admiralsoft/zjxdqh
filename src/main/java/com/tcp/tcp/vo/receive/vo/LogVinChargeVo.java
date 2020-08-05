package com.tcp.tcp.vo.receive.vo;

import com.tcp.bean.LogMsData;
import com.tcp.bean.LogVinCharge;
import com.tcp.tcp.base.message.BaseMessageBody;

public class LogVinChargeVo extends LogVinCharge implements BaseMessageBody {


    /**
     * 充电时间（单位：分钟）
     */
    private Integer chargeTime;

    /**
     * 充电金额
     */
    private Double chargeAmount;

    public Integer getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Integer chargeTime) {
        this.chargeTime = chargeTime;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
