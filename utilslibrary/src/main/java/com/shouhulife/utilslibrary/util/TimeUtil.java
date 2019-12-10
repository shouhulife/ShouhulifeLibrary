package com.shouhulife.utilslibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : shouhulife
 * time: 2019/12/10 15
 * email: xhdshz@foxmail.com
 */
public class TimeUtil {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * 格式化日期字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 获取当前时间 格式为yyyy-MM-dd 例如2011-07-08
     *
     * @return
     */
    public static String getDate() {
        return formatDate(new Date(), DEFAULT_DATE_PATTERN);
    }
    /**
     * 获取当前时间 格式为yyyy-MM-dd hh:mm:ss 例如2011-07-08 11:11:11
     *
     * @return
     */
    public static String getDateDetail() {
        return formatDate(new Date(), DEFAULT_DATETIME_PATTERN);
    }


    /**
     * 字符串转换成日期
     * @param str
     * @return
     */
    public static Date strToDate(String str){
        try {
            Date date = new SimpleDateFormat(DEFAULT_DATE_PATTERN).parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
