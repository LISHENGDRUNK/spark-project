package com.spark.electricity.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtils：时间工具类
 * @Time : 2020/8/11 0011 14:27
 * @Author : lisheng
 * @Description:
 **/
public class DateUtils {
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @return
     * @Author lisheng
     * @Description //TODO 判断一个时间是否在另一个时间之前
     * @Date 14:34 2020/8/11 0011
     * @Param
     **/
    public static boolean before(String time1, String time2) {

        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);
            if (dateTime1.before(dateTime2)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return
     * @Author lisheng
     * @Description //TODO 判断一个时间是否在另一个时间之后
     * @Date 14:34 2020/8/11 0011
     * @Param
     **/
    public static boolean after(String time1, String time2) {

        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);
            if (dateTime1.after(dateTime2)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return
     * @Author lisheng
     * @Description //TODO 计算时间差值
     * @Date 14:34 2020/8/11 0011
     * @Param
     **/
    public static int minus(String time1, String time2) {

        try {
            Date dateTime1 = TIME_FORMAT.parse(time1);
            Date dateTime2 = TIME_FORMAT.parse(time2);
            long millisecend = dateTime1.getTime() - dateTime2.getTime();
            return Integer.valueOf(String.valueOf(millisecend / 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return
     * @Author lisheng
     * @Description //TODO 计算时间差值
     * @Date 14:34 2020/8/11 0011
     * @Param
     **/
    public static String getDateHour(String dateTime) {

        String date = dateTime.split(" ")[0];
        String hourMinuteSecond = dateTime.split(" ")[1];
        String hour = hourMinuteSecond.split(":")[0];

        return date + "_" + hour;
    }

    /*
     * 获取当天日期
     * @return 当天的日期
     **/
    public static String getTodayDate() {
        return DATE_FORMAT.format(new Date());
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 获取昨天的日期
     * @Date 14:47 2020/8/11 0011
     * @Param []
     **/
    public static String getYestodayDate() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date date = cal.getTime();
        return DATE_FORMAT.format(date);
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 格式化日期
     * @Date 14:49 2020/8/11 0011
     * @Param [date]
     **/
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * @return java.lang.String
     * @Author lisheng
     * @Description //TODO 格式化时间
     * @Date 14:50 2020/8/11 0011
     * @Param [date]
     **/
    public static String formatTime(Date date) {
        return TIME_FORMAT.format(date);
    }
}
