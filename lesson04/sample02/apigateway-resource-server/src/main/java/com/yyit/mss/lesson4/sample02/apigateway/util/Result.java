package com.yyit.mss.lesson4.sample02.apigateway.util;

import lombok.Data;

@Data
public class Result<T> {

    public static final int ERROR=1;
    public static final int SUCCESS=0;
    private String msg;
    private int code;
    private T data;

    private Result(int code, String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result error(String msg){
        return new Result(ERROR,msg,null);
    }



}
