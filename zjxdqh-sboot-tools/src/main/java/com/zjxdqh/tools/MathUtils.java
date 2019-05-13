package com.zjxdqh.tools;

import java.math.BigDecimal;

/**
 * 金额计算工具类
 */
public class MathUtils {

    /**
     * 加法（四舍五入）， d1 + d2
     * @param d1
     * @param d2
     * @param newScale 小数位数
     * @return
     */
    public static BigDecimal add(Double d1, Double d2, int newScale) {
        return add(new BigDecimal(d1), d2, newScale);
    }

    /**
     * 加法（四舍五入）， d1 + d2
     * @param d1
     * @param d2
     * @param newScale 小数位数
     * @return
     */
    public static BigDecimal add(BigDecimal d1, Double d2, int newScale) {
        return d1.add(new BigDecimal(d2)).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法（四舍五入）， d1 - d2
     * @param d1
     * @param d2
     * @param newScale 小数位数
     * @return
     */
    public static BigDecimal subtract(Double d1, Double d2, int newScale) {
        return subtract(new BigDecimal(d1), d2, newScale);
    }


    /**
     * 减法（四舍五入）， d1 - d2
     * @param d1
     * @param d2
     * @param newScale 小数位数
     * @return
     */
    public static BigDecimal subtract(BigDecimal d1, Double d2, int newScale) {
        return d1.subtract(new BigDecimal(d2)).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 乘法（四舍五入）， d1 *d2
     * @param d1
     * @param d2
     * @param newScale 小数位数
     * @return
     */
    public static BigDecimal multiply(Double d1, Double d2, int newScale) {
        return multiply(new BigDecimal(d1), d2, newScale);
    }

    /**
     * 乘法（四舍五入）， d1 *d2
     * @param d1
     * @param d2
     * @param newScale 小数位数
     * @return
     */
    public static BigDecimal multiply(BigDecimal d1, Double d2, int newScale) {
        return d1.multiply(new BigDecimal(d2)).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 除法（四舍五入） d1 / d2
     * @param d1
     * @param d2
     * @param newScale  小数位数
     * @return
     */
    public static BigDecimal divide(Double d1, Double d2, int newScale) {
        return divide(new BigDecimal(d1), d2, newScale);
    }

    /**
     * 除法（四舍五入） d1 / d2
     * @param d1
     * @param d2
     * @param newScale  小数位数
     * @return
     */
    public static BigDecimal divide(BigDecimal d1, Double d2, int newScale) {
        return d1.divide(new BigDecimal(d2)).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }


}
