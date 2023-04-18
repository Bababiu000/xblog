package com.example.xblog.common;

import com.example.xblog.enums.ResultType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success () {
        return new Result(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getMessage(), null);
    }

    public static Result success(Object data) {
        return new Result(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getMessage(), data);
    }

    public static Result success(String msg, Object data) {
        return new Result(ResultType.SUCCESS.getCode(), msg, data);
    }

    public static Result error () {
        return new Result(ResultType.ERROR.getCode(), ResultType.ERROR.getMessage(), null);
    }

    public static Result error (String msg) {
        return new Result(ResultType.ERROR.getCode(), msg, null);
    }

    public static Result error (int code, String msg) {
        return new Result(code, msg, null);
    }

}
