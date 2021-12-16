package com.money.more.common.utils;

import org.springframework.http.HttpStatus;

/**
 * @Describe：
 * @Author：luna
 * @Date：2021/12/2:13:19
 */
public class HttpResponse<T extends Object> {
    private String code = "0000";
    private String msg = "请求成功";
    private T data;

    public static HttpResponse error() {
        return error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), "未知异常，请联系管理员");
    }

    public static HttpResponse error(String msg) {
        return error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), msg);
    }

    public static HttpResponse error(String code, String msg) {
        HttpResponse r = new HttpResponse();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResponse ok(String msg) {
        HttpResponse r = new HttpResponse();
        r.setMsg(msg);
        return r;
    }

    public static HttpResponse ok(Object data) {
        HttpResponse r = new HttpResponse();
        r.setData(data);
        return r;
    }

    public static HttpResponse ok(String msg, Object data) {
        HttpResponse r = new HttpResponse();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static HttpResponse ok() {
        return new HttpResponse();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
