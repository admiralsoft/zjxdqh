package com.tcp.tcp.vo.receive;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

import java.math.BigDecimal;

/**
 * @author TcT
 *         Date: 2018/7/20.
 *         Time: 下午2:40.
 * @Title:
 * @Description:
 */
public class PrescribedRateInfo {

    /**
     * 时间段开始,3字节,例如 12 23 34 为12时23分34秒
     */
    @Data(byteType = DataByteTypEnum.DATE_HMS)
    private String starTime;

    /**
     * 时间段结束,3字节,例如 12 23 34 为12时23分34秒
     */
    @Data(order = 1,byteType = DataByteTypEnum.DATE_HMS)
    private String endTime;

    /**
     * 电价,4
     */
    @Data(order = 2,byteType = DataByteTypEnum.Decimal4, byteLen = 4)
    private BigDecimal powerRate;

    /**
     * 服务费,4
     */
    @Data(order = 3,byteType = DataByteTypEnum.Decimal4, byteLen = 4)
    private BigDecimal serviceRate;


    public String getStarTime() {
        return starTime;
    }

    public void setStarTime(String starTime) {
        this.starTime = starTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getPowerRate() {
        return powerRate;
    }

    public void setPowerRate(BigDecimal powerRate) {
        this.powerRate = powerRate;
    }

    public BigDecimal getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(BigDecimal serviceRate) {
        this.serviceRate = serviceRate;
    }
}
