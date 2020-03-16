package com.okliu.config;

import com.okliu.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhaolie
 * @Time 2020/3/17-1:14
 */
public class ServletConfigMapping {

    private static List<ServletConfig> servletConfigs = new ArrayList<>();

    static {
        // TODO:后期升级成扫描注解
        servletConfigs.add(new ServletConfig("okliu", "/okliu", "com.okliu.servlet.OkliuServlet"));
        servletConfigs.add(new ServletConfig("okeyliu", "/okeyliu", "com.okliu.servlet.OkeyliuServlet"));
        // 日志列表
        LogUtils.infoOut("Servlet Config Mapping List:");
        for (ServletConfig servletConfig : servletConfigs) {
            LogUtils.infoOut("\t\tMapping " + servletConfig.getUrlMapping() + " To " + servletConfig.getClazz());
        }
    }

    public static List<ServletConfig> getServletConfigs() {
        return servletConfigs;
    }

}
