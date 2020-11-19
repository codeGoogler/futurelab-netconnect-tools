package com.net.tools.futurelabnetconnecttools.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtils {
//    public static  MultipartFile getMultipartFile(File file){
//        FileInputStream fileInputStream = null;
//        MultipartFile multipartFile = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//            multipartFile = new MockMultipartFile(file.getName(),file.getName(),
//                    "text/plain",fileInputStream);
//            //  MultipartFile multipartFile =new MockMultipartFile(“file”, file.getName(), “text/plain”, IOUtils.toByteArray(input));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return multipartFile;
//    }
    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件和文件夹
     * @param directory
     */
    public static void deleteDirFile(File directory){
        if(directory == null || !directory.exists()){
            return;
        }
        if (directory.isFile()){
            directory.delete();
        }else {
            File[] files = directory.listFiles();
            //此处有个巧妙之处是当是当递归到空文件夹时，数组的长度为空，但是files不是null，forEach会忽略掉不去执行，
            //而且最开始的if语句排除了文件，当listFiles的执行对象是一个真文件而不是文件夹时会返回null，
            //此处为null的情况也被前面的条件规避了，因此此处不需要判断数组为空或者为null
            for(File direc : files){
                deleteFile(direc);
            }
            //递归结束抛出一层删一层（这时候的删除文件夹肯定是空文件夹,不是空文件夹是删不掉的）
            directory.delete();
        }
    }

    /**
     * 删除指定文件
     * @param file
     */
    public static void deleteFile(File file){
        if(file == null || !file.exists()){
            return;
        }
        file.delete();
    }
}
