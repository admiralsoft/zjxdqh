package com.zjxdqh.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yorking
 * @date 2019/05/07
 */
public class DateUtils {

    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M_D = "yyyy-MM-dd";


    /**
     * 获取 时间格式化字符串
     * @param date
     * @param patten
     * @return
     */
    public static String getDateString(Date date, String patten) {
        DateFormat df = new SimpleDateFormat(patten);
        return df.format(date);
    }

    /**
     *
     * 获取 时间格式化字符串（默认格式： yyyy-MM-dd HH:mm:ss)
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        DateFormat df = new SimpleDateFormat(YMD_HMS);
        return df.format(date);
    }


    /**
     *
     * 获取 当前时间格式化字符串
     * @param patten
     * @return
     */
    public static String getNowString(String patten) {
        DateFormat df = new SimpleDateFormat(patten);
        return df.format(new Date());
    }

    /**
     *
     * 获取 当前时间格式化字符串（默认格式： yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String getNowString() {
        DateFormat df = new SimpleDateFormat(YMD_HMS);
        return df.format(new Date());
    }

}
