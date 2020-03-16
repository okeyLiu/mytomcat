package com.okliu.servlet;

import com.okliu.exception.ServletException;

/**
 * @author liuhaolie
 * @Time 2020/3/17-1:07
 */
public abstract class Servlet {

    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request, Response response);

    public void service(Request request, Response response) {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        } else {
            throw new ServletException("Method Not Suppprt");
        }
    }

}
