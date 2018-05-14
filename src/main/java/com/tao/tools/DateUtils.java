package com.tao.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {

    /**
     * 格式化日期时间
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date,String pattern){
        if(date==null||date.equals("")){
            return null;
        }
        SimpleDateFormat sf=new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 转成日期格式
     * @param date
     * @param pattern
     * @return
     */
    public static Date strToDate(String date,String pattern){
        SimpleDateFormat sf=new SimpleDateFormat(pattern);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * long类型时间戳转换时间字符串
     * @param str
     * @param formate
     * @return
     */
    public static String longToDateStr(Long str,String formate){
        SimpleDateFormat sf=new SimpleDateFormat(formate);
        return sf.format(new Date(str));
    }

    /**
     * long 类型时间戳转换时间类型
     * @param str
     * @returne
     */
    public static Date longToDat(Long str){
        if(str!=null){
            return new Date(str);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(strToDate("2017-04-03 23:22:33","yyyy-MM-dd"));
    }
}
