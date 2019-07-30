package com.minip.tx.utils;

import com.mchange.util.AssertException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.minip.tx.common.constant.TODAY;
import static com.minip.tx.common.constant.TOMORROW;
import static com.minip.tx.common.constant.YESTERDAY;

public class TimeUtils {

    private static SimpleDateFormat yyyy_MM_dd;

    //避免每次调用方法重复创建SimpleDateFormat对象
    static{
        yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    }

    // 工具类 拒绝实例化
    private TimeUtils(){
        throw new AssertException();
    }


    public static Timestamp getCurrentTimeStamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getDay(String dayType){
        Date date= new Date();//取时间
        Calendar calendar = Calendar.getInstance();
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
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        String dateString = yyyy_MM_dd.format(date);
        return dateString;
    }

    public static String transDay(Date date){
        String strDay = yyyy_MM_dd.format(date);
        return strDay;
    }
}
