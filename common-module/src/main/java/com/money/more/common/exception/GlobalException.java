package com.money.more.common.exception;

/**
 * @Describe：统一异常处理
 * @Author：luna
 * @Date：2021/11/29:15:56
 */
public class GlobalException extends RuntimeException{
    private String code;
    private String msg;

    public GlobalException(String code) {
        super(code);
        this.code = code;
    }

    public GlobalException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public GlobalException(String msg, String code, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public GlobalException(String msg, Throwable cause) {
        super(cause);
        this.msg = msg;
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
}
