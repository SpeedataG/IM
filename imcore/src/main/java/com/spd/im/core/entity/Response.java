package com.spd.im.core.entity;


import androidx.annotation.Keep;

/**
 * @author :Reginer in  2020/6/1 14:44.
 * 联系方式:QQ:282921012
 * 功能描述:返回响应
 */
@Keep
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
