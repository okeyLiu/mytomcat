package com.okliu.util;

/**
 * @author liuhaolie
 * @Time 2020/3/17-1:06
 */
public class HttpUtils {

    public static String getHttpResponseContext(int code, String content, String errorMsg) {
        if (code == 200) {
            return "HTTP/1.1 200 OK \n" +
                    "Content-Type: text/html\n" +
                    "\r\n" + content;
        } else if (code == 500) {
            return "HTTP/1.1 500 Internal Error = " + errorMsg + "\n" +
                    "Content-Type: text/html\n" +
                    "\r\n";
        } else {
            return "HTTP/1.1 404 Not Found \n" +
                    "Content-Type: text/html\n" +
                    "\r\n" +
                    "<h1>404 not found</h1>";
        }

    }

    public static String getHttpResponseContext200(String context) {
        return getHttpResponseContext(200, context, "");
    }

    public static String getHttpResponseContext404() {
        return getHttpResponseContext(404, "", "");
    }


}
