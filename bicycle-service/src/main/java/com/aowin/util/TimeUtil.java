package com.aowin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String dateToStr(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

//    yyyy-MM-dd HH:mm:ss表示24小时
//    yyyy-MM-dd hh:mm:ss表示12小时
    /**
     * return yyyy-MM-dd hh:
     * */
    public static String dateToStr2(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }


    public static Date Str2ToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }
}
