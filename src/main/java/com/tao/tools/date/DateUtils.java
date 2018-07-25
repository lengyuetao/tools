package com.tao.tools.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {

    private static final String DEFAULT_FORMATE="yyy-MM-dd";

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

    public static void main(String[] args) {
        System.out.println(formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println(strToDate("2017-04-03 23:22:33","yyyy-MM-dd"));
    }

    //获取指定月份的天数
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month );
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 遍历指定月份的每天日期
     * @param date yyyy-MM
     */
    public void getEveryDayDate(String date){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM");
        Date time=null;
        try {
            time=sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int year = cal.get(Calendar.YEAR);
        System.out.println("年份："+year);
        int m = cal.get(Calendar.MONTH);
        System.out.println("月份："+m);
        int dayNumOfMonth = getDaysByYearMonth(year, m);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始

        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String df = simpleDateFormat.format(d);
            System.out.println(df);
        }
    }


    /**
     * 判断是否周末
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(String date) throws ParseException {
        return isWeekend(date,DEFAULT_FORMATE);
    }

    public static boolean isWeekend(String date,String format) throws ParseException {
        DateFormat format1 = new SimpleDateFormat(format);
        Date bdate = format1.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断是否周末
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(Date date){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
}
