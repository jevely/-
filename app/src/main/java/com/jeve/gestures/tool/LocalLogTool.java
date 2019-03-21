package com.jeve.gestures.tool;

import android.os.Environment;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 本地日志
 */
public class LocalLogTool {

    private static String fileName = "locallog.txt";

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        LocalLogTool.fileName = fileName;
    }

    /**
     * 本地记录日志
     */
    public synchronized static void writeTxtToFile(String strcontent) {

        String filePath = Environment.getExternalStorageDirectory().getPath() + "/MakeMoney/";

        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;


        String strContent = getTime() + ": " + strcontent + "\r\n";
        RandomAccessFile raf = null;
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 生成文件
    private static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    private static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
