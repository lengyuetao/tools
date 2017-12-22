package com.tao.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {

    public static String formatDate(Date date,String pattern){
        if(date==null||date.equals("")){
            return null;
        }
        SimpleDateFormat sf=new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    public static void main(String[] args) {
        System.out.println(formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }
}
