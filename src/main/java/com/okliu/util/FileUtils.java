package com.okliu.util;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;

/**
 * @author liuhaolie
 * @Time 2020/3/17-1:06
 */
public class FileUtils {

    public static boolean writeFile(InputStream inputStream, OutputStream outputStream) {
        boolean success = false;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(HttpUtils.getHttpResponseContext200("").getBytes());
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            int fileSize = count;
            long written = 0;
            int byteSize = 1024;
            byte[] bytes = new byte[byteSize];
            while (written < fileSize) {
                if (written + byteSize > fileSize) {
                    byteSize = (int) (fileSize - written);
                    bytes = new byte[byteSize];
                }
                bufferedInputStream.read(bytes);
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                written += byteSize;
            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    public static boolean writeFile(File file, OutputStream outputStream) throws Exception {
        return writeFile(new FileInputStream(file), outputStream);
    }


    public static String getResourcePath(String path) {
        String resource = FileUtils.class.getResource("/").getPath();
        String s = resource + "\\" + path;
        LogUtils.infoOut("path = " + s + ", ContentType = " + getContentType(s));
        return s;
    }

    // TODO:实现文件格式返回
    public static String getContentType(String fileUrl) {
        String contentType = null;
        try {
            contentType = new MimetypesFileTypeMap().getContentType(new File(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentType;
    }

}
