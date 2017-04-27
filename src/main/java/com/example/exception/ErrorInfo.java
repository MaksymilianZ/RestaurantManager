package com.example.exception;

/**
 * Created by Maksymilian on 2017-04-27.
 */
public class ErrorInfo {
    public final String url;
    public final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

    public ErrorInfo(String url, String msg) {
        this.url = url;
        this.ex = msg;
    }
}
