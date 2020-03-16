package com.okliu.server;

import com.okliu.concurrent.ThreadPool;
import com.okliu.config.ServletConfig;
import com.okliu.config.ServletConfigMapping;
import com.okliu.servlet.Request;
import com.okliu.servlet.Response;
import com.okliu.servlet.Servlet;
import com.okliu.util.HttpUtils;
import com.okliu.util.LogUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhaolie
 * @Time 2020/3/17-0:13
 */
public class MyTomcat {

    private int port;

    public MyTomcat() {
        this.port = 8080;
    }

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        long start = System.currentTimeMillis();
        LogUtils.infoOut("MyTomcat Version 0.0.1");
        LogUtils.infoOut("MyTomcat Starting InitServlet");
        initServlet();
        LogUtils.infoOut("MyTomcat Finish InitServlet");
        LogUtils.infoOut("MyTomcat Starting Server");
        ThreadPool threadPool = new ThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(port);
        long end = System.currentTimeMillis();
        LogUtils.infoOut("MyTomcat Port Is " + port + ", Started Cost Time " + (end - start) + " ms");
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.add(() -> {
                Request request = null;
                long s1 = System.currentTimeMillis();
                try {
                    request = new Request(socket.getInputStream());
                    LogUtils.infoIn("Receive Request URL is [\"" + request.getUrl() + "\"]");
                    Response response = new Response(socket.getOutputStream());
                    if ("/".equals(request.getUrl())) {
                        //返回默认的欢迎页面
                        response.writeFile("index.html");
                    } else if (stringClassMap.get(request.getUrl()) == null) {
                        response.writeFile(request.getUrl());
                    } else {
                        dispatcherServlet(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                long s2 = System.currentTimeMillis();
                LogUtils.infoOut("Handle Mapping [\"" + request.getUrl() + "\"] Finished, Cost Time " + (s2 - s1) + " ms");
            });
        }
    }

    private Map<String, Class<Servlet>> stringClassMap = new HashMap<>();

    private void initServlet() throws ClassNotFoundException {
        for (ServletConfig servletConfig : ServletConfigMapping.getServletConfigs()) {
            stringClassMap.put(
                    servletConfig.getUrlMapping(), (Class<Servlet>) Class.forName(servletConfig.getClazz()));
        }
    }

    private void dispatcherServlet(Request request, Response response) throws IllegalAccessException, InstantiationException {
        Class<Servlet> servletClass = stringClassMap.get(request.getUrl());
        if (servletClass != null) {
            Servlet servlet = servletClass.newInstance();
            servlet.service(request, response);
        } else {
            response.write(HttpUtils.getHttpResponseContext404());
        }
    }

}
