package com.minip.tx.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {
    public final static String TODAY = "today";
    public final static String YESTERDAY = "yesterday";
    public final static String TOMORROW = "tomorrow";
    public static Timestamp getCurrentTimeStamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getDay(String dayType){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int amount = 0;
        switch(dayType){
            case YESTERDAY:
                amount = -1;
                break;
            case TOMORROW:
                amount = 1;
                break;
            case TODAY:
                break;
            default:
                return null;
        }
        calendar.add(calendar.DATE, amount);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String transDay(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDay = sdf.format(date);
        return strDay;
    }

    public static String transDayTime(Timestamp ts){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDayTime = sdf.format(ts);
        return strDayTime;
    }


}
