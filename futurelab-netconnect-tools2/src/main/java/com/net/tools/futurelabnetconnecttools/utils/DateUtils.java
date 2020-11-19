package com.net.tools.futurelabnetconnecttools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Pangzhonghua on 2016/10/25.
 */
public class DateUtils {
    /**
     * 返回unix时间戳 (1970年至今的秒数)
     * @return
     */
    public static long getUnixStamp(){
        return System.currentTimeMillis()/1000;
    }

    /**
     * 得到昨天的日期
     * @return
     */
    public static String getYestoryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String yestoday = sdf.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 得到今天的日期
     * @return
     */
    public static  String getTodayDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 得到今天的日期
     * @return
     */
    public static  String String2Date(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(d);
        return date;
    }
    public static  String String2Date(Date d,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = sdf.format(d);
        return date;
    }
    /**
     * 时间戳转化为时间格式
     * @param timeStamp
     * @return
     */
    public static String timeStampToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }
    /**
     * 时间戳转化为时间格式
     * @param timeStamp
     * @return
     */
    public static String timeStampMsToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp);
        return date;
    }
    /**
     * 时间戳转化为时间格式
     * @param timeStamp
     * @return
     */
    public static String timeStampToStrr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }


    /**
     * 时间戳转化为时间格式
     * @param timeStamp
     * @return
     */
    public static String timeHourToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * 得到日期   yyyy-MM-dd
     * @param timeStamp  时间戳
     * @return
     */
    public static String formatDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }
    /**
     * 得到日期   yyyy-MM-dd
     * @param timeStamp  时间戳
     * @return
     */
    public static String formatDateYear(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }


    /**
     * 得到时间  HH:mm:ss
     * @param timeStamp   时间戳
     * @return
     */
    public static String getTime(long timeStamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if ( split.length > 1 ){
            time = split[1];
        }
        return time;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime =System.currentTimeMillis() / (long) 1000 ;
        long time = curTime - timeStamp;
        if (time < 60 && time >= 0) {
            return time +"秒前";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，(多少分钟)
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToFormat(long timeStamp) {
        long curTime =System.currentTimeMillis() / (long) 1000 ;
        long time = curTime - timeStamp;
        return time/60 + "";
    }
    /**
     * 时间戳转化为时间格式
     * @param timeStamp
     * @return
     */
    public static String timeStampToStrWeb(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }

    /**
     * Date（long） 转换 String
     *
     * @param time
     * @param format
     * @return
     *
     */
    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));//设置时区
        String s = sdf.format(time);
        return s;
    }

    /**
     * 将时间戳转化为 LocalDateTime
     * @param time  long 时间戳  毫秒
     * @return
     */
    public static String long2String(long time) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return localDateTime.toString();
    }

    /**
     * 毫秒转化为 localTime
     * @param time
     * @param instant g格式
     * @return
     */
    public static String longToLocalTime(long time,Instant instant) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.toString();
    }

    /**
     * 毫秒转化为 localTime
     * @param time
     * @return
     */
    public static String longToLocalTime(long time) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return localDateTime.toString();
    }

    /**
     *  LocalDateTime格式的时间戳 转化为 long 型（毫秒）的时间戳
     * @param time
     * @param
     * @return long
     */
    public static long localTimeToLong(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }

    /**
     *    LocalDateTime格式的时间戳 转化为 long 型（毫秒）的时间戳
     * @param time
     * @return
     */
    public static String localTimeToLongString(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time);
        return String.valueOf(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()).getTime());
    }

    /**
     * 获取当前正确的时间
     * @return
     */
//   public static Date getCurrentDate() {
//        System.out.println("原时间 " + new Date());
//        TimeZone time = TimeZone.getTimeZone("ETC/GMT-8");
//        TimeZone.setDefault(time);
//        System.out.println("修改后时间 " + new Date());
//        return new Date();
//    }

    //获取系统当前时间，字符串类型
    public static String getStrDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置为东八区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date newDate = new Date();
        String dateStr = sdf.format(newDate);
        return dateStr;
    }



    //获取系统当前时间Date类型，需要将字符串类型转成时间
    public static Date getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置为东八区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date();
        String dateStr = sdf.format(date);

        //将字符串转成时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate=null;
        try {
            newDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 格式化String时间
     * @param time String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String timeFromat) {


        Date date=null;
        try{
            DateFormat dateFormat=new SimpleDateFormat(timeFromat);
            date=dateFormat.parse(getStrDate());
        }catch(Exception e){

        }
        return date;
    }

}
