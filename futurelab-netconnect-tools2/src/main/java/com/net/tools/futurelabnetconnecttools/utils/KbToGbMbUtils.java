package com.net.tools.futurelabnetconnecttools.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class KbToGbMbUtils {

    /**
     * @param bytes 转换得字节
     * @param si    是否需要单位
     * @return
     */
    public static String byteFormat(long bytes, boolean si) {
        String[] units = new String[]{" B", " KB", " MB", " GB", " TB", " PB", " EB", " ZB", " YB"};
        int unit = 1024;
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        double pre = 0;
        if (bytes > 1024) {
            pre = bytes / Math.pow(unit, exp);
        } else {
            pre = (double) bytes / (double) unit;
        }
        if (si) {
            return String.format(Locale.ENGLISH, "%.1f%s", pre, units[(int) exp]);
        }
        return String.format(Locale.ENGLISH, "%.1f", pre);
    }
    /**
     * @param size 转换得字节   byte
     * @return  单位 ：kb
     */
    public static int byteFormatKb(long size) {
        int unit = 1024;
        BigDecimal temp = new BigDecimal(size);
        BigDecimal temp2 = new BigDecimal(unit);
        int ressult  = temp.divide(temp2,BigDecimal.ROUND_HALF_UP).intValue();
        return  ressult;
    }
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * 将文件大小kb转换成M
     * @param fileSizeKb
     * @return fileSizeM
     */
    public static String toFileSizeM(String fileSizeKb){
        String fileSizeM= "";
        if(fileSizeKb==null)
            fileSizeM ="0"+"B";
        long fSize = Long.valueOf(fileSizeKb);
        if(fSize<1024){
            fileSizeM = fileSizeKb+"B";//字节
        }else if(fSize>1024){
            BigDecimal bg = new BigDecimal(fSize);
            BigDecimal flsize = new BigDecimal(fSize);
            BigDecimal temp = new BigDecimal(1024);
            if(-1==temp.compareTo(flsize) || 0==temp.compareTo(flsize)){
                //四色五入保留2位小数(根据需求随意调整)
                flsize = bg.divide(new BigDecimal(1024), 2,BigDecimal.ROUND_HALF_UP);
                fileSizeM =flsize+"KB";
            }
            if(-1==temp.compareTo(flsize) || 0==temp.compareTo(flsize)){ //MB
                //四色五入保留2位小数(根据需求随意调整)
                flsize = flsize.divide(new BigDecimal(1024), 2,BigDecimal.ROUND_HALF_UP);
                fileSizeM = flsize+"M";
            }
        }
        return fileSizeM;
    }

    /**
     * 根据文件大小转换为B、KB、MB、GB单位字符串显示
     * @param filesize 文件的大小（long型）
     * @return 返回 转换后带有单位的字符串
     */
    public  static String GetLength(long filesize){

        String strFileSize = null;
        if(filesize < 1024){
            strFileSize = filesize+"B";
            return strFileSize;
        }

        DecimalFormat df = new DecimalFormat("######0.00");

        if ((filesize >= 1024) && (filesize < 1024*1024)){//KB
            strFileSize = df.format(((double)filesize)/1024)+"KB";
        }else if((filesize >= 1024*1024)&&(filesize < 1024*1024*1024)){//MB
            strFileSize = df.format(((double)filesize)/(1024*1024))+"MB";
        }else{//GB
            strFileSize = df.format(((double)filesize)/(1024*1024*1024))+"GB";
        }
        return strFileSize;
    }

}
