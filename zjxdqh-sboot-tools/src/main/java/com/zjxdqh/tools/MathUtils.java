package com.zjxdqh.tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

/**
 * 金额计算工具类
 */
public class MathUtils {

    private static final String TARGET_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char[] chs = TARGET_STR.toCharArray();
    private static final BigInteger INTEGER0 = new BigInteger("0");

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

    /**
     * 10进制转任意进制
     */
    public static String numToRadix(String number, int radix) {
        if(radix < 0 || radix > TARGET_STR.length()){
            radix = TARGET_STR.length();
        }

        BigInteger bigNumber = new BigInteger(number);
        BigInteger bigRadix = new BigInteger(radix + "");

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(0);
        while (!bigNumber.equals(INTEGER0)) {
            stack.add(chs[bigNumber.remainder(bigRadix).intValue()]);
            bigNumber = bigNumber.divide(bigRadix);
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0" : result.toString();
    }

    /**
     * 任意进制转10进制
     */
    public static String radixToNum(String number, int radix){
        if(radix < 0 || radix > TARGET_STR.length()){
            radix = TARGET_STR.length();
        }
        if (radix == 10) {
            return number;
        }

        char ch[] = number.toCharArray();
        int len = ch.length;

        BigInteger bigRadix = new BigInteger(radix + "");
        BigInteger result = new BigInteger("0");
        BigInteger base = new BigInteger("1");


        for (int i = len - 1; i >= 0; i--) {
            BigInteger index = new BigInteger(TARGET_STR.indexOf(ch[i]) + "");
            result = result.add(index.multiply(base)) ;
            base = base.multiply(bigRadix);
        }

        return result.toString();
    }


    /**
     * 任意进制之间的互相转换, 先将任意进制转为10进制, 然后在转换为任意进制
     */
    public static String transRadix(String num, int fromRadix, int toRadix) {
        return numToRadix(radixToNum(num, fromRadix), toRadix);
    }


    public static void main(String[] args) {
        //将一个大到随意的数字转换为尽可能的短

        System.out.println(numToRadix("19051017161233300000000001231", 52));

        System.out.println(radixToNum("6ywHsqHIFwwshj4sv", 52));


    }


}
