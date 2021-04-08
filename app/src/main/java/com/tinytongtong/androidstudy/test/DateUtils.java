package com.tinytongtong.androidstudy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Description: TODO
 * @Author wangjianzhou@didichuxing.com
 * @Date 4/8/21 3:05 PM
 */
public class DateUtils {

    /**
     * 字符串日期格式的计算
     *
     * @param oldDate
     * @param curDate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String oldDate, String curDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(oldDate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(curDate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static void main(String[] args) {
        try {
            int result = daysBetween(null, "202202");
            System.out.println(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
