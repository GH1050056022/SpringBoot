package com.lucio.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    public static String SUCCESS = "success";

    public static String FAILURE = "failure";

    public static String EXCEPTION = "exception";

    private String code;

    private String msg;

    private T data;

    private String token;



    public Response() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Response(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public Response(String code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
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

