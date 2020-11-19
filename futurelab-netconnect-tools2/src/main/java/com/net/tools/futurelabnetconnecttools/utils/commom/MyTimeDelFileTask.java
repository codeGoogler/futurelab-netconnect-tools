package com.net.tools.futurelabnetconnecttools.utils.commom;


import java.io.File;
import java.util.TimerTask;

/**
 * 开启定时任务删除文件
 */
public class MyTimeDelFileTask  extends TimerTask {
    private File file;
    public MyTimeDelFileTask(File file) {
        this.file = file;
    }
    @Override
    public void run() {
        if(file != null && file.exists()){
            file.delete();
            System.out.println("文件："+file.getName()+"已删除~");
        }
    }
}
