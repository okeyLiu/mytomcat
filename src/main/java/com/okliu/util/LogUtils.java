package com.okliu.util;

import java.time.LocalDateTime;

/**
 * @author liuhaolie
 * @Time 2020/3/17-3:07
 */
public class LogUtils {

    private static String IN = ">---IN-->>|";
    private static String OUT = "<<--OUT--<|";

    public static void errOut(String error) {
        String prefix = OUT + LocalDateTime.now() + "|";
        System.err.println(prefix + error);
    }

    public static void infoIn(String info) {
        String prefix = IN + LocalDateTime.now() + "|";
        System.out.println(prefix + info);
    }

    public static void infoOut(String info) {
        String prefix = OUT + LocalDateTime.now() + "|";
        System.out.println(prefix + info);
    }

}
