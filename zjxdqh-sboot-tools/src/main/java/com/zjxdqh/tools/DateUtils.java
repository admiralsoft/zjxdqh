package com.zjxdqh.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
     *
     * @param date
     * @param patten
     * @return
     */
    public static String getDateString(Date date, String patten) {
        DateFormat df = new SimpleDateFormat(patten);
        return df.format(date);
    }

    /**
     * 获取 时间格式化字符串（默认格式： yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        DateFormat df = new SimpleDateFormat(YMD_HMS);
        return df.format(date);
    }


    /**
     * 获取 当前时间格式化字符串
     *
     * @param patten
     * @return
     */
    public static String getNowString(String patten) {
        DateFormat df = new SimpleDateFormat(patten);
        return df.format(new Date());
    }

    /**
     * 获取 当前时间格式化字符串（默认格式： yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getNowString() {
        DateFormat df = new SimpleDateFormat(YMD_HMS);
        return df.format(new Date());
    }

    /**
     * 两时间相减  --  获取时间长度
     *
     * @return 例如：
     * 1年34天5小时5分钟4秒
     * 34天5小时5分钟4秒
     * 5小时5分钟4秒
     */
    public static String getEndTimeSubtractStartTime(Date endtime, Date starttime) {
        //这样得到的差值是毫秒级别
        long diff = endtime.getTime() - starttime.getTime();

        // 获取年份差
        Calendar from = Calendar.getInstance();
        from.setTime(endtime);
        Calendar to = Calendar.getInstance();
        to.setTime(starttime);
        int year = from.get(Calendar.YEAR) - to.get(Calendar.YEAR);

        // 天数
        long days = diff / (1000 * 60 * 60 * 24);
        long days_day = days % 365;

        // 将小时转为天
        long hours = diff / (1000 * 60 * 60);
        long hous_day = hours % 24;

        // 分钟
        long minutes = (diff - hours * 1000 * 60 * 60) / 1000 / 60;

        // 分钟
        long second = (diff - hours * 1000 * 60 * 60 - minutes * 1000 * 60) / 1000;

        if (year >= 1) {
            return year + "年" + days_day + "天" + hous_day + "小时" + minutes + "分" + second + "秒";
        } else {
            if (days >= 1) {
                return days + "天" + hous_day + "小时" + minutes + "分" + second + "秒";
            } else {
                return hours + "小时" + minutes + "分" + second + "秒";

            }
        }
    }

}
