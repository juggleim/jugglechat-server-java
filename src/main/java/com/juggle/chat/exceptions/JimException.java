package com.juggle.chat.exceptions;

public class JimException extends RuntimeException{
    private int code;
    private String msg;

    public JimException(int code){
        this.code = code;
        this.msg = "";
    }

    public JimException(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
