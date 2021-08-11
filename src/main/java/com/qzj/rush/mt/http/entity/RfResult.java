package com.qzj.rush.mt.http.entity;

import lombok.Data;

@Data
public class RfResult<T> {
    private Integer code;
    private String message;
    private T data;

    public boolean isSuccess(){
        return code == 200;
    }

    public static <T> RfResult<T> error(String message){
        RfResult<T> result = new RfResult<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }
}
