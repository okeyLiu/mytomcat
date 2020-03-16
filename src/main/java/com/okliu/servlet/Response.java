package com.okliu.servlet;

import com.okliu.util.FileUtils;
import com.okliu.util.HttpUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author liuhaolie
 * @Time 2020/3/17-0:43
 */
public class Response {

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) {
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String path) throws Exception {
        String resourcePath = FileUtils.getResourcePath(path);
        File file = new File(resourcePath);
        if (file.exists()) {
            FileUtils.writeFile(file, outputStream);
        } else {
            write(HttpUtils.getHttpResponseContext404());
        }
    }

}
