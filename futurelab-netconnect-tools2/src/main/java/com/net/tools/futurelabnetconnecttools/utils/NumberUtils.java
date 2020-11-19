package com.net.tools.futurelabnetconnecttools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {

    public static boolean isPhone(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return df2.format(date1);
    }

    public static String parseDateMillis(Integer time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(Long.valueOf(time) * 1000);
    }

    public static String parseDate(String dateStr) {

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
            Date result;
            result = df.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return sdf.format(result);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {

        System.out.println(parseDate("2019-07-05T02:23:24.000+0000"));
    }
    /*方法二：推荐，速度最快
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
