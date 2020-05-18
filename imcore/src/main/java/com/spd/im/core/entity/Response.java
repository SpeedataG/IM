package com.spd.im.core.entity;


public class Response<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setErrorCode(int code) {
        this.code = code;
    }

    public void setErrorMsg(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
