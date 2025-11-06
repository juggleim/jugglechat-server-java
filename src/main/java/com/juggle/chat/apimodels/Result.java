package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juggle.chat.exceptions.JimErrorCode;

public class Result {
    @JsonProperty("code")
    private int code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getData() {
        return this.data;
    }

    public static Result success(Object data){
        return new Result(JimErrorCode.SUCCESS, "success", data);
    }
}
