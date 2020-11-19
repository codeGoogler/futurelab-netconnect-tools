//package com.net.tools.futurelabnetconnecttools.utils;
//
//import ai.oline.laboratory.aiolinelaboratory.net.ResponseObject;
//import ai.oline.laboratory.aiolinelaboratory.utils.commom.MyTimeDelFileTask;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.compress.archivers.zip.Zip64Mode;
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
//import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.Date;
//import java.util.List;
//import java.util.Timer;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
//@Slf4j
//public class FileCromressZipUtils {
//    public static void zipFile(List<File> files, ZipOutputStream outputStream) throws IOException, ServletException {
//        try {
//            int size = files.size();
//            // 压缩列表中的文件
//            for (int i = 0; i < size; i++) {
//                File file = (File) files.get(i);
//                zipFile(file, outputStream);
//            }
//        } catch (IOException e) {
//            throw e;
//        }
//    }
//    public static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException, ServletException {
//        try {
//            if (inputFile.exists()) {
//                if (inputFile.isFile()) {
//                    FileInputStream inStream = new FileInputStream(inputFile);
//                    BufferedInputStream bInStream = new BufferedInputStream(inStream);
//                    ZipEntry entry = new ZipEntry(inputFile.getName());
//                    outputstream.putNextEntry(entry);
//
//                    final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
//                    long streamTotal = 0; // 接受流的容量
//                    int streamNum = 0; // 流需要分开的数量
//                    int leaveByte = 0; // 文件剩下的字符数
//                    byte[] inOutbyte; // byte数组接受文件的数据
//
//                    streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
//                    streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
//                    leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量
//
//                    if (streamNum > 0) {
//                        for (int j = 0; j < streamNum; ++j) {
//                            inOutbyte = new byte[MAX_BYTE];
//                            // 读入流,保存在byte数组
//                            bInStream.read(inOutbyte, 0, MAX_BYTE);
//                            outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
//                        }
//                    }
//                    // 写出剩下的流数据
//                    inOutbyte = new byte[leaveByte];
//                    bInStream.read(inOutbyte, 0, leaveByte);
//                    outputstream.write(inOutbyte);
//                    outputstream.closeEntry(); // Closes the current ZIP entry
//                    // and positions the stream for
//                    // writing the next entry
//                    bInStream.close(); // 关闭
//                    inStream.close();
//                }
//            } else {
//                throw new ServletException("文件不存在！");
//            }
//        } catch (IOException e) {
//            throw e;
//        }
//    }
//    // http://www.itxm.cn/post/15942.html
//    // https://blog.csdn.net/toxic_guantou/article/details/52605920?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
//
//    public static void compressFiles2Zip(File[] files, String zipFilePath) {
//        if (files != null && files.length > 0) {
//            ZipArchiveOutputStream zaos = null;
//            File f = new File(zipFilePath);
//            if(f.isDirectory())  {
//                System.out.println("isDirectory");
//                return;
//            } if(f.exists())  {
//                f.delete();
//            }
//            try {
//                File zipFile = new File(zipFilePath);
//                zaos = new ZipArchiveOutputStream(zipFile);
//                zaos.setUseZip64(Zip64Mode.AsNeeded);
//                int index = 0;
//                for (File file : files) {
//                    if (file != null) {
//                        InputStream input = new FileInputStream(file);
//                        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry( file, new File(file.getParent()).getName()+File.separator+file.getName());
////                        ZipArchiveEntry zipArchiveEntry2 = new ZipArchiveEntry( input, new File(file.getParent()).getName()+File.separator+file.getName());
//                        zaos.putArchiveEntry(zipArchiveEntry);
//
//                        InputStream is = null;
//                        try {
//                            is = new BufferedInputStream(new FileInputStream(
//                                    file));
//                            byte[] buffer = new byte[1024 * 5];
//                            int len = -1;
//                            while ((len = is.read(buffer)) != -1) {
//                                // 把缓冲区的字节写入到ZipArchiveEntry
//                                zaos.write(buffer, 0, len);
//                            }
//                            // Writes all necessary data for this entry.
//                            zaos.closeArchiveEntry();
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        } finally {
//                            if (is != null)
//                                is.close();
//                        }
//                    }
//                }
//                zaos.finish();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            } finally {
//                try {
//                    if (zaos != null) {
//                        zaos.close();
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
//    public static ResponseObject compressFiles2Zip2(List<File> files, String zipFilePath) {
//        if (files != null && files.size() > 0) {
//            ZipArchiveOutputStream zaos = null;
//            File f = new File(zipFilePath);
//            if(f.isDirectory())  {
//                System.out.println("isDirectory");
//                return ResponseObject.fail(1002,"目标路径不对");
//            } if(f.exists())  {
//                f.delete();
//            }
//            try {
//                File zipFile = new File(zipFilePath);
//                zaos = new ZipArchiveOutputStream(zipFile);
//                zaos.setUseZip64(Zip64Mode.AsNeeded);
//                int index = 0;
//                for (File file : files) {
//                    if (file != null) {
//                        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(
//                                file, new File(file.getParent()).getName()+File.separator+file.getName());
//                        zaos.putArchiveEntry(zipArchiveEntry);
//                        InputStream is = null;
//                        try {
//                            is = new BufferedInputStream(new FileInputStream(
//                                    file));
//                            byte[] buffer = new byte[1024 * 5];
//                            int len = -1;
//                            while ((len = is.read(buffer)) != -1) {
//                                // 把缓冲区的字节写入到ZipArchiveEntry
//                                zaos.write(buffer, 0, len);
//                            }
//                            // Writes all necessary data for this entry.
//                            zaos.closeArchiveEntry();
//                        } catch (Exception e) {
//                            throw new RuntimeException(e);
//                        } finally {
//                            if (is != null)
//                                is.close();
//                        }
//                    }
//                }
//                zaos.finish();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            } finally {
//                try {
//                    if (zaos != null) {
//                        zaos.close();
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        return ResponseObject.success();
//    }
//
//    public static  ResponseEntity<FileSystemResource> downloadFile(String filePath) {
//        String downloadUrl = filePath;
//        //log.info("下载文件名称：{}",fileId);
//        String fileName = null; // 文件名称的编码
//        InputStream in = null;
//        File file = null;
//        try {
////            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/43ceaed30b2c495dab328d4cb03d4240202003071sss75537.zip");
////            File fileojb = new File(filePath);
////            String os = System.getProperty("os.name");
////            if(os.toLowerCase().startsWith("win")){
////                file = new File("src/main/resources/static/43ceaed30b2c495dab328d4cb03d4240202003071sss75537.zip");
////            }else{
////                file = new File(filePath);
////            }
//            file = new File(filePath);
//            fileName = new String(file.getName().getBytes("Gb2312"), "ISO-8859-1");
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//            headers.add("Content-Disposition", "attachment; filename=" + fileName);
//            headers.add("Pragma", "no-cache");
//            headers.add("Expires", "0");
//            headers.add("Last-Modified", new Date().toString());
//            headers.add("ETag", String.valueOf(System.currentTimeMillis()));
//            FileSystemResource fileSystemResource =  new FileSystemResource(file);
//
//            Timer t=new Timer();
//            System.out.println("要开始计时啦11111111");
//            t.schedule(new MyTimeDelFileTask(file), 20000);
//            System.out.println("要开始计时啦22222222");
//            return  ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .contentLength(file.length())
//                    .contentType(MediaType.parseMediaType("application/octet-stream"))
//                    .body(fileSystemResource);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("dfsId:{}下载文件失败，cause:{}",filePath,e);
//        }finally {
//            if(in != null){
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return ResponseEntity.status(HttpStatus.MULTI_STATUS).build();
//    }
//    public static  HttpServletResponse download(String path, HttpServletResponse response) {
//        try {
//            // path是指欲下载的文件的路径。
////            File file = new File(path);
//            File file = new File("src/main/resources/static/43ceaed30b2c495dab328d4cb03d4240202003071sss75537.zip");
//            // 取得文件名。
//            String filename = file.getName();
//            // 取得文件的后缀名。
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
//
//            // 以流的形式下载文件。
//            InputStream fis = new BufferedInputStream(new FileInputStream(file));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            // 清空response
//            response.reset();
//            // 设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//            response.addHeader("Content-Length", "" + file.length());
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            toClient.write(buffer);
//            toClient.flush();
//            toClient.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return response;
//    }
//
//
//}
