package com.okliu;

import com.okliu.server.MyTomcat;

/**
 * mytomcat的启动类
 *
 * @author liuhaolie
 * @Time 2020/3/17-1:07
 */
public class MainStarter {

    public static void main(String[] args) throws Exception {
        MyTomcat myTomcat = new MyTomcat();
        myTomcat.start();
    }

}
