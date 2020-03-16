package com.okliu.servlet;

import com.okliu.util.HttpUtils;

/**
 * @author liuhaolie
 * @Time 2020/3/17-1:13
 */
public class OkeyliuServlet extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        response.write(HttpUtils.getHttpResponseContext200("OkeyliuServlet doGet ..."));
    }

    @Override
    public void doPost(Request request, Response response) {
        response.write(HttpUtils.getHttpResponseContext200("OkeyliuServlet doPost ..."));
    }
}
