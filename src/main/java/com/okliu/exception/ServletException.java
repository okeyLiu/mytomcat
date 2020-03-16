package com.okliu.exception;

/**
 * @author liuhaolie
 * @Time 2020/3/17-1:10
 */
public class ServletException extends RuntimeException {

    public ServletException() {
        super("ServletException:未知原因");
    }

    public ServletException(String message) {
        super(message);
    }

    public ServletException(Throwable ex) {
        super(ex);
    }

}
