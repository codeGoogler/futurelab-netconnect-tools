package com.net.tools.futurelabnetconnecttools.utils;

import java.io.*;
import java.util.*;

/**
 * 类功能描述：</br>
 *
 * @author yuyahao
 * @version 1.0 </p> 修改时间：10/1/2020</br> 修改备注：</br>
 */
public class SensitiveWord {
    private StringBuilder replaceAll;// 初始化
    private String encoding = "UTF-8";
    private String replceStr = "*";
    private int replceSize = 500;
    private static final String fileName = "sensitiveWords.txt";
    private List<String> arrayList;
    public Set<String> sensitiveWordSet;
    public List<String> sensitiveWordList;

    public SensitiveWord(String replceStr, int replceSize) {
        this.replceStr = fileName;
        this.replceSize = replceSize;
    }

    public SensitiveWord() {
    }

    public StringBuilder getReplaceAll() {
        return replaceAll;
    }

    public void setReplaceAll(StringBuilder replaceAll) {
        this.replaceAll = replaceAll;
    }

    public String getReplceStr() {
        return replceStr;
    }

    public void setReplceStr(String replceStr) {
        this.replceStr = replceStr;
    }

    public int getReplceSize() {
        return replceSize;
    }

    public void setReplceSize(int replceSize) {
        this.replceSize = replceSize;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 将敏感字转换为*符号
     *
     * @param str
     * @return
     */
    public String filterInfo(String str) {
        sensitiveWordSet = new HashSet<String>();
        sensitiveWordList = new ArrayList<>();
        StringBuilder buffer = new StringBuilder(str);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        for (int x = 0; x < arrayList.size(); x++) {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for (int start = -1; (start = buffer.indexOf(temp, findIndexSize)) > -1;) {
                findIndexSize = start + temp.length();
                Integer mapStart = hash.get(start);
                if (mapStart == null || (mapStart != null && findIndexSize > mapStart)) {
                    hash.put(start, findIndexSize);
                }
            }
        }
        Collection<Integer> values = hash.keySet();
        for (Integer startIndex : values) {
            Integer endIndex = hash.get(startIndex);
            String sensitive = buffer.substring(startIndex, endIndex);
            if (!sensitive.contains("*")) {
                sensitiveWordSet.add(sensitive);
                sensitiveWordList.add(sensitive);
            }
            buffer.replace(startIndex, endIndex, replaceAll.substring(0, endIndex - startIndex));
        }
        hash.clear();
        return buffer.toString();
    }

    /**
     * 初始化读取铭感文件库
     */
    public void InitializationWork() {
        replaceAll = new StringBuilder(replceSize);
        for (int x = 0; x < replceSize; x++) {
            replaceAll.append(replceStr);
        }
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream("data/"+fileName), encoding);
            bufferedReader = new BufferedReader(read);
            for (String txt = null; (txt = bufferedReader.readLine()) != null;) {
                if (!arrayList.contains(txt))
                    arrayList.add(txt);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != read)
                    read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否有敏感词汇
     *
     * @param str
     * @return
     */
    public static boolean checkSenstiveWord(String str) {
        // 初始敏感词库
        SensitiveWord sw = new SensitiveWord();
        sw.InitializationWork();
        str = sw.filterInfo(str);
        if (str.contains("*")) {
            return true;
        }
        return false;
    }

    public static String filterInfoAfter(String str) {
        // 初始敏感词库
        SensitiveWord sw = new SensitiveWord();
        sw.InitializationWork();
        str = sw.filterInfo(str);
        return str;
    }

    public static void main(String args[]){
/*        System.out.println("我过来了");
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/sensitiveWords.txt");
        *//*try(Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("读取文件数据异常");
        }*//*
        System.out.println(filterInfoAfter("郑州办证"));*/


    }


    public static String readInputStream(InputStream is){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0 ;
        byte [] buffer= new byte[1024];
        try {
            while((length = is.read(buffer)) != -1){
                baos.write(buffer, 0, length);
            }
            is.close();
            baos.close();
            byte [] result = baos.toByteArray();
            String str = new String();//试着解析result里的字符串
// if(str.contains("uft-8")){
// return str;
// }else if(str.contains("gb2312")){
// return new String(result,"gb2312");
// }
            return str;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "获取失败";
        }
    }
}
