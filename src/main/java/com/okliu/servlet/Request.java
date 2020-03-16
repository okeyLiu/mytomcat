package com.okliu.servlet;

import java.io.InputStream;

/**
 * @author liuhaolie
 * @Time 2020/3/17-0:38
 */
public class Request {
    private String method;
    private String url;
    private InputStream inputStream;

    public Request(InputStream inputStream) throws Exception {
        this.inputStream = inputStream;
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        extractFields(new String(bytes));
    }

    private void extractFields(String content) {
        if (content.equals("")) {
            System.out.println("empty");
        } else {
            String firstLine = content.split("\\n")[0];
            String[] split = firstLine.split("\\s");
            setMethod(split[0]);
            setUrl(split[1]);
        }

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
