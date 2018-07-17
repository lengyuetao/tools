package com.tao.tools;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        timeStampTest();
    }

    /**
     * TimeStamp使用
     */
    public static void timeStampTest(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());

        Timestamp time2 = new Timestamp(new Date().getTime());

        Timestamp time3 = new Timestamp(Calendar.getInstance().getTimeInMillis());


        System.out.println("----------time1:"+time1);
        System.out.println("----------time2:"+time2);
        System.out.println("----------time3:"+time3);
    }
}
