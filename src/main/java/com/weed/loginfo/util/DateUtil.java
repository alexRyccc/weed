package com.weed.loginfo.util;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: com.weed.loginfo.util
 * @date 2020/12/10 9:20
 */
public class DateUtil {

    /** 日期格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(yyyyMMdd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN2 = "yyyyMMdd HH:mm:ss";
    /** 日期格式(yyyyMMdd) */
    public final static String DATE_DAY_PATTERN = "yyyyMMdd";
    /** 日期格式(yyyyMM) */
    public final static String DATE_MONTH_PATTERN = "yyyyMM";
    /** 时间格式(HH:mm:ss) */
    public final static String TIME_PATTERN = "HH:mm:ss";

    public static String getFormatDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
        return simpleDateFormat.format(date);
    }

    public static String getFormatDateyyyyMMdd(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_DAY_PATTERN);
        return simpleDateFormat.format(date);
    }

    public static String getFormatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date addMonths(int months) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MONTH, months);
        return nowTime.getTime();
    }


    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * 
     * @param date
     *            日期
     * @param pattern
     *            格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * 
     * @param date
     *            日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        if (date != null) {
            return format(date, DATE_PATTERN);
        }
        return null;
    }


    /**
     * 上个月第一天
     * 
     * @return
     */
    public static Date getPreMonthStart() {
        return getPreMonthStart(-1);
    }

    public static Date getPreMonthStart(int size) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, size);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 上个月最后一天
     * 
     * @return
     */
    public static Date getPreMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 将时间戳转换为日期
     * 
     * @param stampStr
     * @return
     */
    public static String stampToDate(String stampStr) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        long lt = new Long(stampStr);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取当前 时间戳 (秒级)
     * 
     * @return
     */
    public static long getCurrTime() {
        return Calendar.getInstance().getTimeInMillis() / 1000;
    }

}
