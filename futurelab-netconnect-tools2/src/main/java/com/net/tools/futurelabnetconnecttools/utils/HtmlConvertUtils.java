package com.net.tools.futurelabnetconnecttools.utils;

import org.springframework.web.util.HtmlUtils;

public class HtmlConvertUtils {
    public static  String createHtmlByStr(String  content){
        //用于存储html字符串
        StringBuilder stringHtml = new StringBuilder();
        //输入HTML文件内容
        stringHtml.append("<html><head>");
        stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");
        stringHtml.append("<title>测试报告文档</title>");
        stringHtml.append("</head>");
        stringHtml.append("<body>");
        stringHtml.append("<div>"+content+"</div>");
        stringHtml.append("</body></html>");
        return stringHtml.toString();
    }

    String html = "";
    /**
     * 把html的标签特殊字符转换成普通字符
     */
    public void testhtmlEscape(){
        String value = HtmlUtils.htmlEscape(html);
        System.out.println(value);
    }
    /**
     * 把html的特殊字符转换成普通数字
     */
    public void testhtmlEscapeDecimal(){
        String value = HtmlUtils.htmlEscapeDecimal(html);
        System.out.println(value);
    }
    /**
     * 把html的特殊字符转换成符合Intel HEX文件的字符串
     */
    public void htmlEscapeHex(){
        String value = HtmlUtils.htmlEscapeHex(html);
        System.out.println(value);
    }
    /**
     * 把html的特殊字符反转换成html标签
     * 以上三种方法都可以反转换
     */
    public void htmlUnescape(){
        String tmp = HtmlUtils.htmlEscapeDecimal(html);
        System.out.println(tmp);

        String value = HtmlUtils.htmlUnescape(tmp);
        System.out.println(value);
    }
}
