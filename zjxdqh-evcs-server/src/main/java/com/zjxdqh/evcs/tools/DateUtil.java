package com.zjxdqh.evcs.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author liujixiang
 * @date 2018/8/8
 */
public class DateUtil {

    public static final String formatStr_yyyyMMddHHmmssS_ = "yyyyMMddHHmmss";
    public static final String formatStr_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String formatStr_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String formatStr_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String formatStr_yyyyMMddHH = "yyyy-MM-dd HH";
    public static final String formatStr_yyyyMMdd = "yyyy-MM-dd";
    public static final String formatStr_yyyy = "yyyy";
    public static final String formatStr_yyyy_MM_dd = "yyyyMMdd";
    public static final String formatStr_yyyyMMddDelimiter = "-";

    public static String getYYYYMMDDHHMMSS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static String getNowDateStr(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(getNowDate());
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date strToDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 当前日
     *
     * @return
     */
    public static String getCurrentDay() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("dd").format(ts);
    }

    /**
     * 当前时
     *
     * @return
     */
    public static String getCurrentHour() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("HH").format(ts);
    }

    /**
     * 当前分
     *
     * @return
     */
    public static String getCurrentMinute() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("mm").format(ts);
    }

    /**
     * 当前月的 下12个的数组
     *
     * @return
     */
    public static String getNextMonth() {
        String ret = new String();
        int nextYear = Integer.parseInt(getCurrentYear());
        ;
        int nextMonth = Integer.parseInt(getCurrentMonth());

        nextMonth++;
        if (nextMonth > 12) {
            nextYear++;
            nextMonth = 1;
        }
        String mth = "";
        if (nextMonth < 10) {
            mth = "0" + nextMonth;
        } else {
            mth = nextMonth + "";
        }
        ret = new String(String.valueOf(nextYear) + mth);

        return ret;
    }

    /**
     * 当前月
     *
     * @return
     */
    public static String getCurrentMonth() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("MM").format(ts);
    }

    /**
     * 当前年
     *
     * @return
     */
    public static String getCurrentYear() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy").format(ts);
    }

    public static String getYear(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(formatStr_yyyy);
        return df.format(date);
    }

    /*
     * 根据date类日期返回yyyymmdd类型的日期
     *
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc
                .get(Calendar.DATE));
        return sdf.format(gc.getTime());
    }

    /**
     * @param
     * @return java.lang.String
     * @throws
     * @Title: getCurrentDateString
     * @Description:获取当前时间字符串(yyyMMdd) eg:20170918
     * @author chenshunhua
     * @date 2018-08-09
     */
    public static String getCurrentDateString() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMdd").format(ts);
    }

    /**
     * 获取当前时间 HHmmss
     */
    public static String getCurrentTime() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String currentTime = new SimpleDateFormat("HHmmss").format(ts);
        return currentTime;
    }


    /**
     * @param stDate
     * @param endDate
     * @return boolean
     * @throws
     * @Title: currentDateInRange
     * @Description:
     * @author chenshunhua
     * @date 2018-08-09
     */
    public static boolean currentDateInRange(Integer stDate, Integer endDate) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        if (currentDate == null || "".equals(currentDate)) {
            return false;
        }
        return (Integer.valueOf(currentDate) >= stDate && Integer.valueOf(currentDate) <= endDate) ? true : false;
    }

    // 修改日期的月份,zl>0增加,zl<0减少 zl增加几个月
    public static Date getAddMonth(Date startDate, int zl) {
        Calendar cal = null;
        cal = getCalendar(startDate);
        cal.add(Calendar.MONTH, zl);
        return cal.getTime();
    }

//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    Calendar c = Calendar.getInstance();
//c.add(Calendar.MONTH, -1);    //得到前一个月
//    String start = format.format(c.getTime())+" 00:00:00";


    /**
     * 获取前一个月时间
     *
     * @return yyyy-MM-dd
     */
    public static String getFrontOneMonth() {
        SimpleDateFormat sf = new SimpleDateFormat(formatStr_yyyyMMdd);
        Calendar c = Calendar.getInstance();
        //得到前一个月
        c.add(Calendar.MONTH, -1);
        return sf.format(c.getTime());
    }

    /**
     * 获取前三个月时间
     *
     * @return yyyy-MM-dd
     */
    public static String getFrontThreeMonth() {
        SimpleDateFormat sf = new SimpleDateFormat(formatStr_yyyyMMdd);
        Calendar c = Calendar.getInstance();
        //得到前一个月
        c.add(Calendar.MONTH, -3);
        return sf.format(c.getTime());
    }

    /*
     * 把Date转化成Calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }

    /*
     * 根据string 类型日期返回date类
     */
    public static Date getDateForString(String day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(day);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /*
     * 根据string 类型日期返回date类,格式为yyyyMMddHHmmss
     */
    public static Date getDateTime(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param
     * @return
     * @throws
     * @Title: 得到当前系统日期 :
     * @Description: 当前日期的格式字符串, 日期格式为"yyyy-MM-dd HH:mi:ss"
     * @author chenshunhua
     * @date 2018-08-09
     */
    public static String getCurrentDateTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    /**
     * <li>功能描述：时间相减得到月数【yyyyMMdd】
     *
     * @param date1 被减日期
     * @param date2 日期
     * @return long          月数
     * @author chenshunhua
     * @date 2018-08-09
     */
    public static int getMonth(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int resMonth = 0;
        try {
            Calendar bef = Calendar.getInstance();
            Calendar aft = Calendar.getInstance();
            bef.setTime(sdf.parse(date1));
            aft.setTime(sdf.parse(date2));
            int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
            int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
            resMonth = Math.abs(month + result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resMonth;
    }

    /**
     * @param DATE1
     * @param DATE2
     * @return int
     * @throws
     * @Title: compare_date
     * @Description:日期比较
     * @author chenshunhua
     * @date 2018-08-09
     */
    public static int compare_date(String DATE1, String DATE2) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //dt1在dt2后
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //dt1 在dt2前
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new Exception("日期比较出错");
        }
    }

    /**
     * 获取当前月yyyyMM
     *
     * @return
     */
    public static String currentMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前月yyyyMM
     *
     * @return
     */
    public static String currentMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间戳（精确到秒）
     *
     * @return 时间戳字符串
     */
    public static String timeStemp() {
        long currentTimeMillis = System.currentTimeMillis();
        String time = String.valueOf(currentTimeMillis / 1000);
        return time;
    }


}
